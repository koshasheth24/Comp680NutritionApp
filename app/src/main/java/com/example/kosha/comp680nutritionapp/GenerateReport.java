package com.example.kosha.comp680nutritionapp;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import model.User;
import model.UserCalorieCount;
import sql.DatabaseHelper;

public class GenerateReport extends AppCompatActivity {
    String email;
    DatabaseHelper databaseHelper=new DatabaseHelper();
    int id;
    ArrayList<UserCalorieCount> userCalorieCountArrayList;
    User user=new User();
    private NestedScrollView nestedScrollView;
    private TextView name;
    private TableLayout userReportTable;
    LinearLayoutCompat linearLayout;
    AppCompatButton exportToPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        email = getIntent().getStringExtra("EMAIL");
        id=databaseHelper.getId(email);
        user=databaseHelper.fetchUserDetailsForPDF(id);
        userCalorieCountArrayList=databaseHelper.fetchCurrValuesForReport(id);

        initViews();
        displayResult();
    }

    private void displayResult() {
        name.setText("Name: "+ user.getName());
        for(int i=0;i<userCalorieCountArrayList.size();i++){

            UserCalorieCount ucc=userCalorieCountArrayList.get(i);
            TextView tv=new TextView(this);
            tv.setText("\nDate: "+ucc.getDate()+"\n");
            tv.append("Max Cal: "+user.getMax_cal()+"\n");
            tv.append("Consumed Cal: "+ucc.getTotal_cal()+"\n");
            tv.append("Max Protein: "+user.getMax_protien()+"\n");
            tv.append("Consumed Protein: "+ucc.getTotal_protien()+"\n");
            tv.append("Consumed Fiber: "+user.getMax_fiber()+"\n");
            tv.append("Consumed Fiber: "+ucc.getTotal_fiber()+"\n");
            linearLayout.addView(tv);
        }
    }

    private void initViews() {
        nestedScrollView=(NestedScrollView) findViewById(R.id.nestedScrollView);
        name=(TextView) findViewById(R.id.name);
        linearLayout=(LinearLayoutCompat)findViewById(R.id.linearLayout);
        exportToPDF = (AppCompatButton) findViewById(R.id.exportToPdf);

    }

    private void createPdf(String filename) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            PdfPTable table = createFirstTable();
            table.setWidthPercentage(75);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void onClick(View V){

        //createFirstTable();
    }

    private PdfPTable createFirstTable() {

        PdfPTable table = new PdfPTable(7);

        return table;

    }


}
