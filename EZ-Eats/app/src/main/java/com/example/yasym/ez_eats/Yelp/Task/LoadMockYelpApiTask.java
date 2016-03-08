package com.example.yasym.ez_eats.Yelp.Task;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yasym.ez_eats.R;
import com.example.yasym.ez_eats.Yelp.Business;
import com.example.yasym.ez_eats.Yelp.Yelp;

import java.util.List;

/**
 * Created by simon on 3/7/16.
 */
public class LoadMockYelpApiTask extends AsyncTask<Void, Void, List<Business>> {

    private Yelp api;
    private Activity a;
    private boolean isRunning;

    private static final String LOG_TAG = "Yelp.Task.Mock";

    public LoadMockYelpApiTask(Yelp api, Activity a) {
        this.api = api;
        this.a = a;
        isRunning = true;
    }

    @Override
    protected List<Business> doInBackground(Void... params) {
        try {
            return api.get();
        }
        catch (Exception e) {
            isRunning = false;
            throw e;
        }
    }

    @Override
    protected void onPostExecute(List<Business> result) {
        try {
            if (result == null) {
                return;
            }

            Business b0 = result.get(0);
            ((TextView) a.findViewById(R.id.name)).setText(b0.name);
            ((ImageView) a.findViewById(R.id.ratingImage)).setImageDrawable(b0.ratingImageLarge);
            ((TextView) a.findViewById(R.id.address)).setText(b0.location.toString());
            ((TextView) a.findViewById(R.id.rating)).setText(String.valueOf(b0.rating));
            ((TextView) a.findViewById(R.id.phone)).setText(b0.displayPhone);
        }
        finally {
            isRunning = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
