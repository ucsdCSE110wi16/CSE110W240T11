package com.example.yasym.ez_eats;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private EditText threshold;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_setting);

        confirm = (Button)this.findViewById(R.id.confirm);
        threshold = (EditText)this.findViewById(R.id.editText);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (threshold.getText() != null){
                    QuestionActivity.setThreshold(Integer.parseInt(threshold.getText().toString()));
                    Toast.makeText(SettingActivity.this, "The threshold is: "
                            + QuestionActivity.getThreshold(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
