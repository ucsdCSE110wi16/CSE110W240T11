package com.example.yasym.ez_eats;

import com.example.yasym.ez_eats.Yelp.Task.CurrentLocation;
import com.example.yasym.ez_eats.Yelp.Task.exampleLoadBusinessEntryTask;
import com.example.yasym.ez_eats.Yelp.Yelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    ImageButton btn_start;
    TextView status;
    public static CurrentLocation lastKnownPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        btn_start = (ImageButton)this.findViewById(R.id.startButton);
        btn_start.setOnClickListener(this);
        status = (TextView)this.findViewById(R.id.status);
        lastKnownPlace = new CurrentLocation(this);

        // Tests
        Button changeText = (Button) findViewById(R.id.testButton);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.testTextView);
                ImageView iv = (ImageView) findViewById(R.id.testImageView);
                Yelp api = new Yelp("Pizza");
                new exampleLoadBusinessEntryTask(tv, iv, api).execute();
            }
        });
        // Tests end
    }

    public void startButtonClicked(){
        status.setText("Button Clicked!");
        startActivity(new Intent("android.intent.action.QUESTION"));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.startButton:
                startButtonClicked();
                break;
            default:
                break;
        }
    }

    /**
     * Not sure if this part is necessary.
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
