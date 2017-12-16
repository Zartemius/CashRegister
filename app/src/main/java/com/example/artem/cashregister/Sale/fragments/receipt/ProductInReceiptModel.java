package com.example.artem.cashregister.Sale.fragments.receipt;

import java.util.ArrayList;
import java.util.List;

public class ProductInReceiptModel {
    public interface Listener {
        void onChanged();
    }

    Double totalAmount = 0.00;

    private final List<ProductInReceipt> mProductsInReceiptList = new ArrayList<>();
    private final List<Listener> mListeners = new ArrayList<>();

    public void addListener(Listener listener){mListeners.add(listener);}

    public void addProduct(ProductInReceipt productInReceipt){
     mProductsInReceiptList.add(productInReceipt);
        for(Listener l:mListeners){
            l.onChanged();
        }
    }

    public int count(){return mProductsInReceiptList.size();}

    public ProductInReceipt getProductInReceipt(int position){return mProductsInReceiptList.get(position);}


    public void clearListOfProducts(){
        mProductsInReceiptList.clear();

    }

    public double gainSumOfPurcases(){

        for (int i = 0 ; i < mProductsInReceiptList.size(); i++){

            ProductInReceipt productInReceipt = mProductsInReceiptList.get(i);
            String price = productInReceipt.getPrice();
            Double parsedPrice = Double.parseDouble(price);
            totalAmount += parsedPrice;
        }
        return totalAmount;
    }
}