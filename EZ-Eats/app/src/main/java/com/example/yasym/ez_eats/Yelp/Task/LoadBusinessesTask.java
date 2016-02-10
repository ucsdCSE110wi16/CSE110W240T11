package com.example.yasym.ez_eats.Yelp.Task;

import android.os.AsyncTask;

import com.example.yasym.ez_eats.Yelp.Business;
import com.example.yasym.ez_eats.Yelp.Yelp;

import java.util.List;

/**
 * Primitive AsyncTask for loading Yelp data on the main thread.
 * What is the main thread:
 * http://stackoverflow.com/questions/3652560/what-is-the-android-uithread-ui-thread
 * Why use AsyncTask:
 * http://developer.android.com/training/articles/perf-anr.html
 * Why use AsyncTask in one sentense:
 * The api won't work on the main thread without using something like AsyncTask.
 * <p/>
 * Usage: Override onPostExecute() to implement the callback, and then {@code
 * Yelp api = new Yelp("pizza");
 * new LoadBusinessesTask(api).execute();
 * }
 * Also see {@link AsyncTask}
 * <p/>
 * Created by simon on 2/7/16.
 */
public abstract class LoadBusinessesTask extends AsyncTask<Void, Void, List<Business>> {

    private Yelp api;

    public LoadBusinessesTask(Yelp api) {
        this.api = api;
    }

    @Override
    protected List<Business> doInBackground(Void... params) {
        return api.get();
    }

    @Override
    protected abstract void onPostExecute(List<Business> result);
}
