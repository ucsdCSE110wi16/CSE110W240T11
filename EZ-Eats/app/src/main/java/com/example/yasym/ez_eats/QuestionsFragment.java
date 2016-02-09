package com.example.yasym.ez_eats;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuestionsFragment extends Fragment {

    public QuestionsFragment(){

    }

    public static QuestionsFragment newInstance(){
        QuestionsFragment fragment = new QuestionsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        TextView title = (TextView)rootView.findViewById(R.id.title);
        return rootView;
    }
}
