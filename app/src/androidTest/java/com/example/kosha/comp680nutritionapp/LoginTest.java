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
        onView(withId(R.id.textInputEditTextEmail))
                .perform(typeText(login), closeSoftKeyboard());
        onView(withId(R.id.textInputEditTextPassword)).perform(typeText(passwd), closeSoftKeyboard());
        onView(withId(R.id.appCompatButtonLogin)).perform(click());

        /*ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
                                withParent(withId(R.id.searchView)))),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("che"), closeSoftKeyboard());

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.listview),
                                withParent(withId(R.id.textInputLayout))),
                        0),
                        isDisplayed()));
        relativeLayout.perform(click());*/
    }
}