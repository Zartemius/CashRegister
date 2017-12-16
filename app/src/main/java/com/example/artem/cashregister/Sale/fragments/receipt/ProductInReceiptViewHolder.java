package com.example.artem.cashregister.Sale.fragments.receipt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.artem.cashregister.R;

public class ProductInReceiptViewHolder extends RecyclerView.ViewHolder {

    private final TextView name;
    private final TextView price;

    public static ProductInReceiptViewHolder create(Context context, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.product_in_receipt_view_holder, parent, false);
        ProductInReceiptViewHolder holder = new ProductInReceiptViewHolder(view);
        return holder;
    }

    private  ProductInReceiptViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.product_in_receipt_view_holder__product_name);
        price = itemView.findViewById(R.id.product_in_receipt_view_holder__product_price);
    }

    public void setProductInReceipt(ProductInReceipt productInReceipt){
        this.name.setText(productInReceipt.name);
        this.price.setText(productInReceipt.price);
    }
}
