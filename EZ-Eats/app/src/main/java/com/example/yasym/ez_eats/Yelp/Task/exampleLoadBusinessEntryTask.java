package com.example.yasym.ez_eats.Yelp.Task;

import android.app.Activity;
import android.util.Log;

import com.example.yasym.ez_eats.Yelp.Business;
import com.example.yasym.ez_eats.Yelp.Yelp;

import java.util.List;

/**
 * Created by simon on 2/8/16.
 */
public class exampleLoadBusinessEntryTask extends LoadBusinessesTask {

    Activity a;

    public static final String LOG_TAG = "Task.LoadBusiness";

    public exampleLoadBusinessEntryTask(Activity a) {
        super((new Yelp(a.getApplicationContext())).setTerm("Pizza"));
        this.a = a;
    }

    @Override
    protected void onPostExecute(List<Business> result) {
        if (result == null) {
            return;
        }

        Business b0 = result.get(0);
        Log.i(LOG_TAG, "Got " + result.size() + " businesses.");
        Log.i(LOG_TAG, "The first one is \"" + b0.name + "\"");
//        ((TextView) a.findViewById(R.id.testTextView)).setText(b0.name);
//        ((ImageView) a.findViewById(R.id.testImageView)).setImageDrawable(b0.image);
//        ((ImageView) a.findViewById(R.id.testImageStars)).setImageDrawable(b0.ratingImage);
//        ((ImageView) a.findViewById(R.id.testImageStarsSmall)).setImageDrawable(b0.ratingImageSmall);
//        ((ImageView) a.findViewById(R.id.testImageStarsLarge)).setImageDrawable(b0.ratingImageLarge);
    }
}
