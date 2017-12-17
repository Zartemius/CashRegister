package com.example.artem.cashregister.Sale.fragments.receipt;

import com.example.artem.cashregister.Sale.fragments.saleProcess.SaleFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductInReceiptModel {
    public interface Listener {
        void onChanged();
    }

    private Double totalAmount = 0.00;

    final List<ProductInReceipt> mProductsInReceiptList = new ArrayList<>();
    private final List<Listener> mListeners = new ArrayList<>();

    public void addListener(Listener listener){mListeners.add(listener);}

    public void addProduct(ProductInReceipt productInReceipt){
     mProductsInReceiptList.add(productInReceipt);
        for(Listener l:mListeners){
            l.onChanged();
        }
        String priceOfProduct = productInReceipt.getPrice();
        this.totalAmount += Double.parseDouble(priceOfProduct);
    }

    public int count(){return mProductsInReceiptList.size();}

    public ProductInReceipt getProductInReceipt(int position){return mProductsInReceiptList.get(position);}
}