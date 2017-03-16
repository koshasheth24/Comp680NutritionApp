package com.example.kosha.comp680nutritionapp;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;


import java.util.ArrayList;

import model.UserCalorieCount;
import sql.DatabaseHelper;

public class FoodItemAddActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private DatabaseHelper databaseHelper=new DatabaseHelper();
    private UserCalorieCount userCalCount;
    int id;
    SearchView simpleSearchView;
    ListView list;
    ListViewAdaptor adapter;
    Float cal,protien,fiber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_add);
        simpleSearchView=(SearchView)findViewById(R.id.searchView);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        simpleSearchView.setSearchableInfo(

                searchManager.getSearchableInfo(new ComponentName(this,FoodItemAddActivity.class)));
        simpleSearchView.setOnQueryTextListener(this);
        ArrayList<String> items=databaseHelper.getResults();

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < items.size(); i++) {

        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdaptor(this, items);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);


       simpleSearchView.setOnQueryTextListener(this);

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
        String text =newText;
        adapter.filter(text);
        return false;
    }
}
