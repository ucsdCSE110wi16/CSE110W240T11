package com.example.yasym.ez_eats;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.ListView;


import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Map;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ymno1 on 3/8/2016.
 * Test if the back button on result page correctly return to questsion page.
 *
 */
@RunWith(AndroidJUnit4.class)
public class BackButtonTest {
    static final String correctQuestion = "Are you super hungry?";
    static final String correctTitle = "Is this what you are looking for?";

    @Rule
    public ActivityTestRule<QuestionActivity> rule =
            new ActivityTestRule<>(QuestionActivity.class);

    @Test
    public void backButtonShouldReturnToFirstQuestion() {
        QuestionActivity q = rule.getActivity();
        onView(withId(R.id.random)).perform(click());
        onView(withId(R.id.result_title)).check(matches(withText(correctTitle)));
        onView(withId(R.id.home)).perform(click());
        onView(withId(R.id.questions)).check(matches(withText(correctQuestion)));
    }

}