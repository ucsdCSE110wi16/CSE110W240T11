package com.example.yasym.ez_eats.Yelp;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by simon on 2/8/16.
 */
public class Utils {

    private static final String LOG_TAG = "Yelp.Utils";

    public static Drawable getImage(JsonObject o, String memberName) {
        return getImage(o.getAsJsonPrimitive(memberName).getAsString());
    }

    public static Drawable getImage(String url) {
        try {
            InputStream is = (InputStream) new URL(url).openConnection()
                    .getContent(new Class[]{InputStream.class});
            return Drawable.createFromStream(is, null);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to load the image from the url: " + url);
            return null;
        }
    }
}
