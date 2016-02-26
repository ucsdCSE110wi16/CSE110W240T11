package com.example.yasym.ez_eats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yasym.ez_eats.Yelp.Task.CurrentLocation;
import com.example.yasym.ez_eats.Yelp.Task.exampleLoadBusinessEntryTask;
import com.example.yasym.ez_eats.Yelp.Yelp;

public class MainActivity extends Activity implements View.OnClickListener {

    ImageButton btn_start;
    TextView status;
    public static CurrentLocation lastKnownPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        btn_start = (ImageButton) this.findViewById(R.id.startButton);
        btn_start.setOnClickListener(this);
        status = (TextView) this.findViewById(R.id.status);
        lastKnownPlace = new CurrentLocation(this);

        // Tests
        Button changeText = (Button) findViewById(R.id.testButton);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new exampleLoadBusinessEntryTask(MainActivity.this).execute();
            }
        });
        // Tests end
    }

    public void startButtonClicked() {
        startActivity(new Intent("android.intent.action.QUESTION"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startButton:
                startButtonClicked();
                break;
            default:
                break;
        }
    }

}
