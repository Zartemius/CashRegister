package com.example.artem.cashregister.baseOfProducts;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.artem.cashregister.dataBase.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>
implements ProductsModel.Listener {

        List<Product> products = new ArrayList<>();
        private String name;
        private String price;
        private String code;

        public ProductAdapter(List<Product> products){
                this.products = products;
        }

        @Override
        public ProductViewHolder onCreateViewHolder (ViewGroup parent, int ViewType){
                return ProductViewHolder.create(parent.getContext(),parent);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position){
                name = products.get(position).getProductName();
                price = products.get(position).getPrice();
                code = products.get(position).getCode();
                holder.setProduct(name,code,price);
        }

        @Override
        public int getItemCount() {
        return products.size();
        }

        @Override
        public void onChanged() {
        notifyDataSetChanged();
        }
}
