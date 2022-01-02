package com.encycode.shivamsms.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Stocks.class, version = 1)
public abstract class SDatabase extends RoomDatabase {

    private static SDatabase instance;

    public abstract StocksDao dao();

    public static synchronized SDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SDatabase.class, "stocks_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private StocksDao dao;

        private PopulateDbAsyncTask(SDatabase db) {
            dao = db.dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            dao.insert(new Stocks("Anaar","160ml",0));
            dao.insert(new Stocks("Litchi","160ml",0));
            dao.insert(new Stocks("Kiwi","160ml",0));
            dao.insert(new Stocks("Mixed Fruit","160ml",0));
            dao.insert(new Stocks("Berries","160ml",0));
            dao.insert(new Stocks("Guava","160ml",0));
            dao.insert(new Stocks("Mango","160ml",0));
            dao.insert(new Stocks("Orange","160ml",0));
            dao.insert(new Stocks("Apple","160ml",0));
            dao.insert(new Stocks("Anaar","200ml",0));
            dao.insert(new Stocks("Litchi","200ml",0));
            dao.insert(new Stocks("Kiwi","200ml",0));
            dao.insert(new Stocks("Mixed Fruit","200ml",0));
            dao.insert(new Stocks("Berries","200ml",0));
            dao.insert(new Stocks("Guava","200ml",0));
            dao.insert(new Stocks("Mango","200ml",0));
            dao.insert(new Stocks("Orange","200ml",0));
            dao.insert(new Stocks("Apple","200ml",0));
            dao.insert(new Stocks("Anaar","250ml",0));
            dao.insert(new Stocks("Litchi","250ml",0));
            dao.insert(new Stocks("Kiwi","250ml",0));
            dao.insert(new Stocks("Mixed Fruit","250ml",0));
            dao.insert(new Stocks("Berries","250ml",0));
            dao.insert(new Stocks("Guava","250ml",0));
            dao.insert(new Stocks("Mango","250ml",0));
            dao.insert(new Stocks("Orange","250ml",0));
            dao.insert(new Stocks("Apple","250ml",0));
            dao.insert(new Stocks("Anaar","1l",0));
            dao.insert(new Stocks("Litchi","1l",0));
            dao.insert(new Stocks("Kiwi","1l",0));
            dao.insert(new Stocks("Mixed Fruit","1l",0));
            dao.insert(new Stocks("Berries","1l",0));
            dao.insert(new Stocks("Guava","1l",0));
            dao.insert(new Stocks("Mango","1l",0));
            dao.insert(new Stocks("Orange","1l",0));
            dao.insert(new Stocks("Apple","1l",0));
            dao.insert(new Stocks("Blood Orange","Mojito(250ml)",0));
            dao.insert(new Stocks("Ginger","Mojito(250ml)",0));
            dao.insert(new Stocks("Mint","Mojito(250ml)",0));
            dao.insert(new Stocks("Kiwi","Mojito(250ml)",0));
            dao.insert(new Stocks("Lime","1l",0));
            dao.insert(new Stocks("Jamun","1l",0));
            dao.insert(new Stocks("Mosambi","1l",0));
            dao.insert(new Stocks("Pineapple","1l",0));
            dao.insert(new Stocks("Lime","160ml",0));
            dao.insert(new Stocks("Jamun","160ml",0));
            dao.insert(new Stocks("Mosambi","160ml",0));
            dao.insert(new Stocks("Pineapple","160ml",0));
            dao.insert(new Stocks("Lime","200ml",0));
            dao.insert(new Stocks("Jamun","200ml",0));
            dao.insert(new Stocks("Mosambi","200ml",0));
            dao.insert(new Stocks("Pineapple","200ml",0));
            dao.insert(new Stocks("Coconut","200ml",0));
            dao.insert(new Stocks("Lime","250ml",0));
            dao.insert(new Stocks("Jamun","250ml",0));
            dao.insert(new Stocks("Mosambi","250ml",0));
            dao.insert(new Stocks("Pineapple","250ml",0));


            return null;
        }
    }

}
