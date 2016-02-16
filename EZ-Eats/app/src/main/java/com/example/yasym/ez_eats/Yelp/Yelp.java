package com.example.yasym.ez_eats.Yelp;

import com.example.yasym.ez_eats.MainActivity;
import com.example.yasym.ez_eats.Yelp.Task.CurrentLocation;

import android.util.Log;
import android.location.Location;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.scribe.exceptions.OAuthConnectionException;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Yelp API.
 * <p>
 * Usage:
 * on main thread: {@link com.example.yasym.ez_eats.Yelp.Task.LoadBusinessesTask}
 * on non-main thread: {@code
 * Yelp api = new Yelp("pizza");
 * List<Business> bs = api.get();
 * if (bs.size() > 0) {
 * System.out.println(bs.get(0).name);
 * }
 * }
 * <p>
 * Created by simon on 2/6/16.
 */
public class Yelp {

    public enum Sort {
        BEST_MATCHED(0),
        DISTANCE(1),
        HIGHEST_RATED(2);

        int value;

        Sort(int value) {
            this.value = value;
        }
    }

    private static final String DEFAULT_TERM = "";
    private static final int DEFAULT_LIMIT = -1; // maximum is 20
    private static final int DEFAULT_OFFSET = 0;
    // TODO attach parsed categories
    private static final List<Category> DEFAULT_CATEGORIES = new ArrayList<>();
    // TODO maybe we could set two default radii for users with and without car
    private static final int DEFAULT_RADIUS = -1; // maximum is 40000 meters (= 25 miles)
    private static final boolean DEFAULT_DEALS = false; // default is false
    private static final Sort DEFAULT_SORT = Sort.BEST_MATCHED;

    private static final String PRESET_LOCATION = "La Jolla, CA";

    private static final String CONSUMER_KEY = "Xa4WSNVHq-Edzf9YkVMftA";
    private static final String CONSUMER_SECRET = "Pws-vhJ66gNIvu8VehP62cqS3jc";
    private static final String TOKEN = "eK7QQ_sBeljpQhXFbzk1VIRpIafYJ02b";
    private static final String TOKEN_SECRET = "Z9FRq0Q9vRb4WppEgLVlVbAUwfQ";

    private static final String LOG_TAG = "Yelp";

    private BaseAPI api;
    private Map<String, String> params;
    private Gson gson;
    private BusinessDeserializer deserializer;

    public Yelp() {
        this(DEFAULT_TERM, DEFAULT_CATEGORIES);
    }

    public Yelp(String term) {
        this(term, DEFAULT_CATEGORIES);
    }

    public Yelp(List<Category> categories) {
        this(DEFAULT_TERM, categories);
    }

    public Yelp(String term, List<Category> categories) {
        this(term, categories, DEFAULT_RADIUS);
    }

    public Yelp(String term, List<Category> categories, int radius) {
        this(term, categories, radius, null, DEFAULT_SORT, DEFAULT_OFFSET, DEFAULT_DEALS, DEFAULT_LIMIT);
    }

    /**
     * Build a Yelp API object.
     *
     * @param term       Search term (e.g. "food", "restaurants"). If term isn’t included we search
     *                   everything. The term keyword also accepts business names such as "Starbucks".
     * @param categories Category to filter search results with. See the list of supported categories.
     *                   Categories are "or"ed.
     * @param radius     Search radius in meters. If the value is too large, a AREA_TOO_LARGE error may be
     *                   returned.
     * @param location   Location to find businesses. Pass null to use the current GPS location
     * @param sort       Sort mode: Best matched (default), Distance, Highest Rated. If the mode is Distance
     *                   or Highest Rated a search may retrieve an additional 20 businesses past the initial
     *                   limit of the first 20 results. This is done by specifying an offset and limit of 20.
     *                   Sort by distance is only supported for a location or geographic search. The rating
     *                   sort is not strictly sorted by the rating value, but by an adjusted rating value
     *                   that takes into account the number of ratings, similar to a bayesian average. This
     *                   is so a business with 1 rating of 5 stars doesn’t immediately jump to the top.
     * @param offset     Offset the list of returned business results by this amount
     * @param deals      Whether to exclusively search for businesses with deals
     * @param limit      Number of business results to return
     */
    public Yelp(String term, List<Category> categories, int radius, String location, Sort sort, int offset,
                boolean deals, int limit) {
        api = new BaseAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);

