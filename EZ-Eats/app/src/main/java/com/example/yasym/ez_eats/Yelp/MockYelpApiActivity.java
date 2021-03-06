package com.example.yasym.ez_eats.Yelp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.yasym.ez_eats.R;
import com.example.yasym.ez_eats.Yelp.Task.LoadMockYelpApiTask;

public class MockYelpApiActivity extends AppCompatActivity {

    private Yelp api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_yelp_api);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button) findViewById(R.id.buttonGet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (api == null) {
                    return;
                }
                LoadMockYelpApiTask task = new LoadMockYelpApiTask(api, MockYelpApiActivity.this);
                task.execute();
            }
        });
    }

    public void setApi(Yelp api) {
        this.api = api;
    }
}
