package com.example.artem.cashregister.sale.fragments.receipt;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.example.artem.cashregister.dataBase.GoodsInReceipt;
import java.util.ArrayList;
import java.util.List;

public class ProductInReceiptAdapter extends RecyclerView.Adapter<ProductInReceiptViewHolder> {

    private List<GoodsInReceipt> listOfGoods = new ArrayList<>();
    private String name;
    private String price;

    public ProductInReceiptAdapter(List<GoodsInReceipt> listOfGoods){
        this.listOfGoods = listOfGoods;
    }

    @Override
    public ProductInReceiptViewHolder onCreateViewHolder (ViewGroup parent, int ViewType){
        return ProductInReceiptViewHolder.create(parent.getContext(),parent);
    }

    @Override
    public void onBindViewHolder(final ProductInReceiptViewHolder holder, int position){
        name = listOfGoods.get(position).getProductName();
        price = listOfGoods.get(position).getPrice();
        holder.setGoodsInReceipt(name,price);
    }

    @Override
    public int getItemCount() {
        return listOfGoods.size();
    }

    public void addItems(List<GoodsInReceipt> listOfGoods){
        this.listOfGoods = listOfGoods;
        notifyDataSetChanged();
    }
}

