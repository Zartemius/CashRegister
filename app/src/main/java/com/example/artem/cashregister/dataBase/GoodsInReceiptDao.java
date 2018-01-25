package com.example.artem.cashregister.dataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GoodsInReceiptDao {

    @Query("SELECT * FROM GoodsInReceipt")
    LiveData<List<GoodsInReceipt>>getAllItemsInReceipt();

    @Query("SELECT * FROM GoodsInReceipt where productName LIKE :productName")
    GoodsInReceipt findByName(String productName);

    @Query("DELETE FROM GoodsInReceipt")
    public void clearListOfGoodsInReceipt();

    @Insert
    public void addProduct(GoodsInReceipt goodsInReceipt);

    @Delete
    public void deleteProduct (GoodsInReceipt goodsInReceipt);
}
