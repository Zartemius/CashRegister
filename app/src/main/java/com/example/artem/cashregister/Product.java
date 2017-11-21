package com.example.artem.cashregister;


import android.widget.ImageView;

public class Product {

    public final String name;
    public final int quantity;
    public final String code;
    public final double price;

    public Product(String name, int quantity, String code, double price){

        this.name = name;
        this.quantity = quantity;
        this.code = code;
        this.price = price;
    }
}
