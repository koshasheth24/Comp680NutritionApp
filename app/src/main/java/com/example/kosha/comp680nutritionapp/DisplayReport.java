package com.example.kosha.comp680nutritionapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DisplayReport extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_report);
        intent = getIntent();
        PdfRenderer renderer = null;
        try {
            renderer = new PdfRenderer(getSeekableFileDescriptor());
            // let us just render all pages
            final int pageCount = renderer.getPageCount();
            for (int i = 0; i < pageCount; i++) {
                PdfRenderer.Page page = renderer.openPage(i);

                // say we render for showing on the screen
                Bitmap mBitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(),
                        Bitmap.Config.ARGB_8888);
                page.render(mBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                // do stuff with the bitmap
                ImageView img=(ImageView) findViewById(R.id.imageView);
                img.setImageBitmap(mBitmap);
                // close the page
                page.close();
            }

            // close the renderer
            renderer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        //pdfView=(PDFView) findViewById(R.id.pdfView);
        //pdfView.fromAsset("pres.pdf").load();

    }

    private ParcelFileDescriptor getSeekableFileDescriptor() {
        ParcelFileDescriptor ps = null;
        try {
            ps=ParcelFileDescriptor.open(new File(intent.getStringExtra("FILE_PATH")),ParcelFileDescriptor.MODE_READ_ONLY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  ps;
    }
}
