package com.example.kosha.comp680nutritionapp;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import model.UserCalorieCount;
import sql.DatabaseHelper;

public class FoodItemAddActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private UserCalorieCount userCalCount;
    int id;
    Float cal,protien,fiber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_add);
        //ToDo : fetch id from intent
    }

    public void onClick(View v) {
        if(v.getId()==R.id.appCompatButtonCalculate){
            userCalCount=databaseHelper.fetchPreviousValue(id);
            calculateNewValues();
            databaseHelper.updateUserCalorieCountTable(userCalCount);
        }
        Intent intentRegister = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentRegister);

    }

    private void calculateNewValues() {
        userCalCount.setTotal_cal(userCalCount.getTotal_cal()+cal);
        userCalCount.setTotal_fiber(userCalCount.getTotal_fiber()+fiber);
        userCalCount.setTotal_protien(userCalCount.getTotal_protien()+protien);
    }
}
