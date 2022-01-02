package com.encycode.shivamsms.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SViewModel extends AndroidViewModel {

    private SRepository repository;
    private LiveData<List<Stocks>> allStocks;

    public SViewModel(@NonNull Application application) {
        super(application);
        repository = new SRepository(application);
        allStocks = repository.getAllStocks();
    }

    public void insert(Stocks stocks) {

        repository.insert(stocks);
    }

    public void update(Stocks stocks) {
        repository.update(stocks);
    }

    public void delete(Stocks stocks) {
        repository.delete(stocks);
    }

    public LiveData<List<Stocks>> getAllStocks() {
        return allStocks;
    }
}
