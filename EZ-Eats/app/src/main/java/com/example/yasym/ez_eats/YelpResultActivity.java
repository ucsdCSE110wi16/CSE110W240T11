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
import android.widget.SimpleAdapter;

import com.example.yasym.ez_eats.Yelp.Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class YelpResultActivity extends AppCompatActivity {

    private ListView resultRestaurants;
    private MyListAdapter myAdapter;

    private List<Business> business;
    private List<String> snippetText;
    private List<Drawable> images;
    private List<String> names;

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

        names = new ArrayList<>();
        images = new ArrayList<>();
        snippetText = new ArrayList<>();
        for (Business b:business){
            names.add(b.name);
            images.add(b.image);
            snippetText.add(b.snippetText);
        }
        

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this,
                R.layout.restaurant_list, snippetText);
        resultRestaurants.setAdapter(adapter);
    }

}
