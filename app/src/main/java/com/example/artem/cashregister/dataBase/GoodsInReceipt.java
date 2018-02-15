package com.example.artem.cashregister.dataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "GoodsInReceipt")
public class GoodsInReceipt {

    @PrimaryKey(autoGenerate = true)
    private int pid;
    private String productName;
    private String code;
    private String price;
    private String quantity;
    private String totalAmount;

    @Ignore
    public GoodsInReceipt(String productName,String code, String price, String quantity){
        this.productName = productName;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }

    public GoodsInReceipt(String productName, String price){
        this.productName = productName;
        this.price = price;
    }

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

    public String getQuantity(){ return quantity;}

    public void setQuantity(String quantity){ this.quantity = quantity; }


    public String getTotalAmount(){return totalAmount;}

    public void setTotalAmount(String totalAmount){this.totalAmount = totalAmount;}
}
