package com.example.yasym.ez_eats;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.yasym.ez_eats.Yelp.Business;
import com.example.yasym.ez_eats.Yelp.Task.LoadBusinessesTask;
import com.example.yasym.ez_eats.Yelp.Yelp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ymno1 on 2/29/2016.
 */
public class YelpLoader extends LoadBusinessesTask {

    private final String LOG_MESSAGE = "YelpLoader: ";

    public YelpLoader(Activity a, String term){
        super((new Yelp(a.getApplicationContext())).setTerm(term));

    }

    @Override
    protected void onPostExecute(List<Business> result) {

        if (result == null) {
            return;
        }

        Log.i(LOG_MESSAGE, result.size() + "");
    }

}
