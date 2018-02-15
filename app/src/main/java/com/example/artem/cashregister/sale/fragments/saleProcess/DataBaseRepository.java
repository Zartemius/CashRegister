package com.example.artem.cashregister.sale.fragments.saleProcess;

import android.content.Context;
import android.os.AsyncTask;
import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.GoodsInReceipt;
import com.example.artem.cashregister.dataBase.GoodsInReceiptDao;
import com.example.artem.cashregister.dataBase.Product;
import com.example.artem.cashregister.dataBase.ProductDao;
import java.util.List;

public class DataBaseRepository  {
    private static AppDataBase appDataBase;
    private ProductDao productDao;
    private GoodsInReceiptDao goodsInReceiptDao;

    public List<Product> getProductsInfo(Context context, String productName){
        appDataBase = AppDataBase.getDatabase(context);

        if(productDao == null){
            productDao = DataBaseRepository.appDataBase.productDao();
        }
        return productDao.findByName(productName+"%");
    }

    public void addItemInReceiptDataBase(Context context, Product product){
        appDataBase = AppDataBase.getDatabase(context);

        String productName = product.getProductName();
        String productPrice = product.getPrice();

        GoodsInReceipt goodsInReceipt = new GoodsInReceipt(productName,productPrice);

        new addAsyncTask(appDataBase).execute(goodsInReceipt);
    }

    private static class addAsyncTask extends AsyncTask<GoodsInReceipt,Void,Void> {
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

