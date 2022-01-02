package com.encycode.shivamsms.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StocksDao {

    @Insert
    void insert(Stocks stocks);

    @Update
    void update(Stocks stocks);

    @Delete
    void delete(Stocks stocks);

    @Query("SELECT * from stocks")
    LiveData<List<Stocks>> getAllStocks();
}
