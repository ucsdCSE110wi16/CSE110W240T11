package com.example.yasym.ez_eats;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    ImageButton btn_start;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        btn_start = (ImageButton)this.findViewById(R.id.startButton);
        btn_start.setOnClickListener(this);
        status = (TextView)this.findViewById(R.id.status);
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
