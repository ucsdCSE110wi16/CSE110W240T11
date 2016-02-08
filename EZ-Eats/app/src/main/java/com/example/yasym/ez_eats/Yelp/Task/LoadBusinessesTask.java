package com.example.yasym.ez_eats.Yelp.Task;

import android.os.AsyncTask;

import com.example.yasym.ez_eats.Yelp.Business;
import com.example.yasym.ez_eats.Yelp.Yelp;

import java.util.List;

/**
 * Created by simon on 2/7/16.
 */
public class LoadBusinessesTask extends AsyncTask<Void, Void, List<Business>> {

    private Yelp api;

    public LoadBusinessesTask(Yelp api) {
        this.api = api;
    }

    @Override
    protected List<Business> doInBackground(Void... params) {
        return api.get();
    }

    @Override
    protected void onPostExecute(List<Business> result) {
        if (result != null) {
            System.out.println("Got " + result.size() + " businesses.");
            System.out.println("The first one is \"" + result.get(0).name + "\"");
        }
    }
}
