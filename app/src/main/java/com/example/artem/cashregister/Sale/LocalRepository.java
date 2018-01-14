package com.example.artem.cashregister.Sale;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.Product;
import com.example.artem.cashregister.dataBase.ProductDao;

import java.util.List;

public class LocalRepository {
        private static AppDataBase appDataBase;
        private static final Object LOCK = new Object();

        public synchronized static AppDataBase getAppDataBase (Context context){
            if(appDataBase == null){
                synchronized (LOCK){
                    if(appDataBase == null){
                        appDataBase = Room.databaseBuilder(context,AppDataBase.class,"APP DB")
                                .fallbackToDestructiveMigration().addCallback(dbCallBack).build();
                    }
                }
            }
            return appDataBase;
        }

        public ProductDao getProductDao(Context context){
            return getAppDataBase(context).productDao();
        }

        public LiveData<List<Product>>getProductListInfo(Context context, String query){
            return getProductDao(context).getProductsList(query);
        }

        public Cursor getProductsCursor(Context context, String query){
            return getProductDao(context).getProductsCursor(query);
        }

        private static RoomDatabase.Callback dbCallBack = new RoomDatabase.Callback(){
            public void onCreate(SupportSQLiteDatabase db){

            }
            public void onOpen (SupportSQLiteDatabase db){
                db.execSQL("Delete from product");

                ContentValues contentValues = new ContentValues();
                contentValues.put("product_name", "Хлеб");
                contentValues.put("code", "0001");
                contentValues.put("price","25");

                db.insert("product", OnConflictStrategy.IGNORE,contentValues);

                contentValues = new ContentValues();
                contentValues.put("product_name", "Молоко");
                contentValues.put("code", "0002");
                contentValues.put("price","62");

                db.insert("product", OnConflictStrategy.IGNORE,contentValues);

                contentValues = new ContentValues();
                contentValues.put("product_name", "Печенье");
                contentValues.put("code", "0003");
                contentValues.put("price","43");

                db.insert("product", OnConflictStrategy.IGNORE,contentValues);

                contentValues = new ContentValues();
                contentValues.put("product_name", "Макароны");
                contentValues.put("code", "0004");
                contentValues.put("price","50");

                db.insert("product", OnConflictStrategy.IGNORE,contentValues);

                contentValues = new ContentValues();
                contentValues.put("product_name", "Пиво");
                contentValues.put("code", "0005");
                contentValues.put("price","90");

                db.insert("product", OnConflictStrategy.IGNORE,contentValues);

                contentValues = new ContentValues();
                contentValues.put("product_name", "Ананас");
                contentValues.put("code", "0006");
                contentValues.put("price","110");

                contentValues = new ContentValues();
                contentValues.put("product_name", "Шоколад");
                contentValues.put("code", "0007");
                contentValues.put("price","67");

                db.insert("product", OnConflictStrategy.IGNORE,contentValues);


            }
        };
}


