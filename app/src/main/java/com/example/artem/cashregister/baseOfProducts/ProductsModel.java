package com.example.artem.cashregister.baseOfProducts;

import com.example.artem.cashregister.dataBase.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsModel {
    public interface Listener {
        void onChanged();
    }

    private final List<Product> mProductsList = new ArrayList<>();
    private final List<Listener> mListeners = new ArrayList<>();

    public void addListener(Listener listener){mListeners.add(listener);}

    public void addProduct(List<Product> mProductsList){
     //   mProductsList.add();
        for(Listener l:mListeners){
            l.onChanged();
        }
    }

    public int count(){return mProductsList.size();}

    public Product getProduct(int position){return mProductsList.get(position);}
}