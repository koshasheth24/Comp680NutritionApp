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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.kosha.comp680nutritionapp.R.id.addressText;

/**
 * Created by atilp on 5/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class NewUserProfileSetup {
    private String email;
    private String pwd;
    private String name;
    private String address;
    private String dob;
    private String height;
    private String weight;
    private String age;
    private String sex;
    private String phone;

    @Rule
    public ActivityTestRule<Login> createNew= new ActivityTestRule<Login>(Login.class);
    public ActivityTestRule<Profile> setupNewProfile = new ActivityTestRule<Profile>(Profile.class
    );
    public  ActivityTestRule<RegisterActivity> registerUser = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    @Before
    public void setAllInputTestValues(){
        email="harry@gmail.com";
        pwd="avadacadabra";
        name="potter";
        address="hogwarts";
        dob="1990-02-02";
        height="5.10";
        weight="73.8";
        age="27";
        sex="M";
        phone="9876534455";
    }

    @Test
    public void runNewUserSetupActivities(){
        onView(withId(R.id.textViewLinkRegister)).perform(click());
        onView(withId(R.id.textInputEditTextName)).perform(typeText(name),closeSoftKeyboard());
        onView(withId(R.id.textInputEditTextEmail)).perform(typeText(email),closeSoftKeyboard());
        onView(withId(R.id.textInputEditTextPassword)).perform(typeText(pwd),closeSoftKeyboard());
        onView(withId(R.id.textInputEditTextConfirmPassword)).perform(typeText(pwd),closeSoftKeyboard());
        onView(withId(R.id.appCompatButtonRegister)).perform(click());

        onView(withId(addressText)).perform(typeText(address),closeSoftKeyboard());
        onView(withId(R.id.textphone)).perform(typeText(phone),closeSoftKeyboard());
        onView(withId(R.id.textdob)).perform(typeText(dob),closeSoftKeyboard());
        onView(withId(R.id.textage)).perform(typeText(age),closeSoftKeyboard());
        onView(withId(R.id.textweight)).perform(typeText(weight),closeSoftKeyboard());
        onView(withId(R.id.textheight)).perform(typeText(height),closeSoftKeyboard());
        onView(withId(R.id.textSex)).perform(typeText(sex),closeSoftKeyboard());
        onView(withId(R.id.appCompatButtonCompleteProfile)).perform(click());
    }

}
