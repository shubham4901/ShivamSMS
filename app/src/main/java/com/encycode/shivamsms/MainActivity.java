package com.encycode.shivamsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.encycode.shivamsms.adapters.MainSizeAdapter;
import com.encycode.shivamsms.database.SViewModel;
import com.encycode.shivamsms.database.Stocks;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SViewModel viewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MainSizeAdapter adapter = new MainSizeAdapter(this);


        viewModel = new SViewModel(getApplication());
        viewModel.getAllStocks().observe(this, new Observer<List<Stocks>>() {
            @Override
            public void onChanged(List<Stocks> stocks) {
                adapter.setAllStocks(stocks);
            }
        });

        recyclerView.setAdapter(adapter);
    }
}