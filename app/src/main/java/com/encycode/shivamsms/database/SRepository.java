package com.encycode.shivamsms.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SRepository {

    private StocksDao sDao;

    private LiveData<List<Stocks>> allStocks;

    public SRepository(Application application) {
        SDatabase database = SDatabase.getInstance(application);
        sDao = database.dao();
        allStocks = sDao.getAllStocks();
    }

    public void insert(Stocks stocks) {
        new InsertAsyncTask(sDao).execute(stocks);
    }

    public void update(Stocks stocks) {
        new UpdateAsyncTask(sDao).execute(stocks);
    }

    public void delete(Stocks stocks) {
        new DeleteAsyncTask(sDao).execute(stocks);
    }

    public LiveData<List<Stocks>> getAllStocks() {
        return allStocks;
    }

    private static class InsertAsyncTask extends AsyncTask<Stocks, Void, Void> {

        private StocksDao stocksDao;

        private InsertAsyncTask(StocksDao stocksDao) {
            this.stocksDao = stocksDao;
        }

        @Override
        protected Void doInBackground(Stocks... stocks) {
            stocksDao.insert(stocks[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Stocks, Void, Void> {

        private StocksDao stocksDao;

        private UpdateAsyncTask(StocksDao stocksDao) {
            this.stocksDao = stocksDao;
        }

        @Override
        protected Void doInBackground(Stocks... stocks) {
            stocksDao.update(stocks[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Stocks, Void, Void> {

        private StocksDao stocksDao;

        private DeleteAsyncTask(StocksDao stocksDao) {
            this.stocksDao = stocksDao;
        }

        @Override
        protected Void doInBackground(Stocks... stocks) {
            stocksDao.delete(stocks[0]);
            return null;
        }
    }



}
