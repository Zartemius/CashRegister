package com.example.artem.cashregister.dataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;
import com.example.artem.cashregister.dataBase.Product;
import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Query("SELECT * FROM Product")
    List<Product> get();

    @Query("SELECT * FROM Product WHERE product_name LIKE :productName ")
    List<Product> findByName(String productName);

    @Query("SELECT COUNT(*) from Product")
    public int countUsers();

    @Insert
    public void insertAll(Product...products);

    @Delete
    public void delete (Product product);
}
