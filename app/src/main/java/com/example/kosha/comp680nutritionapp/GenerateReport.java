package com.example.kosha.comp680nutritionapp;

import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GenerateReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        createPdf();
    }

    private void createPdf() {

    }

}
