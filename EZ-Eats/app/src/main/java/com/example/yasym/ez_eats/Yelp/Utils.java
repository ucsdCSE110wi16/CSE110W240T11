package com.example.yasym.ez_eats.Yelp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.yasym.ez_eats.R;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by simon on 2/8/16.
 */
public class Utils {

    private static final String[] LOCATION_PROVIDERS =
            {LocationManager.NETWORK_PROVIDER, LocationManager.GPS_PROVIDER,
                    LocationManager.PASSIVE_PROVIDER};
    static final int[][] IDSS =
            new int[][]{{R.drawable.stars_0, R.drawable.stars_small_0, R.drawable.stars_large_0},
                    {R.drawable.stars_1, R.drawable.stars_small_1, R.drawable.stars_large_1},
                    {R.drawable.stars_1_5, R.drawable.stars_small_1_5, R.drawable.stars_large_1_5},
                    {R.drawable.stars_2, R.drawable.stars_small_2, R.drawable.stars_large_2},
                    {R.drawable.stars_2_5, R.drawable.stars_small_2_5, R.drawable.stars_large_2_5},
                    {R.drawable.stars_3, R.drawable.stars_small_3, R.drawable.stars_large_3},
                    {R.drawable.stars_3_5, R.drawable.stars_small_3_5, R.drawable.stars_large_3_5},
                    {R.drawable.stars_4, R.drawable.stars_small_4, R.drawable.stars_large_4},
                    {R.drawable.stars_4_5, R.drawable.stars_small_4_5, R.drawable.stars_large_4_5},
                    {R.drawable.stars_5, R.drawable.stars_small_5, R.drawable.stars_large_5}};

    private static final String LOG_TAG = "Yelp.Utils";

    static String getCoords(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            LocationManager locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            for (String provider : LOCATION_PROVIDERS) {
                Location loc = locManager.getLastKnownLocation(provider);
                if (loc != null) {
                    String coords = loc.getLatitude() + "," + loc.getLongitude();
                    Log.d(LOG_TAG, "Current coords (Longitude,Latitude): " + coords);
                    return coords;
                }
            }
            Log.i(LOG_TAG, "No location providers available");
        } else {
            Log.i(LOG_TAG, "No permission granted to access location");
        }
        return null;
    }

    static Drawable getImage(JsonObject o, String memberName) {
        return getImage(o.getAsJsonPrimitive(memberName).getAsString());
    }

    static Drawable getImage(String url) {
        try {
            InputStream is =
                    (InputStream) new URL(url).openConnection().getContent(new Class[]{InputStream.class});
            return Drawable.createFromStream(is, null);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to load the image from the url: " + url);
            return null;
        }
    }

    static Drawable[] getRatingImages(Context context, double rating) {
        int iRating = (int) Math.round(2 * rating) - 1;
        if (!(iRating >= 1 && iRating <= 9)) {
            iRating = 0;
        }
        int[] ids = IDSS[iRating];
        return new Drawable[]{ContextCompat.getDrawable(context, ids[0]),
                ContextCompat.getDrawable(context, ids[1]), ContextCompat.getDrawable(context, ids[2])};
    }
}
