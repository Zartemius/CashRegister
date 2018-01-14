package com.example.artem.cashregister.Sale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.dataBase.Product;

import java.util.List;

public class ProductsListViewAdapter extends ArrayAdapter {

    private List<Product> dataList;
    private Context mContext;
    private int searchResultItemLayout;

    public ProductsListViewAdapter(Context context, int resource, List<Product> storeSourceDataList){
        super(context, resource, storeSourceDataList);
        dataList = storeSourceDataList;
        mContext = context;
        searchResultItemLayout = resource;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Product getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(parent.getContext()).
                    inflate(searchResultItemLayout,parent, false);

        }

        Product pt = getItem(position);

        TextView productName = (TextView) view.findViewById(R.id.item_of_search_suggestions_view_holder__product_name);
        productName.setText(pt.getProductName());

        TextView productPrice = (TextView) view.findViewById(R.id.item_of_search_suggestions_view_holder__product_price);
        productPrice.setText(pt.getPrice());

        TextView productCode = (TextView) view.findViewById(R.id.item_of_search_suggestions_view_holder__product_code);
        productCode.setText(pt.getCode());

        return view;
    }
}