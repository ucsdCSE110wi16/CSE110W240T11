package edu.ucsd.cse110_team11.ezeats;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by JAAASON on 2016/2/2.
 */
public class QuestionFragment extends Fragment{
    Button btn_y;
    Button btn_n;
    TextView question;

    public static QuestionFragment newInstance(){
        QuestionFragment frag = new QuestionFragment();
        return frag;
    }

    public QuestionFragment(){
        //intentionally left blank.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.question_fragment, container, false);
        btn_y = (Button)rootView.findViewById(R.id.btn_y);
        btn_n = (Button)rootView.findViewById(R.id.btn_n);
        question = (TextView)rootView.findViewById(R.id.question);

        btn_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setText("user answered yes!");
            }
        });

        btn_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setText("user answered no!");
            }
        });
        return rootView;
    }

}
