package com.example.yasym.ez_eats;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class QuestionActivity extends Activity {


    final int RIGHT = 0;//Indicator of swiping right.
    final int LEFT = 1;//swiping left.

    GestureDetector gestureDetector;
    TextView questionBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionsfragment);
        questionBox = (TextView)this.findViewById(R.id.questions);
        gestureDetector = new GestureDetector(this,onGestureListener);
    }


    private GestureDetector.OnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    float x = e2.getX() - e1.getX();
                    if (x > 100) {
                        someAction(RIGHT);
                    } else if (x < -100) {
                        someAction(LEFT);
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
    public void someAction(int action) {
        switch (action) {
            case RIGHT:
                questionBox.setText("swiped right!");
                questionBox.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
                //TODO
                break;
            case LEFT:
                questionBox.setText("swiped left!");
                questionBox.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
                //TODO
                break;
        }
    }
}
