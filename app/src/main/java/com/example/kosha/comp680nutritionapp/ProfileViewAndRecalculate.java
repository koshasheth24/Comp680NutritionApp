package com.example.kosha.comp680nutritionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import model.User;
import sql.DatabaseHelper;

public class ProfileViewAndRecalculate extends AppCompatActivity {
    String idStr;
    DatabaseHelper databaseHelper;
    User user;
    AppCompatButton appCompatButtonRecal;
    TableLayout UserDetails;
    EditText height,weight,age;
    TextView name, email,address,phone,sex, maxCal,maxPro,maxFiber;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view_and_recalculate);
        idStr=getIntent().getStringExtra("ID");
        initObjects();
        id=databaseHelper.getId(idStr);
        user=databaseHelper.fetchUserDetails(id);
        initViews();
        initListeners();
        setFields(user);

    }

    private void initObjects() {
        user=new User();
        databaseHelper=new DatabaseHelper();
    }

    private void initListeners() {

    }

    private void initViews() {
        UserDetails=(TableLayout)findViewById(R.id.UserDetails);
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.email);
        address=(TextView)findViewById(R.id.address);
        phone=(TextView)findViewById(R.id.phone);
        sex=(TextView)findViewById(R.id.sex);
        maxCal=(TextView)findViewById(R.id.calories);
        maxPro=(TextView)findViewById(R.id.protein);
        maxFiber=(TextView)findViewById(R.id.fiber);
        height=(EditText)findViewById(R.id.height);
        weight=(EditText)findViewById(R.id.weight);
        age=(EditText)findViewById(R.id.Age);
        appCompatButtonRecal = (AppCompatButton) findViewById(R.id.appCompatButtonRecal);
    }

    private void setFields(User user) {
        name.setText(user.getName());
        email.setText(idStr);
        address.setText(user.getAddress());
        phone.setText(user.getPhone());
        sex.setText(user.getSex());
        maxCal.setText(String.valueOf(user.getMax_cal()));
        maxPro.setText(String.valueOf(user.getMax_protien()));
        maxFiber.setText(String.valueOf(user.getMax_fiber()));
        age.setText(String.valueOf(user.getAge()));
        weight.setText(String.valueOf(user.getWeight()));
        height.setText(String.valueOf(user.getHeight()));

    }
    public void onClick(View v) {
        if(v.getId()==R.id.appCompatButtonRecal){
            user.setHeight(Double.valueOf(height.getEditableText().toString().trim()));
            user.setWeight(Double.valueOf(weight.getEditableText().toString().trim()));
            user.setAge(Integer.parseInt(age.getEditableText().toString().trim()));
            user.setId(id);
            user=databaseHelper.calculateRequiredValues(user);
            databaseHelper.saveToUserTable(user);
            setFields(user);

        }
    }
}
