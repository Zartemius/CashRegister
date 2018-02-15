package com.example.artem.cashregister.sale.fragments.saleProcess.platesOfFastAccess;

public class Plate{
    private int picture;
    private final String nameOfProduct;
    private double price;

    public Plate(int picture, String nameOfProduct, double price){
        this.picture = picture;
        this.nameOfProduct = nameOfProduct;
        this.price = price;
    }

    public int getImageRecource(){
        return picture;
    }

    public String getNameOfProduct(){
        return nameOfProduct;
    }

    public double getPriceOfProduct(){
        return price;
    }
}
