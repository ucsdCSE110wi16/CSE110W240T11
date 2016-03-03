package com.example.yasym.ez_eats;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yasym.ez_eats.Yelp.Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Class representing the result window.
 * This activity is opened when one of the restaurant categories is
 * selected on the previous page.
 */
public class YelpResultActivity extends Activity {


    private final String TITLE_FONT = "font/remachine.ttf";

    /**
     * Components of this class.
     */
    private ListView resultRestaurants;
    private TextView title;
    private MyListAdapter myAdapter;
    private Button homeButton;

    /**
     * Lists that hold different attributes of restaurants.
     */
    private List<Business> business;
    private List<String> snippetText;
    private List<Drawable> images;
    private List<String> names;

    /**
     * The yelp loader, helps connect to yelp and query for
     * "resultTerm" categories.
     */
    private YelpLoader yLoader;
    private String resultTerm;

    /**
     * Indicates how many items to be displayed.
     */
    private int threshold;
    private int currentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_yelp_result);
        Typeface tf = Typeface.createFromAsset(getAssets(), TITLE_FONT);
        title = (TextView)this.findViewById(R.id.result_title);
        title.setTypeface(tf);
        resultRestaurants = (ListView)this.findViewById(R.id.result_list);
        homeButton = (Button)this.findViewById(R.id.home);

        resultTerm = QuestionActivity.getTerm();
        yLoader = new YelpLoader(this, resultTerm);

        /**
         * Get the result of querying
         */
        try {
            business = yLoader.execute().get();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }catch (ExecutionException ee){
            ee.printStackTrace();
        }

        /**
         * If the returned list is empty, which means no matching restaurant is
         * found, then take user back to the questions and restart.
         */
        if (business == null){
            Log.i("ResultActivity: ", "list is empty");
            AlertDialog.Builder dialog = new AlertDialog.Builder(YelpResultActivity.this);
            dialog.setMessage("No result found! Back to Questions!").setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            YelpResultActivity.this.finish();
                        }
                    }).show();
            return;
        }

        /**
         * Get the threshold
         */
        threshold = QuestionActivity.getThreshold();

        /**
         * Set up the list to be displayed
         */
        names = new ArrayList<>();
        images = new ArrayList<>();
        snippetText = new ArrayList<>();
        for (Business b:business){
            if (currentCount < threshold) {
                names.add(b.name + " [rating=" + b.rating + "]");
                images.add(b.image);
                snippetText.add(b.snippetText);
                currentCount ++;
            }
        }

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this,
                R.layout.restaurant_list, names);
        resultRestaurants.setAdapter(adapter);

        /**
         * When a restaurant is clicked, show its information.
         */
        resultRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
                view.setSelected(true);
                Business tmp = business.get(position);
                AlertDialog.Builder alert = new AlertDialog.Builder(YelpResultActivity.this);
                LayoutInflater factory = LayoutInflater.from(YelpResultActivity.this);
                View v = factory.inflate(R.layout.dialog, null);
                alert.setView(v);

                ImageView i = (ImageView)v.findViewById(R.id.picture);
                i.setImageDrawable(tmp.image);
                TextView t_1 = (TextView)v.findViewById(R.id.text1);
                t_1.setText(tmp.name);

                TextView t_2 = (TextView)v.findViewById(R.id.text2);
                t_2.setText(tmp.location.toString());

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YelpResultActivity.this.finish();
            }
        });
    }


}
