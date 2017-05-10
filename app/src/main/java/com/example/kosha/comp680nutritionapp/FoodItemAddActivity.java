package com.example.kosha.comp680nutritionapp;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import model.ItemNutrient;
import model.UserCalorieCount;
import sql.DatabaseHelper;

public class FoodItemAddActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private DatabaseHelper databaseHelper=new DatabaseHelper();
    private UserCalorieCount userCalCount;
    int id;
    SearchView simpleSearchView;
    ListView list;
    ListViewAdaptor adapter;
    String email;
    String selItem;
    ItemNutrient itemNutrient;
    TextInputLayout addQuan;
    TextInputEditText addQuantity;
    TextView calorieValue,proteinValue,fiberValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_add);
        addQuan=(TextInputLayout) findViewById(R.id.quantity);
        addQuantity=(TextInputEditText) findViewById(R.id.AddQuantity);
        calorieValue=(TextView) findViewById(R.id.caloriesValue);
        proteinValue=(TextView)findViewById(R.id.protiensValue);
        fiberValue=(TextView) findViewById(R.id.fiberValue);
        email = getIntent().getStringExtra("EMAIL");
        id=databaseHelper.getId(email);
        simpleSearchView=(SearchView)findViewById(R.id.searchView);
        simpleSearchView.setQueryHint("Search Food itmes");
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        simpleSearchView.setSearchableInfo(

                searchManager.getSearchableInfo(new ComponentName(this,FoodItemAddActivity.class)));

        ArrayList<String> items=databaseHelper.getResults();

        list = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdaptor(this, items);

        list.setAdapter(adapter);
        simpleSearchView.setOnQueryTextListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selItem = (String) list.getItemAtPosition(position);
               // simpleSearchView.setQueryHint(selItem);
                simpleSearchView.setQuery(selItem,false);
                list.setVisibility(View.INVISIBLE);


            }
        });

    }

    public void onClick(View v) {
        itemNutrient = databaseHelper.fetchValuesForItem(selItem.substring(0,selItem.indexOf(":")));
        userCalCount = databaseHelper.fetchPreviousValue(id);
        userCalCount.setId(id);
        if(v.getId()==R.id.appCompatButtonCalculate) {


            calorieValue.setText(String.valueOf(itemNutrient.getCalories()));
            proteinValue.setText(String.valueOf(itemNutrient.getProtiens()));
            fiberValue.setText(String.valueOf(itemNutrient.getFiber()));

        }
        if(v.getId()==R.id.appCompatDone) {
            if (addQuantity.getText().toString() != null) {
                Integer n = Integer.parseInt(addQuantity.getText().toString());
                userCalCount.setTotal_cal(userCalCount.getTotal_cal()+(itemNutrient.getCalories() * n));
                userCalCount.setTotal_fiber(userCalCount.getTotal_fiber()+(itemNutrient.getFiber() * n));
                userCalCount.setTotal_protien(userCalCount.getTotal_protien()+(itemNutrient.getProtiens() * n));
            }else{
                calculateNewValues();
            }
            databaseHelper.updateUserCalorieCountTable(userCalCount);

            Intent intentRegister = new Intent(getApplicationContext(), MainActivity.class);
            intentRegister.putExtra("EMAIL", email);
            startActivity(intentRegister);
        }

    }

    private void calculateNewValues() {
        userCalCount.setTotal_cal(userCalCount.getTotal_cal()+itemNutrient.getCalories());
        userCalCount.setTotal_fiber(userCalCount.getTotal_fiber()+itemNutrient.getFiber());
        userCalCount.setTotal_protien(userCalCount.getTotal_protien()+itemNutrient.getProtiens());
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
