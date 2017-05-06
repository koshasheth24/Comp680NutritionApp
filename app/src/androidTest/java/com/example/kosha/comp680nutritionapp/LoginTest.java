package com.example.kosha.comp680nutritionapp;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Kosha on 5/6/2017.
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    private String login,passwd;

    @Rule
    public ActivityTestRule<Login> mActivityRule = new ActivityTestRule<>(
            Login.class);

    @Before
    public void initValidString() {
        login = "abcd@gmail.com";
        passwd="abcd";
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.textInputEditTextEmail))
                .perform(typeText(login), closeSoftKeyboard());
        onView(withId(R.id.textInputEditTextPassword)).perform(click());
        onView(withId(R.id.appCompatButtonLogin)).perform(click());
    }
}