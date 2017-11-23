package com.example.artem.cashregister.data;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>
implements ProductsModel.Listener{

        private final ProductsModel model;

        public ProductAdapter(ProductsModel model){
            this.model = model;
            model.addListener(this);
        }

        @Override
        public ProductViewHolder onCreateViewHolder (ViewGroup parent, int ViewType){
            return ProductViewHolder.create(parent.getContext(),parent);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = model.getProduct(position);
        holder.setProduct(product);
        }

        @Override
        public int getItemCount() {
        return model.count();
        }

        @Override
        public void onChanged() {
        notifyDataSetChanged();
        }
}
