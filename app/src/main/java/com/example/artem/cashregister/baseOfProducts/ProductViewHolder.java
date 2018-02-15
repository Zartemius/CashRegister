package com.example.artem.cashregister.baseOfProducts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import com.example.artem.cashregister.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private final TextView name;
    private final TextView price;

    public static ProductViewHolder create(Context context, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.product_data_base_item_view_holder, parent, false);
        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    private ProductViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.product_data_base_item_view_holder__product_name);
        price = itemView.findViewById(R.id.product_data_base_item_view_holder__product_price);
    }

    public void setProduct(String name, String code, String price){
        this.name.setText(name);
        this.price.setText(price);
    }
}