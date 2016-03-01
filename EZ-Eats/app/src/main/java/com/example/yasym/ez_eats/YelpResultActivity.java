package com.example.yasym.ez_eats;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yasym.ez_eats.Yelp.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class YelpResultActivity extends AppCompatActivity {

    private ListView resultRestaurants;
    private MyListAdapter myAdapter;

    private List<Business> business;
    private List<String> snippetText;

    private YelpLoader yLoader;
    private String resultTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_yelp_result);
        resultRestaurants = (ListView)this.findViewById(R.id.result_list);

        resultTerm = QuestionActivity.getTerm();
        yLoader = new YelpLoader(this, resultTerm);

        try {
            business = yLoader.execute().get();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }catch (ExecutionException ee){
            ee.printStackTrace();
        }

//
//        Log.i("business size = ", business.size() + "");
//        Log.i("Yelp result", "finished");

        snippetText = new ArrayList<>();
        for (Business b:business){
            snippetText.add(b.name + " [rating=" + b.rating + "]");
        }

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this,
                R.layout.restaurant_list, snippetText);
        resultRestaurants.setAdapter(adapter);
    }

}
