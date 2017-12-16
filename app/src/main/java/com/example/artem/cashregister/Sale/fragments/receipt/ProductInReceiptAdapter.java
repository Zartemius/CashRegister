package com.example.artem.cashregister.Sale.fragments.receipt;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class ProductInReceiptAdapter extends RecyclerView.Adapter<ProductInReceiptViewHolder>
        implements ProductInReceiptModel.Listener {

    private final ProductInReceiptModel productInReceiptModel;

    public ProductInReceiptAdapter(ProductInReceiptModel productInReceiptModel){
        this.productInReceiptModel = productInReceiptModel;
        productInReceiptModel.addListener(this);
    }

    @Override
    public ProductInReceiptViewHolder onCreateViewHolder (ViewGroup parent, int ViewType){
        return ProductInReceiptViewHolder.create(parent.getContext(),parent);
    }

    @Override
    public void onBindViewHolder(ProductInReceiptViewHolder holder, int position){
        ProductInReceipt productInReceipt = productInReceiptModel.getProductInReceipt(position);
        holder.setProductInReceipt(productInReceipt);
    }

    @Override
    public int getItemCount() {
        return productInReceiptModel.count();
    }

    @Override
    public void onChanged() {
        notifyDataSetChanged();
    }
}
