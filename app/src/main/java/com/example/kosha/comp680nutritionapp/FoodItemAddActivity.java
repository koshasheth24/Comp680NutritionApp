package com.example.kosha.comp680nutritionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FoodItemAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_add);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatDone:
                // Navigate to MainActivity
                Intent intentRegister = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.appCompatButtonCalculate:
                //Todo: Add logic to calculate on input
                break;
        }
    }
}
