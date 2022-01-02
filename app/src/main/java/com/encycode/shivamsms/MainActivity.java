package com.encycode.shivamsms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.encycode.shivamsms.adapters.MainSizeAdapter;
import com.encycode.shivamsms.database.SViewModel;
import com.encycode.shivamsms.database.Stocks;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SViewModel viewModel;
    RecyclerView recyclerView;
    List<Stocks> stocksList;
    Button getStocks,getInvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        stocksList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        getStocks = findViewById(R.id.getStocks);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MainSizeAdapter adapter = new MainSizeAdapter(this, getApplication());


        viewModel = new SViewModel(getApplication());

        getStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getAllStocks().observe(MainActivity.this, new Observer<List<Stocks>>() {
                    @Override
                    public void onChanged(List<Stocks> stocks) {
                        adapter.setAllStocks(stocks);
                        try {
                            createPdf(stocks);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



        recyclerView.setAdapter(adapter);


    }

    public void createPdf(List<Stocks> stocksList) throws FileNotFoundException {

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "Shivam Stocks.pdf");
        OutputStream outputStream = new FileOutputStream(file);


        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        Text heading = new Text("Shivam Sales Current Stock")
                .setBold()
                .setFontSize(30)
                .setUnderline()
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph paragraph = new Paragraph();
        paragraph.add(heading);
        document.add(paragraph);

        float columnWidtth[] = {50f, 200f, 100f};

        Table tables[] = {new Table(columnWidtth), new Table(columnWidtth), new Table(columnWidtth), new Table(columnWidtth), new Table(columnWidtth)};
        String[] sizes = new String[]{"160ml", "200ml", "250ml", "1l", "Mojito(250ml)"};



        for (int i = 0; i < 5; i++) {

            Text heading2 = new Text(sizes[i])
                    .setBold()
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph paragraph2 = new Paragraph();
            paragraph2.add(heading2);

            tables[i].addHeaderCell(new Cell().setBackgroundColor(ColorConstants.BLACK).setFontColor(ColorConstants.WHITE).add(new Paragraph("Sr. No.")));
            tables[i].addHeaderCell(new Cell().setBackgroundColor(ColorConstants.BLACK).setFontColor(ColorConstants.WHITE).add(new Paragraph("Flavours")));
            tables[i].addHeaderCell(new Cell().setBackgroundColor(ColorConstants.BLACK).setFontColor(ColorConstants.WHITE).add(new Paragraph("Quantity")));


            int sr = 1;
            for (int j = 0; j < stocksList.size(); j++) {
                if (stocksList.get(j).getSize().equals(sizes[i])) {
                    tables[i].addCell(String.valueOf(sr));
                    tables[i].addCell(stocksList.get(j).getFlavour());
                    tables[i].addCell(String.valueOf(stocksList.get(j).getQuantity()));
                    sr++;
                }
            }


            document.add(paragraph2);
            document.add(tables[i]);

        }
        document.close();
        Toast.makeText(this, "File Saved to Downloads", Toast.LENGTH_SHORT).show();

    }


}