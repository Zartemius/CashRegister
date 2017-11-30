package com.example.artem.cashregister.data;

public class Product {

    public final String name;
    public final String code;
    public final double price;

    public Product(String name, String code, double price){

        this.name = name;
        this.code = code;
        this.price = price;
    }
}
