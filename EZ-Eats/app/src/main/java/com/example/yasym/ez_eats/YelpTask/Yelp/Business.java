package com.example.yasym.ez_eats.YelpTask.Yelp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by simon on 2/7/16.
 * <p/>
 * TODO is Drawable the appropriate type for images?
 * TODO using local assets instead of downloading images for ratingImageUrl, ...
 * TODO customzie deserializer so that urls are replaced with images
 */
public class Business {


    public String name; // Name of this business
    public List<Category> categories; // Categories that this business is associated with.
    @SerializedName("display_phone")
    public String displayPhone;
    public String phone; // Phone number for this business formatted for display
    public double rating; // Rating for this business (value ranges from 1, 1.5, ... 4.5, 5)
    public Location location; // Location data for this business
    public Drawable image; // Photo for this business
    @SerializedName("snippet_text")
    public String snippetText; // Snippet text associated with this business
    public Drawable snippetImage; // URL of snippet image associated with this business

    private String id; // Yelp ID for this business // TODO is this entry necessary?
    private String url; // for business page on Yelp
    private String mobileUrl; // for mobile business page on Yelp
    @SerializedName("rating_img_url")
    private String ratingImageUrl; // Star rating image for this business (size = 84x17)
    @SerializedName("rating_img_url_small")
    private String ratingImageUrlSmall; // Small version of rating image for this business (size = 50x10)
    @SerializedName("rating_img_url_large")
    private String ratingImageUrlLarge; // Large version of rating image for this business (size = 166x30)

    private static final String LOG_TAG = "Yelp.Business";

    public Intent getYelpAppIntent() {
//        TODO open the Yelp App
        return null;
    }

    public Intent getYelpDesktopIntent() {
//        TODO probably means open in a browser
        return null;
    }

    public Intent getMapIntentFromLocation() {
//        TODO use Google Maps?
        return null;
    }

    public Drawable getRatingImage() {
        return getImage(ratingImageUrl);
    }

    public Drawable getLargeRatingImage() {
        return getImage(ratingImageUrlLarge);
    }

    public Drawable getSmallRatingImage() {
        return getImage(ratingImageUrlSmall);
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    private static Drawable getImage(JsonObject o, String memberName) {
        return getImage(o.getAsJsonPrimitive(memberName).getAsString());
    }

    private static Drawable getImage(String url) {
        try {
            InputStream is = (InputStream) new URL(url).openConnection().getContent(new Class[]{InputStream.class});
            return Drawable.createFromStream(is, null);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to load the image from the url: " + url);
            return null;
        }
    }

    /**
     * Location data for this business
     */
    public class Location {

        @SerializedName("display_address")
        public List<String> displayAddress; // Address for this business formatted for display. Includes all address fields, cross streets and city, state_code, etc.
        @SerializedName("cross_streets")
        public String crossStreets; // Cross streets for this business
        public List<String> address; // Address for this business. Only includes address fields.
        public List<String> neighborhoods; // List that provides neighborhood(s) information for business
        public String city; // City for this business
        @SerializedName("state_code")
        public String stateCode; // ISO 3166-2 state code for this business
        @SerializedName("postal_code")
        public String postalCode; // Postal code for this business
        @SerializedName("country_code")
        public String countryCode; // ISO 3166-1 country code for this business

        // Coordinates of this location.
        // This will be omitted if coordinates are not known for the location.
        public Coordinate coordinate;

        public String toString() {
            return TextUtils.join("\n", displayAddress);
        }
    }

    public class Coordinate {
        public double latitude; // Latitude associated with the location.
        public double longitude; // Longitude associated with the location.
    }
}
