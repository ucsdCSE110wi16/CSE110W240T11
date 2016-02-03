package edu.ucsd.cse110_team11.ezeats;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by JAAASON on 2016/2/2.
 */
public class QuestionFragment extends Fragment{
    Button btn_y;
    Button btn_n;
    TextView question;

    Tree myTree = new Tree();
    Node curr;

    Animation in ;
    Animation out;
    AnimationSet as;

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

        in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1000);
        out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(1000);

        as = new AnimationSet(true);
        as.addAnimation(out);
        in.setStartOffset(1000);
        as.addAnimation(in);

        myTree.insert(5);
        myTree.insert(3);
        myTree.insert(4);
        myTree.insert(2);
        myTree.insert(7);
        myTree.insert(8);
        myTree.insert(6);
        curr = myTree.getRoot();

        question = (TextView)rootView.findViewById(R.id.question);
        question.setText("Is the number greater than " + curr.getValue() + "?");


        btn_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (curr.isLeaf()){
                    question.startAnimation(as);
                    question.setText("You have already reached the end of search!");
                }else {
                    curr = curr.getRightChild();
                    if (curr.isLeaf()) {
                        question.startAnimation(as);
                        question.setText("This is what you are looking for : " + curr.getValue());
                    } else {
                        question.startAnimation(as);
                        question.setText("Greater than " + curr.getValue() + "?");
                    }
                }
            }
        });

        btn_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curr.isLeaf()) {
                    question.startAnimation(as);
                    question.setText("You have already reached the end of search!");
                } else {
                    curr = curr.getLeftChild();
                    if (curr.isLeaf()) {
                        question.startAnimation(as);
                        question.setText("This is what you are looking for : " + curr.getValue());
                    } else {
                        question.startAnimation(as);
                        question.setText("Greater than " + curr.getValue() + "?");
                    }
                }
            }
        });


        return rootView;
    }

}
