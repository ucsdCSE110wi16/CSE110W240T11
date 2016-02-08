package com.example.yasym.ez_eats.YelpTask;

import android.os.AsyncTask;

import com.example.yasym.ez_eats.YelpTask.Yelp.Business;
import com.example.yasym.ez_eats.YelpTask.Yelp.Yelp;

import java.net.ConnectException;
import java.util.List;

/**
 * Created by simon on 2/7/16.
 */
public class LoadBusinessesTask extends AsyncTask<Void, Void, List<Business>> {

    private Yelp api;
    protected ConnectException connectException;
    protected boolean success;

    public LoadBusinessesTask(Yelp api) {
        this.api = api;
    }

    @Override
    protected List<Business> doInBackground(Void... params) {
        Yelp api = new Yelp();
        List<Business> bs;
        try {
            bs = api.get();
        } catch (ConnectException e) {
            connectException = e;
            return null;
        }
        success = true;
        return bs;
    }

    @Override
    protected void onPostExecute(List<Business> result) {
        if (success) {
            System.out.println("Got " + result.size() + " businesses.");
            System.out.println("The first one is \"" + result.get(0).name + "\"");
        }
    }
}