        params = new HashMap<>();
        if (!term.equals(DEFAULT_TERM)) {
            params.put("term", term);
        }
        if (location == null) {
            //Get the current location
            CurrentLocation lastKnownPlace = MainActivity.lastKnownPlace;
            Location currentPlace = lastKnownPlace.getLoc();
            //Checks to see if latitude/longitude are possible to get
            if (currentPlace != null) {
                String lati = String.valueOf(lastKnownPlace.getLatitude());
                String longi = String.valueOf(lastKnownPlace.getLongitude());
                location = "&ll=" + lati + "," + longi;
            }
            //use preset location if user cannot get current location
            else {
                location = "&location=" + PRESET_LOCATION;
            }
        }
        params.put("location", location);
        if (!categories.equals(DEFAULT_CATEGORIES)) {
            params.put("category_filter", joinCategoryAliases(categories));
        }
        if (sort != DEFAULT_SORT) {
            // TODO throw exception if mode = Distance and not a location or geographic search. == null?
            params.put("sort", String.valueOf(sort.value));
        }
        if (offset != DEFAULT_OFFSET) {
            params.put("offset", String.valueOf(offset));
        }
        if (deals != DEFAULT_DEALS) {
            params.put("deals_filter", String.valueOf(deals));
        }
        if (radius != DEFAULT_RADIUS) {
            params.put("radius_filter", String.valueOf(radius));
        }
        if (limit != DEFAULT_LIMIT) {
            params.put("limit", String.valueOf(limit));
        }

        deserializer = new BusinessDeserializer();
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Business.class, deserializer);
        gson = builder.create();
    }

    public List<Business> get() {
        return get(true);
    }

    /**
     * Get a list of businesses' data found according to the parameters provided in the ctors before.
     *
     * @param loadImages Whether to load {@link Business#image} and {@link Business#snippetImage} or not. It
     *                   could help reduce loading time when images are not needed.
     * @return a list of {@link Business}es, or null if no business was found
     */
    public List<Business> get(boolean loadImages) {
        Reader searchResponse;
        try {
            searchResponse = api.searchForBusinesses(params);
        } catch (OAuthConnectionException e) {
            Log.e(LOG_TAG, "Failed to connect to Yelp server");
            Log.d(LOG_TAG, "Error: ", e);
            return null;
        }

        List<Business> bs = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray barr = ((JsonObject) parser.parse(searchResponse)).getAsJsonArray("businesses");
        deserializer.loadImages = loadImages;
        for (JsonElement e : barr) {
            try {
                Business b = gson.fromJson(e, Business.class);
                bs.add(b);
            } catch (JsonSyntaxException ex) {
                Log.e(LOG_TAG, "Failed to parse JSON response: " + searchResponse, ex);
            }
        }

        return (bs.size() > 0) ? bs : null;
    }

    private static String joinCategoryAliases(List<Category> list) {
        StringBuilder s = new StringBuilder();
        for (Category c : list) {
            s.append(c.alias);
            s.append(',');
        }
        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

    class BusinessDeserializer implements JsonDeserializer<Business> {

        boolean loadImages;
        Gson gson;

        public BusinessDeserializer() {
            this.loadImages = true;
            this.gson = new GsonBuilder().registerTypeAdapter(Category.class, new CategoryDeserializer())
                    .create();
        }

        @Override
        public Business deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            Business b = gson.fromJson(json, Business.class);
            //            TODO set rating images
            if (loadImages) {
                b.image = Utils.getImage((JsonObject) json, "image_url");
                b.snippetImage = Utils.getImage((JsonObject) json, "snippet_image_url");
            }
            return b;
        }
    }

    class CategoryDeserializer implements JsonDeserializer<Category> {
        @Override
        public Category deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String name = ((JsonArray) json).get(0).getAsString();
            String alias = ((JsonArray) json).get(1).getAsString();
            return new Category(name, alias);
        }
    }
}
