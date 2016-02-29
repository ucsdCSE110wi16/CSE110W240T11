package com.example.yasym.ez_eats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yasym.ez_eats.Yelp.Task.CurrentLocation;
import com.example.yasym.ez_eats.Yelp.Task.exampleLoadBusinessEntryTask;
import com.example.yasym.ez_eats.Yelp.Yelp;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity{

    private final int RIGHT = 0;//Indicator of swiping right.
    private final int LEFT = 1;//swiping left.

    private final int SPLASH_DISPLAY_LENGHT = 3000;
    TextView status;
    public static CurrentLocation lastKnownPlace;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        gestureDetector = new GestureDetector(this.onGestureListener);
        lastKnownPlace = new CurrentLocation(this);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent("android.intent.action.QUESTION"));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };
        timer.schedule(task, SPLASH_DISPLAY_LENGHT);
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

    public void toQuestionActivity() {
        startActivity(new Intent("android.intent.action.QUESTION"));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * @param action indicating swipe movement
     * has to be associated with the tree in the future.
     */
    public void takeAction(int action) {
        switch (action) {
            case RIGHT:
                //TODO
                Toast.makeText(this, "Swipe left to begin!", Toast.LENGTH_LONG).show();
                break;
            case LEFT:
                //TODO
                toQuestionActivity();
                break;
        }
    }

}
