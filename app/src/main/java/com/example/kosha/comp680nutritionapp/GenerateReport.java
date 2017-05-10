package com.example.kosha.comp680nutritionapp;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.User;
import model.UserCalorieCount;
import sql.DatabaseHelper;

public class GenerateReport extends AppCompatActivity {
    String email,fromDate,toDate;
    DatabaseHelper databaseHelper=new DatabaseHelper();
    int id;
    ArrayList<UserCalorieCount> userCalorieCountArrayList;
    User user=new User();
    private NestedScrollView nestedScrollView;
    private TextView name,dateFrom,dateTo;
    LinearLayoutCompat linearLayout;
    AppCompatButton exportToPDF,generateResults;
    TextInputLayout dateFromWid,dateToWid;
    File myFile;
    Date date=new Date();
    String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
    File pdfFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        email = getIntent().getStringExtra("EMAIL");
        id=databaseHelper.getId(email);
        user=databaseHelper.fetchUserDetailsForPDF(id);
        initViews();
        name.setText("Name: "+ user.getName());

    }

    private void displayResult() {

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
        dateFrom=(TextView) findViewById(R.id.dateFrom);
        dateTo=(TextView) findViewById(R.id.dateTo);
        exportToPDF = (AppCompatButton) findViewById(R.id.exportToPdf);
        generateResults = (AppCompatButton) findViewById(R.id.generatePdf);
        dateFromWid=(TextInputLayout) findViewById(R.id.dateFromWid);
        dateToWid=(TextInputLayout)findViewById(R.id.dateToWid);

    }

    private void createPdf() {
        pdfFolder=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"Report");

        if(!pdfFolder.exists()){
            pdfFolder.mkdir();
        }
        try {
        myFile = new File(pdfFolder + timeStamp + ".pdf");
            Document document = new Document();
            PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(myFile));
            document.open();
            document.addTitle("Report");
            PdfPTable table = createFirstTable();
            table.setWidthPercentage(90);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            document.add(table);
            document.close();
            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View V){

        userCalorieCountArrayList=databaseHelper.fetchCurrValuesForReport(id,dateFrom.getText().toString(),dateTo.getText().toString());

        if(V.getId()==R.id.exportToPdf) {
            createPdf();

            Intent intent = new Intent(getApplicationContext(),DisplayReport.class);
            intent.putExtra("FILE_PATH",pdfFolder + timeStamp + ".pdf");
            startActivity(intent);
        }else if(V.getId()==R.id.generatePdf){
            displayResult();
        }
    }

    private PdfPTable createFirstTable() {
        PdfPTable table = new PdfPTable(7);
        table.addCell("Date");
        table.addCell("Maximum Calories");
        table.addCell("Consumed Calories");
        table.addCell("Max. Protein");
        table.addCell("Consumed Protein");
        table.addCell("Max. Fiber");
        table.addCell("Consumed Fiber");

        for(int i=0;i<userCalorieCountArrayList.size();i++){
            UserCalorieCount ucc=userCalorieCountArrayList.get(i);
            table.addCell(String.valueOf(ucc.getDate()));
            table.addCell(String.valueOf(user.getMax_cal()));
            table.addCell(String.valueOf(ucc.getTotal_cal()));
            table.addCell(String.valueOf(user.getMax_protien()));
            table.addCell(String.valueOf(ucc.getTotal_protien()));
            table.addCell(String.valueOf(user.getMax_fiber()));
            table.addCell(String.valueOf(ucc.getTotal_fiber()));

        }
        return table;
    }
}
