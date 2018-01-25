package com.example.artem.cashregister.sale.fragments.receipt;

import com.example.artem.cashregister.dataBase.Product;

public class ProductInReceipt {

    public final double quantity = 1;
    public final String price;
    public final String name;
    public final String code;

    public ProductInReceipt(Product product){
        this.price = product.getPrice();
        this.name = product.getProductName();
        this.code = product.getCode();
    }

    public String getPrice(){
        return price;
    }
}
