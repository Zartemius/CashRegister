package com.example.artem.cashregister.Sale;

public class ProductInReceipt {

    public final double quantity;
    Product product;

    public ProductInReceipt(double quantity, Product product){
        this.quantity = quantity;
        this.product = product;
    }
}
