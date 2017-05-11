package com.example.kosha.comp680nutritionapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GoldenPath1 {

    @Rule
    public ActivityTestRule<Login> mActivityTestRule = new ActivityTestRule<>(Login.class);

    @Test
    public void goldenPath1() {
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.textInputEditTextEmail), isDisplayed()));
        textInputEditText.perform(replaceText("abcd@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.textInputEditTextPassword), isDisplayed()));
        textInputEditText2.perform(replaceText("abcd"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.appCompatButtonLogin), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Profile"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.appCompatButtonRecal), withText("Recalculate"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.appCompatButtonRecal), withText("Recalculate"), isDisplayed()));
        appCompatButton3.perform(click());

        pressBack();

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Today's Count"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

    }

}
