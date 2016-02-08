package com.example.yasym.ez_eats.Yelp;

import android.util.Log;

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
 * Created by simon on 2/6/16.
 * <p/>
 */
public class Yelp {

    // Search term (e.g. "food", "restaurants"). If term isn’t included we search everything.
    // The term keyword also accepts business names such as "Starbucks".
    private static final String DEFAULT_TERM = "";

    // Number of business results to return
    private static final int DEFAULT_LIMIT = -1; // maximum is 20

    // Offset the list of returned business results by this amount
    private static final int DEFAULT_OFFSET = 0;

    // Category to filter search results with. See the list of supported categories. TODO attach parsed categories
    // Categories are "or"ed.
    private static final List<Category> DEFAULT_CATEGORIES = new ArrayList<>();

    // Search radius in meters. If the value is too large, a AREA_TOO_LARGE error may be returned.
    // TODO maybe we could set two default radii for users with and without car
    private static final int DEFAULT_RADIUS = -1; // maximum is 40000 meters (= 25 miles)

    // Whether to exclusively search for businesses with deals
    private static final boolean DEFAULT_DEALS = false; // default is false

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
        this(term, categories, radius, null, DEFAULT_OFFSET, DEFAULT_DEALS, DEFAULT_LIMIT);
    }

    public Yelp(String term, List<Category> categories, int radius, String location, int offset, boolean deals, int limit) {
        api = new BaseAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);

        params = new HashMap<>();
        if (!term.equals(DEFAULT_TERM)) {
            params.put("term", term);
        }
        if (location == null) {
//            TODO get accurate location through GPS or network
//            TODO learn methods of specifying location https://www.yelp.com/developers/documentation/v2/search_api
//            TODO on failure, location = PRESET_LOCATION
            location = PRESET_LOCATION;
        }
        params.put("location", location);
        if (!categories.equals(DEFAULT_CATEGORIES)) {
            params.put("category_filter", joinCategoryAliases(categories));
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
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(Business.class, deserializer);
        gson = builder.create();
    }

    public List<Business> get() {
        return get(true);
    }

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
        JsonArray barr = ((JsonObject) parser.parse(searchResponse))
                .getAsJsonArray("businesses");
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
            this.gson = new GsonBuilder()
                    .registerTypeAdapter(Category.class, new CategoryDeserializer()).create();
        }

        @Override
        public Business deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
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
        public Category deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
            String name = ((JsonArray) json).get(0).getAsString();
            String alias = ((JsonArray) json).get(1).getAsString();
            return new Category(name, alias);
        }
    }
}
