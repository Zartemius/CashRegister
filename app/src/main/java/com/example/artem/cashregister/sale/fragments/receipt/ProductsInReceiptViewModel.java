package com.example.artem.cashregister.sale.fragments.receipt;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.GoodsInReceipt;

import java.util.List;


public class ProductsInReceiptViewModel extends AndroidViewModel {

    private final LiveData<List<GoodsInReceipt>> receiptProductsList;

    private AppDataBase appDataBase;

    public ProductsInReceiptViewModel(Application application) {
        super(application);

        appDataBase = AppDataBase.getDatabase(this.getApplication());
        receiptProductsList = appDataBase.goodsInReceiptDao().getAllItemsInReceipt();
    }

    public LiveData<List<GoodsInReceipt>> getReceiptProductsList(){
            return receiptProductsList;
    }

    // delete all data from table

    public void deleteAllGoodsFromReceipt(){
        new addAsyncTask(appDataBase).execute();
    }


    private static class addAsyncTask extends AsyncTask<GoodsInReceipt,Void,Void> {

        private AppDataBase db;

        addAsyncTask(AppDataBase appDataBase) {
            db = appDataBase;
        }

        @Override
        protected Void doInBackground(final GoodsInReceipt... goodsInReceipt) {
            db.goodsInReceiptDao().clearListOfGoodsInReceipt();
            return null;
        }
    }
}
