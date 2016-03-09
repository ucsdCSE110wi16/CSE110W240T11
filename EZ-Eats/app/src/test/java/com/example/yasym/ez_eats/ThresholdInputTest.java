package com.example.yasym.ez_eats;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * Created by ymno1 on 3/9/2016.
 */
public class ThresholdInputTest extends ActivityUnitTestCase<SettingActivity> {

    private Activity a;
    private EditText editText;
    private Button confirm;
    private int testThreshold = 10;
    private int upperLimit = 999;

    public ThresholdInputTest(){
        super(SettingActivity.class);
    }

    @Before
    public void setUpActivity(){
        startActivity(new Intent(getInstrumentation().getTargetContext(),
                SettingActivity.class), null, null);
        a = getActivity();
        editText = (EditText)a.findViewById(R.id.editText);
        confirm = (Button)a.findViewById(R.id.confirm);
    }

    @Test
    public void testThreshold_10_ShouldBe_10(){

        editText.setText("10");
        confirm.performClick();
        assertEquals(QuestionActivity.getThreshold(), testThreshold);
    }

    @Test
    public void testThreshold_0_ShouldBe_999(){

        editText.setText("0");
        confirm.performClick();
        assertEquals(QuestionActivity.getThreshold(), upperLimit);
    }

}
