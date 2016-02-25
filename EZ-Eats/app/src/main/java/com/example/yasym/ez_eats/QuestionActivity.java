package com.example.yasym.ez_eats;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionActivity extends Activity {


    final int RIGHT = 0;//Indicator of swiping right.
    final int LEFT = 1;//swiping left.

    GestureDetector gestureDetector;
    TextView questionBox;
    ListView restaurants;
    ArrayAdapter<String> listAdapter;
    ArrayList<String> resultingRestaurants;
    QuestionTree tree;
    QuestionNode currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionsfragment);

        //Initialize the components.
        questionBox = (TextView)this.findViewById(R.id.questions);
        gestureDetector = new GestureDetector(this.onGestureListener);
        restaurants = (ListView)this.findViewById(R.id.restaurantlist);

        tree = new QuestionTree();
        //Set currentQuestion to root of the tree.
        currentQuestion = tree.getRoot();

        //Display the first question.
        questionBox.setText(currentQuestion.getQuestion());
        //Display all possible restaurant first.
        resultingRestaurants = ResultingCategories.getAll();
        //Initialize the list content adapter.
        listAdapter = new ArrayAdapter<String>(this,
                R.layout.restaurant_list, resultingRestaurants);
        restaurants.setAdapter(listAdapter);
    }

    /**
     * set the gesture listener to take left and right swipe.
     */
    private GestureDetector.OnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    float x = e2.getX() - e1.getX();
                    if (x > 100) {
                        takeAction(RIGHT);
                    } else if (x < -100) {
                        takeAction(LEFT);
                    }
                    return true;
                }
            };

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    /**
     * @param action indicating swipe movement
     * has to be associated with the tree in the future.
     */
    public void takeAction(int action) {
        switch (action) {
            case RIGHT:
                //questionBox.setText("swiped right!");
                //questionBox.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
                //TODO
                updateQuestionAndResult(currentQuestion, RIGHT);
                break;
            case LEFT:
                //questionBox.setText("swiped left!");
                //questionBox.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
                //TODO
                updateQuestionAndResult(currentQuestion, LEFT);
                break;
        }
    }

    void updateQuestionAndResult(QuestionNode node, int direction){
        if (node.isResult()){
            questionBox.setText("This is what you are looking for!");
        }else{
            currentQuestion = currentQuestion.getNextQuestion(direction);
            questionBox.setText(currentQuestion.getQuestion());
            if (currentQuestion.isResult()){
                resultingRestaurants = currentQuestion.getResults();
                listAdapter = new ArrayAdapter<String>(this, R.layout.restaurant_list,
                        resultingRestaurants);
                restaurants.setAdapter(listAdapter);
                questionBox.setText(currentQuestion.getQuestion());
            }
        }
    }
}
