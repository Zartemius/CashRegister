package com.example.artem.cashregister.dataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ProductDao productDao();
}
