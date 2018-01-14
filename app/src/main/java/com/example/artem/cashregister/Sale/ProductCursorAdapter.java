package com.example.artem.cashregister.Sale;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artem.cashregister.R;

public class ProductCursorAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private SearchView searchView;

    public ProductCursorAdapter(Context context, Cursor cursor, SearchView sv){
        super(context,cursor,false);
        mContext = context;
        searchView = sv;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.item_of_search_suggestion_view_holder,parent,false);
        return v;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        String product  = cursor.getString(cursor.getColumnIndexOrThrow("product"));
        String code = cursor.getString(cursor.getColumnIndexOrThrow("code"));
        String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));

        TextView productName = (TextView) view.findViewById(R.id.item_of_search_suggestions_view_holder__product_name);
        productName.setText(product);

        TextView productPrice = (TextView) view.findViewById(R.id.item_of_search_suggestions_view_holder__product_price);
        productPrice.setText(code);

        TextView productCode = (TextView) view.findViewById(R.id.item_of_search_suggestions_view_holder__product_code);
        productCode.setText(price);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Ping",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}
