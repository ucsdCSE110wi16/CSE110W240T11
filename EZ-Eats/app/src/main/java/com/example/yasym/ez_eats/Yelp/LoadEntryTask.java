package com.example.yasym.ez_eats.Yelp;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.ConnectException;
import java.util.List;

/**
 * Created by simon on 2/7/16.
 */
public class LoadEntryTask extends AsyncTask<String, Void, List<Business>> {

    @Override
    protected List<Business> doInBackground(String... params) {
        TextView tv = (TextView) params[0];
        ImageView iv = (ImageView) params[1];

        Yelp api = new Yelp();
        Business b0;
        try {
            b0 = api.get().get(0);
        } catch (ConnectException e) {
            iv.setImageDrawable(b0.image);
            tv.setText("Connection failure");
            return;
        }

        return null;
    }

    protected void onPostExecute(Bitmap result) {
        mImageView.setImageBitmap(result);
    }
}
