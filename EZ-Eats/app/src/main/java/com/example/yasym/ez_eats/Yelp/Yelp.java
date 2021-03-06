package com.example.yasym.ez_eats.Yelp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Yelp API.
 * Usage: {@link com.example.yasym.ez_eats.Yelp.Task.LoadBusinessesTask} (on main thread)
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

    private static final String PRESET_LOCATION = "La Jolla, CA";

    private static final String CONSUMER_KEY = "Xa4WSNVHq-Edzf9YkVMftA";
    private static final String CONSUMER_SECRET = "Pws-vhJ66gNIvu8VehP62cqS3jc";
    private static final String TOKEN = "pPShxbu5bI7zTwnldC_5AGha5eVRm0iz";
    private static final String TOKEN_SECRET = "j3B32ZhobUno-_UVRvaFsyXtWUU";

    private static final String LOG_TAG = "Yelp";

    private BaseAPI api;
    private Map<String, String> params;
    private Gson gson;
    private BusinessDeserializer deserializer;
    private List<Business> cache;
    private Context context;

    /**
     * Build a Yelp API object.
     *
     * @param context Returned object of getApplicationContext()
     */
    public Yelp(Context context) {
        this.context = context;
        api = new BaseAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
        params = new HashMap<>();
        deserializer = new BusinessDeserializer(context);
        gson = new GsonBuilder().registerTypeAdapter(Business.class, deserializer).create();
    }

    /**
     * @param term Search term (e.g. "food", "restaurants"). If term isn’t included we search
     *             everything. The term keyword also accepts business names such as "Starbucks".
     * @return this
     */
    public Yelp setTerm(String term) {
        setParam("term", term);
        return this;
    }

    /**
     * @param categories Category to filter search results with. See the list of supported categories.
     *                   Categories are "or"ed.
     * @return this
     */
    public Yelp setCatagories(List<String> categories) {
        if (categories.size() > 0) {
            setParam("category_filter", TextUtils.join(",", categories));
        }
        return this;
    }

    /**
     * @param radius Search radius in meters. If the value is too large, a AREA_TOO_LARGE error may be
     *               returned.
     * @return this
     */
    public Yelp setRadius(int radius) {
        if (radius > 0 && radius <= 40000) {
            setParam("radius_filter", String.valueOf(radius));
        }
        return this;
    }

    /**
     * @param limit Number of business results to return
     * @return this
     */
    public Yelp setLimit(int limit) {
        if (limit > 0 && limit < 20) {
            setParam("limit", String.valueOf(limit));
        }
        return this;
    }

    /**
     * @param sort Sort mode: Best matched (default), Distance, Highest Rated. If the mode is Distance
     *             or Highest Rated a search may retrieve an additional 20 businesses past the initial
     *             limit of the first 20 results. This is done by specifying an offset and limit of
     *             20.
     *             Sort by distance is only supported for a location or geographic search. The rating
     *             sort is not strictly sorted by the rating value, but by an adjusted rating value
     *             that takes into account the number of ratings, similar to a bayesian average. This
     *             is so a business with 1 rating of 5 stars doesn’t immediately jump to the top.
     * @return this
     */
    public Yelp setSort(Sort sort) {
        if (sort != Sort.BEST_MATCHED) {
            setParam("sort", String.valueOf(sort.value));
        }
        return this;
    }

    /**
     * @param offset Offset the list of returned business results by this amount
     * @return this
     */
    public Yelp setOffset(int offset) {
        if (offset > 0) {
            setParam("offset", String.valueOf(offset));
        }
        return this;
    }

    /**
     * @param deals Whether to exclusively search for businesses with deals
     * @return this
     */
    public Yelp setDeals(boolean deals) {
        if (deals) {
            setParam("deals_filter", String.valueOf(deals));
        }
        return this;
    }

    /**
     * @param loadingImages Whether to load {@link Business#image} and {@link Business#snippetImage} or not. It
     *                   could help reduce loading time when images are not needed. Default is True.
     */
    public Yelp setLoadingImages(boolean loadingImages) {
        this.deserializer.loadingImages = loadingImages;
        cache = null;
        return this;
    }

    private void setParam(String key, String value) {
        params.put(key, value);
        cache = null;
    }

    /**
     * Get a list of businesses' data found according to the parameters provided in the ctors before.
     *
     * @return a list of {@link Business}es, or null if no business was found
     */
    public List<Business> get() {
        // throw exception if mode = Distance and not a location or geographic search?

        // Get location
        String key, location = Utils.getCoords(context);
        if (location == null) {
            key = "location";
            location = PRESET_LOCATION;
            Log.i(LOG_TAG, "Failed to access to user's current location. Using the default city instead");
        } else {
            key = "ll";
        }
        params.put(key, location);

        InputStream searchResponse;
        try {
            searchResponse = api.searchForBusinesses(params);
        } catch (OAuthConnectionException e) {
            Log.e(LOG_TAG, "Failed to connect to Yelp server");
            return null;
        }

        JsonObject respObj = (JsonObject) (new JsonParser()).parse(new InputStreamReader(searchResponse));
        JsonArray barr = respObj.getAsJsonArray("businesses");
        if (barr == null) {
            String msg = null;
            try {
                String id = respObj.getAsJsonObject("error").getAsJsonPrimitive("id").getAsString();
                if (id.equals("INVALID_SIGNATURE")) {
                    msg = "Is your system clock correct?";
                }
            } catch (NullPointerException ex) {
            }
            msg = (msg == null) ? "Invalid response: " + respObj.toString() : "Invalid request: " + msg;
            Log.e(LOG_TAG, msg);
            return null;
        }

        List<Business> bs = new ArrayList<>();
        for (JsonElement e : barr) {
            try {
                Business b = gson.fromJson(e, Business.class);
                bs.add(b);
            } catch (JsonSyntaxException ex) {
                Log.e(LOG_TAG, "Failed to parse JSON response: " + searchResponse, ex);
            }
        }

        cache = (bs.size() > 0) ? bs : null;
        return cache;
    }

    static class BusinessDeserializer implements JsonDeserializer<Business> {

        boolean loadingImages;
        Gson gson;
        Context context;

        public BusinessDeserializer(Context context) {
            this.loadingImages = true;
            this.gson = new GsonBuilder().registerTypeAdapter(Category.class, new CategoryDeserializer()).
                    addDeserializationExclusionStrategy(new DrawableExclusionStrategy()).create();
            this.context = context;
        }

        @Override
        public Business deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext jsonContext)
                throws JsonParseException {
            Business b = gson.fromJson(json, Business.class);
            // Set rating images
            if (context != null) {
                Drawable[] ratingImages = Utils.getRatingImages(context, b.rating);
                b.ratingImage = ratingImages[0];
                b.ratingImageSmall = ratingImages[1];
                b.ratingImageLarge = ratingImages[2];
            }
            // Load images of the business
            if (loadingImages) {
                JsonObject o = (JsonObject) json;
                b.image = Utils.getImage(o, "image_url");
                b.snippetImage = Utils.getImage(o, "snippet_image_url");
            }
            return b;
        }

        static class DrawableExclusionStrategy implements ExclusionStrategy {
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz.equals(Drawable.class);
            }

            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }
        }
    }

    static class CategoryDeserializer implements JsonDeserializer<Category> {
        @Override
        public Category deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String name = ((JsonArray) json).get(0).getAsString();
            String alias = ((JsonArray) json).get(1).getAsString();
            return new Category(name, alias);
        }
    }
}
