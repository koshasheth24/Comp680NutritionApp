package com.example.kosha.comp680nutritionapp;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;


import model.UserCalorieCount;
import sql.DatabaseHelper;

public class FoodItemAddActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private DatabaseHelper databaseHelper;
    private UserCalorieCount userCalCount;
    int id;
    String idstr;
    Float cal,protien,fiber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_add);
        idstr=getIntent().getStringExtra("ID");
        id=Integer.parseInt(idstr);
        SearchView simpleSearchView = (SearchView) findViewById(R.id.serachView); // inititate a search view
        CharSequence query = simpleSearchView.getQuery(); // get the query string currently in the text field

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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
