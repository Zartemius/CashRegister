package com.example.artem.cashregister.dataBase;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "product")
public class Product {

    public Product(String productName, String code, String price){
        this.productName = productName;
        this.code= code;
        this.price = price;
    }

    @PrimaryKey(autoGenerate = true)
    private int pid;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "code")
    private String code;

    @ColumnInfo(name = "price")
    private String price;

    public int getPid(){
        return pid;
    }

    public void setPid(int pid){
        this.pid = pid;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

}
