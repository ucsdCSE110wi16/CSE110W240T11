package com.example.yasym.ez_eats;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.yasym.ez_eats.Yelp.MockYelpApiActivity;
import com.example.yasym.ez_eats.Yelp.Yelp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by simon on 3/7/16.
 */
@RunWith(AndroidJUnit4.class)
public class YelpApiGetTest {
    @Rule
    public ActivityTestRule<MockYelpApiActivity> mockActivityRule =
            new ActivityTestRule<>(MockYelpApiActivity.class);

    @Before
    public void setupApi() {
        MockYelpApiActivity a = mockActivityRule.getActivity();
        Yelp api = new Yelp(a.getApplicationContext());
        api.setTerm("Pizza").setLimit(1);
        a.setApi(api);
    }

    static final String NAME = "Pizza on Pearl";
    static final String RATING = "4.5";
    static final String PHONE = "+1-858-729-0717";
    static final String ADDRESS = "617 Pearl St\nLa Jolla\nLa Jolla, CA 92037";
    static final int RATING_STAR = R.drawable.stars_large_4_5;

    @Test
    public void clickLeaveButtonSaysGoodbye() {
        onView(withId(R.id.buttonGet)).perform(click());

        onView(withId(R.id.rating)).check(matches(withText(RATING)));
        onView(withId(R.id.phone)).check(matches(withText(PHONE)));
        onView(withId(R.id.address)).check(matches(withText(ADDRESS)));
        onView(withId(R.id.name)).check(matches(withText(NAME)));
        onView(withId(R.id.ratingImage)).check(matches(isDisplayed()));
    }
}
