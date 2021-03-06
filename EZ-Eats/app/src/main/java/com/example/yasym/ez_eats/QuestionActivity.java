package com.example.yasym.ez_eats;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends Activity {

    /**
     * Font for the title and question.
     * Feel free to change.
     */
    private final String QUESTION_FONT = "font/remachine.ttf";
    private final String TITLE_FONT = "font/remachine.ttf";

    private final int RIGHT = 1;//Indicator of swiping right.
    private final int LEFT = 0;//swiping left.

    /**
     * The components of this window
     */
    private GestureDetector gestureDetector;
    private AdapterView.OnItemClickListener listListener;
    private TextView questionBox;
    private TextView title;
    private ListView restaurants;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> resultingRestaurants;

    private QuizTree tree;

    private QuestionNode currentQuestion;
    private Typeface tf;
    private Button restart;
    private Button setting;
    private Button randomButton;

    private static String resultTerm;
    private static int threshold = 999;

    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionsfragment);

        /**
         * Initialize the components
         */
        questionBox = (TextView)this.findViewById(R.id.questions);
        title = (TextView)this.findViewById(R.id.title);
        gestureDetector = new GestureDetector(this.onGestureListener);
        restaurants = (ListView)this.findViewById(R.id.restaurantlist);
        restart = (Button)this.findViewById(R.id.restart);
        setting = (Button)this.findViewById(R.id.preference);
        randomButton = (Button)this.findViewById(R.id.random);
        tree = new QuizTree();

        /**
         * Set the first question to be displayed
         */
        currentQuestion = tree.getRoot();
        questionBox.setText(currentQuestion.getQuestion());

        /**
         * Set font of title and question.
         */
        tf = Typeface.createFromAsset(getAssets(), QUESTION_FONT);
        questionBox.setTypeface(tf);
        tf = Typeface.createFromAsset(getAssets(), TITLE_FONT);
        title.setTypeface(tf);

        /**
         * Display all restaurants.
         */
        resultingRestaurants = YelpCategories.getAll();

        /**
         * Initialize list adapter.
         */
        listAdapter = new ArrayAdapter<String>(this,
                R.layout.restaurant_list, resultingRestaurants);
        restaurants.setAdapter(listAdapter);

        /**
         * When a list item is clicked, it will change color.
         * And the user will be directed to YELP.
         */
        restaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
                resultTerm = (String)parent.getItemAtPosition(position);
                alert = new AlertDialog.Builder(QuestionActivity.this);
                alert.setMessage("Finding some " + resultTerm + " for you").create();
                alert.show();
                view.setSelected(true);
                startActivity(new Intent("android.intent.action.YELPRESULT"));
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestion = tree.getRoot();
                questionBox.setText(currentQuestion.getQuestion());
                resultingRestaurants = currentQuestion.getResults();
                listAdapter = new ArrayAdapter<String>(QuestionActivity.this,
                        R.layout.restaurant_list, resultingRestaurants);
                restaurants.setAdapter(listAdapter);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.SETTING"));
            }
        });

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int rand = r.nextInt(24);
                ArrayList<String> all = YelpCategories.getAll();
                resultTerm = all.get(rand);
                alert = new AlertDialog.Builder(QuestionActivity.this);
                alert.setMessage("Finding some " + resultTerm + " for you").create();
                alert.show();
                startActivity(new Intent("android.intent.action.YELPRESULT"));
            }
        });
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

    /**
     * On touch event listener for swiping functionality.
     * @param event
     * @return
     */
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
                //questionBox.startAnimation(AnimationUtils.
                // loadAnimation(this, R.anim.slide_in_left));
                //TODO
                updateQuestionAndResult(currentQuestion, RIGHT);
                break;
            case LEFT:
                //TODO
                updateQuestionAndResult(currentQuestion, LEFT);
                break;
        }
    }

    /**
     * Decide which question to go next based current question and
     * swiping direction.
     * @param node current question
     * @param direction left or right swipe
     */
    void updateQuestionAndResult(QuestionNode node, int direction){
        if (node.isResult()){
            /**
             * When you have already reachedan answer.
             */
            questionBox.setText("This is what you are looking for!");
        }else{
            /**
             * When you haven't reached an answer yet, proceed to the next question
             * based on swiping direction.
             */
            currentQuestion = currentQuestion.getNextQuestion(direction);

            /**
             * Animation of question transition.
             * To be refined.
             */
            if (direction == RIGHT){
                questionBox.startAnimation(AnimationUtils.
                        loadAnimation(this, R.anim.slide_out_right));
                questionBox.setText(currentQuestion.getQuestion());
                questionBox.startAnimation(AnimationUtils.
                        loadAnimation(this, R.anim.slide_in_left));
            }else{
                questionBox.startAnimation(AnimationUtils.
                        loadAnimation(this, R.anim.slide_out_left));
                questionBox.setText(currentQuestion.getQuestion());
                questionBox.startAnimation(AnimationUtils.
                        loadAnimation(this, R.anim.slide_in_right));
            }

            if (currentQuestion.isResult()){
                resultingRestaurants = currentQuestion.getResults();
                listAdapter = new ArrayAdapter<String>(this, R.layout.restaurant_list,
                        resultingRestaurants);
                restaurants.setAdapter(listAdapter);
                questionBox.setText(currentQuestion.getQuestion());

            }
        }
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    public static String getTerm(){
        return resultTerm;
    }

    public static void setTerm(String term) { QuestionActivity.resultTerm = term;}

    public static void setThreshold(int threshold){
        QuestionActivity.threshold = threshold;
    }

    public static int getThreshold(){
        return threshold;
    }

    public ArrayList<String> getResultingRestaurants(){ return resultingRestaurants;}
}
