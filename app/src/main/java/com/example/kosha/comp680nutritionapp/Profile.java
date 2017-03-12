package com.example.kosha.comp680nutritionapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import Helpers.InputValidation;
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
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);

    }

    private void verifyFromHelper() {
    //ToDo: Validations to check fields on profile
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

    }




}
