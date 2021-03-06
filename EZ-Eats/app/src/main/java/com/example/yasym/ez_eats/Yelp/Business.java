package com.example.yasym.ez_eats.Yelp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by simon on 2/7/16.
 * <p/>
 */
public class Business {

    public String name; // Name of this business
    public List<Category> categories; // Categories that this business is associated with.

    @SerializedName("display_phone")
    public String displayPhone; // Phone number for this business formatted for display
    public String phone;
    public double rating; // Rating for this business (value ranges from 1, 1.5, ... 4.5, 5)
    public Location location; // Location data for this business
    public Drawable image; // Photo for this business

    @SerializedName("snippet_text")
    public String snippetText; // Snippet text associated with this business
    public Drawable snippetImage; // URL of snippet image associated with this business
    public Drawable ratingImage; // Star rating image for this business (size = 103x19)
    public Drawable ratingImageSmall; // Small version of rating image for this business (size = 58x10)
    public Drawable ratingImageLarge; // Large version of rating image for this business (size = 183x31)
    public String id; // Yelp ID for this business

    @SerializedName("mobile_url")
    private String mobileUrl; // for mobile business page on Yelp
    private String url; // for business page on Yelp

    private static final String LOG_TAG = "Yelp.Business";

    public Intent getYelpAppIntent() {
//        TODO open the Yelp App
        return null;
    }

    public Intent getYelpWebsiteIntent() {
//        TODO open in a browser
        return null;
    }

    public Intent getMapIntentFromLocation() {
//        TODO use Google Maps?
        return null;
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

        public class Coordinate {
            public double latitude; // Latitude associated with the location.
            public double longitude; // Longitude associated with the location.
        }
    }
}
