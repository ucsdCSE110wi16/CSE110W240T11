package com.example.yasym.ez_eats;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ymno1 on 3/8/2016.
 *
 */
@RunWith(AndroidJUnit4.class)
public class StartOverButtonTest {
    static final String correctQuestion = "Are you super hungry?";

    @Rule
    public ActivityTestRule<QuestionActivity> rule =
            new ActivityTestRule<>(QuestionActivity.class);

    @Test
    public void startoverShouldGoToFirstQuestion() {
        onView(withId(R.id.restart)).perform(click());

        onView(withId(R.id.questions)).check(matches(withText(correctQuestion)));
    }

}
