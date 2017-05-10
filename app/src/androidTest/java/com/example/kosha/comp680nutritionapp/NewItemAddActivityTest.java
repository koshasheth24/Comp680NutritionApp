package com.example.kosha.comp680nutritionapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by atilp on 5/7/2017.
 */

@RunWith(AndroidJUnit4.class)
public class NewItemAddActivityTest {

private String item;
    private String count;

    @Rule
    public ActivityTestRule<MainActivity> enterFoodItemWindow= new ActivityTestRule<MainActivity>(MainActivity.class);

    public ActivityTestRule<FoodItemAddActivity> newItemTester= new ActivityTestRule
            <FoodItemAddActivity>(FoodItemAddActivity.class);

    @Before
    public void initValidItemString(){
        item="cheese";
        count="3";
    }

    @Test
    public void test_AddNewItem(){
       // String baseCal=onView(withId(R.id.remainingCal)).toString();
        onView(withId(R.id.appCompatButtonMeal)).perform(click());
        onView(withId(R.id.searchView)).perform(click());
        onView(withId(R.id.searchView)).perform(typeText(item), closeSoftKeyboard());
        onView(withId(R.id.AddQuantity)).perform(typeText(count), closeSoftKeyboard());
        onView(withId(R.id.appCompatButtonCalculate));
        onView(withId(R.id.appCompatDone));
    }
}
