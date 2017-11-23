package com.example.artem.cashregister.data;

import com.example.artem.cashregister.data.Product;

public class ProductInReceipt {

    public final double quantity;
    Product product;

    public ProductInReceipt(double quantity, Product product){

        this.quantity = quantity;
        this.product = product;
    }
}
