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

    private final int UPPER_LIMIT = 999;

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
                    int userInput = Integer.parseInt(threshold.getText().toString());
                    if (userInput < 0 ){
                        Toast.makeText(SettingActivity.this, "Your input should be bigger than 0!\n" +
                                "Please try again", Toast.LENGTH_LONG).show();
                    }else if (userInput == 0){
                        QuestionActivity.setThreshold(UPPER_LIMIT);
                    }else{
                        QuestionActivity.setThreshold(userInput);
                    }
                    Toast.makeText(SettingActivity.this, "The threshold is: "
                            + QuestionActivity.getThreshold(), Toast.LENGTH_SHORT).show();
                    if (QuestionActivity.getThreshold() > 10){
                        Toast.makeText(SettingActivity.this, "Better not to be bigger than 10."
                        , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
