package com.example.artem.cashregister.sale.fragments.saleProcess;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.dataBase.Product;

import java.util.ArrayList;
import java.util.List;


public class SearchedProductListAdapter extends ArrayAdapter {

    private List<Product> dataList;
    private Context mContext;
    private int itemLayout;
    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    private SearchedProductListAdapter.ListFilter listFilter = new SearchedProductListAdapter.ListFilter();

    public SearchedProductListAdapter(Context context, int resource, List<Product> productDataList){
        super(context,resource,productDataList);
        dataList = productDataList;
        mContext = context;
        itemLayout = resource;
    }

    @Override
    public int getCount() {
        return dataList.size();

    }

    @Nullable
    @Override
    public Product getItem(int position) {
        return dataList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if(view == null){
                view = LayoutInflater.from(parent.getContext())
                        .inflate(itemLayout,parent,false);
        }

        TextView productName = (TextView) view.findViewById(R.id.search_item_view_holder__product_name);
        productName.setText(getItem(position).getProductName());

        TextView productPrice = (TextView) view.findViewById(R.id.search_item_view_holder__product_price);
        productPrice.setText(getItem(position).getPrice());

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter{
       final private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = new ArrayList<String>();
                    results.count = 0;
                }
            } else {
                final String searchToLowerCase = prefix.toString().toLowerCase();


                List<Product> matchValues = dataBaseRepository.getProductsInfo(mContext, searchToLowerCase);

                results.values = matchValues;
                results.count = matchValues.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {

            if(results.values != null){
                dataList = (ArrayList<Product>)results.values;
            }
            else{
                dataList = null;
            }

            if (results.count > 0){
                notifyDataSetChanged();
            }
            else {
                notifyDataSetInvalidated();
            }
        }
    }
}
