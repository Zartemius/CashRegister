package com.example.artem.cashregister.data;

public class Product {

    public final String name;
    public final String code;
    public final double price;
    public final int image;

    public Product(String name, String code, double price,int image){

        this.name = name;
        this.code = code;
        this.price = price;
        this.image = image;
    }
}
