package com.example.yasym.ez_eats.YelpTask;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.yasym.ez_eats.YelpTask.Yelp.Business;
import com.example.yasym.ez_eats.YelpTask.Yelp.Yelp;

import java.util.List;

/**
 * Created by simon on 2/8/16.
 */
public class LoadEntryTask extends LoadBusinessesTask {

    TextView textView;
    ImageView imageView;

    public LoadEntryTask(TextView tv, ImageView iv) {
        super(new Yelp());
        textView = tv;
        imageView = iv;
    }

    @Override
    protected void onPostExecute(List<Business> result) {
        if (success) {
            Business b0 = result.get(0);
            textView.setText(b0.name);
            imageView.setImageDrawable(b0.image);
        }
        else {
            textView.setText((connectException == null) ? "Unknown Error" : "Connection Failure");
        }
    }
}
