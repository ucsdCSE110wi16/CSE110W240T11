package com.example.yasym.ez_eats;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yasym.ez_eats.Yelp.Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ymno1 on 2/29/2016.
 */
public class MyListAdapter extends ArrayAdapter{


    private Activity context;
    private List<Drawable> pictures;
    private List<String> names;

    public MyListAdapter(Activity context, List<Business> business) {
        super(context, R.layout.yelp_list);
        if (business == null){
            Log.i("map size is : ", "0");
            return;
        }
        this.context = context;
        pictures = new LinkedList<>();
        names = new LinkedList<>();
        for (Business b:business){
            pictures.add(b.snippetImage);
            names.add(b.snippetText);
        }
    }

    @Override
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.yelp_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);

        txtTitle.setText(names.get(position));
        imageView.setImageDrawable(pictures.get(position));
        return rowView;

    };
}
