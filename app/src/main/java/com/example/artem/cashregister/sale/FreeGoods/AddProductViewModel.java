package com.example.artem.cashregister.sale.FreeGoods;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.GoodsInReceipt;

public class AddProductViewModel extends AndroidViewModel {

    private AppDataBase appDataBase;

    public AddProductViewModel(Application application){
        super(application);

        appDataBase = AppDataBase.getDatabase(this.getApplication());
    }

    public void addProduct(final GoodsInReceipt goodsInReceipt){
        new addAsyncTask(appDataBase).execute(goodsInReceipt);
    }

    private static class addAsyncTask extends AsyncTask<GoodsInReceipt,Void,Void>{

        private AppDataBase db;

        addAsyncTask(AppDataBase appDataBase){
            db = appDataBase;
        }

        @Override
        protected Void doInBackground(final GoodsInReceipt... goodsInReceipt) {
            db.goodsInReceiptDao().addProduct(goodsInReceipt[0]);
            return null;
        }
    }
}
