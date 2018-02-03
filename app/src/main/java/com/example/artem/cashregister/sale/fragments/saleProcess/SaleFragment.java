package com.example.artem.cashregister.sale.fragments.saleProcess;
import android.arch.lifecycle.LifecycleFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.dataBase.Product;
import com.example.artem.cashregister.sale.FreeGoods.FreeGoods;

import java.util.ArrayList;
import java.util.List;

public class SaleFragment extends LifecycleFragment {

    public interface SaleFragmentListener{

    }


    private SaleFragmentListener mListener;
    private Double totalAmount = 0.00;
    SearchedProductListAdapter adapter;
    private DataBaseRepository dataBaseRepository = new DataBaseRepository();
    AutoCompleteTextView mAutoCompleteTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale, container,false);


        ImageView addProduct = view.findViewById(R.id.fragment_sale__button_add_product_in_list);
        addProduct.setOnClickListener(new OnAddClicked());

        LinearLayout addFreeProduct = view.findViewById(R.id.fragment_sale__button_add_free_product);
        addFreeProduct.setOnClickListener(new OnButtonAddProductClicked());

        TextView totalAmout = (TextView) view.findViewById(R.id.fragment_sale__result_figure);

        String parsedIntoStringAmount = String.valueOf(totalAmount);
        totalAmout.setText(parsedIntoStringAmount);

        //


        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        mAutoCompleteTextView = (AutoCompleteTextView)
                view.findViewById(R.id.fragment_sale__autoCompleteTextView_for_search);

        List<Product> products = new ArrayList<>();
        adapter = new SearchedProductListAdapter
                (getActivity(),R.layout.search_item_view_holder, products);

        mAutoCompleteTextView.setAdapter(adapter);
       mAutoCompleteTextView.setOnItemClickListener(onItemClickListener);

        return view;
    }

    private AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener(){
            Product product;

            @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                  product = (Product) parent.getAdapter().getItem(i);
                  dataBaseRepository.addItemInReceiptDataBase(getActivity(),product);
                    mAutoCompleteTextView.setText("");
                    mAutoCompleteTextView.setCursorVisible(false);

                Toast toast = Toast.makeText(getActivity(), "Товар добавлен в чек",
                        Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
                }
            };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (SaleFragmentListener) context;
    }

    public class OnAddClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {

        }
    }

    public class OnButtonAddProductClicked implements  View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), FreeGoods.class);
            getActivity().startActivity(intent);
        }
    }
}
