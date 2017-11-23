package com.example.artem.cashregister.data;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artem.cashregister.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public static ProductViewHolder create(Context context, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.product_view_holder, parent, false);
        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;

    }

    private final TextView name;
    private final ImageView image;

    private ProductViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.product_view_holder_name);
        image = itemView.findViewById(R.id.product_view_holder_image);
    }

    public void setProduct(Product product){
        name.setText(product.name);
        name.setText(product.image);
    }

}