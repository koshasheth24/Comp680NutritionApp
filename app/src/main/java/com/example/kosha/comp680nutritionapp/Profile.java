package com.example.kosha.comp680nutritionapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Helpers.InputValidation;
import model.User;
import sql.DatabaseHelper;

public class Profile extends AppCompatActivity {

    private final AppCompatActivity activity = Profile.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout address;
    private TextInputLayout phone;
    private TextInputLayout dob;
    private TextInputLayout age;
    private TextInputLayout weight;
    private TextInputLayout height;
    private TextInputLayout sex;

    private TextInputEditText addressText;
    private TextInputEditText textPhone;
    private TextInputEditText textDob;
    private TextInputEditText textAge;
    private TextInputEditText textWeight;
    private TextInputEditText textHeight;
    private TextInputEditText textSex;

    private AppCompatButton appCompatButtonCompleteProfile;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    private Double max_cal= Double.valueOf(0),max_protien= Double.valueOf(0),max_fiber= Double.valueOf(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initListeners() {

    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        address=(TextInputLayout)findViewById(R.id.address);
        phone=(TextInputLayout)findViewById(R.id.phone);
        dob=(TextInputLayout)findViewById(R.id.dob);
        age=(TextInputLayout)findViewById(R.id.age);
        weight=(TextInputLayout)findViewById(R.id.weight);
        height=(TextInputLayout)findViewById(R.id.height);
        sex=(TextInputLayout)findViewById(R.id.sex);

        addressText=(TextInputEditText) findViewById(R.id.addressText);
        textPhone=(TextInputEditText) findViewById(R.id.textphone);
        textDob=(TextInputEditText) findViewById(R.id.textdob);
        textAge=(TextInputEditText) findViewById(R.id.textage);
        textWeight=(TextInputEditText) findViewById(R.id.textweight);
        textHeight=(TextInputEditText) findViewById(R.id.textheight);
        textSex = (TextInputEditText) findViewById(R.id.textSex);

        appCompatButtonCompleteProfile = (AppCompatButton) findViewById(R.id.appCompatButtonCompleteProfile);

    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    public void onClick(View v) {

                verifyFromHelper();
                //Recieve in array list
                populateUserObject();
                user=databaseHelper.calculateRequiredValues(textAge.toString(),textHeight.toString(),textWeight.toString(),user);
                databaseHelper.saveToUserTable(user);
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);

    }


    private void populateUserObject() {
        user=new User();
        user.setEmail("a@gmail.com");
        user.setAddress(addressText.getEditableText().toString());
        user.setDob(textDob.getEditableText().toString());
        user.setHeight(Double.parseDouble(textHeight.getEditableText().toString()));
        user.setWeight(Double.parseDouble(textWeight.getEditableText().toString()));
        user.setPhone(textPhone.getEditableText().toString());
        user.setSex(textSex.getEditableText().toString());
        user.setAge(Integer.parseInt(textAge.getEditableText().toString()));
        user.setMax_cal(max_cal);
        user.setMax_fiber(max_fiber);
        user.setMax_protien(max_protien);
    }

    private void verifyFromHelper() {

        //Check if filled
        if (!inputValidation.isInputEditTextFilled(addressText, address, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textPhone, phone, getString(R.string.error_message_phone))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textDob, dob, getString(R.string.error_message_dob))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textAge, age, getString(R.string.error_message_age))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textWeight, weight, getString(R.string.error_message_weight))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textHeight, height, getString(R.string.error_message_height))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textSex, sex, getString(R.string.error_message_sex))) {
            return;
        }
        // check format
        if(!inputValidation.isInputEditTextPhoneNumber(textPhone,phone,getString(R.string.error_message_weight))){
           return;
        }
        if(!inputValidation.isInputEditTextSex(textSex,sex,getString(R.string.error_message_weight))){
            return;
        }

        //ToDo : add date format check

    }




}
