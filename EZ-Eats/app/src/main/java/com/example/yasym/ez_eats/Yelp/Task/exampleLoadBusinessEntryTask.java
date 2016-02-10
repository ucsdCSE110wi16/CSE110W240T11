package com.example.yasym.ez_eats.Yelp.Task;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.yasym.ez_eats.Yelp.Business;
import com.example.yasym.ez_eats.Yelp.Yelp;

import java.util.List;

/**
 * Created by simon on 2/8/16.
 */
public class exampleLoadBusinessEntryTask extends LoadBusinessesTask {

    TextView textView;
    ImageView imageView;

    public exampleLoadBusinessEntryTask(TextView tv, ImageView iv) {
        this(tv, iv, new Yelp());
    }

    public exampleLoadBusinessEntryTask(TextView tv, ImageView iv, Yelp api) {
        super(api);
        textView = tv;
        imageView = iv;
    }

    @Override
    protected void onPostExecute(List<Business> result) {
        if (result != null) {
            Business b0 = result.get(0);
            System.out.println("Got " + result.size() + " businesses.");
            System.out.println("The first one is \"" + b0.name + "\"");
            textView.setText(b0.name);
            imageView.setImageDrawable(b0.image);
        }
    }
}
