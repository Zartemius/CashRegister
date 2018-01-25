package com.example.artem.cashregister.sale.fragments.saleProcess;
import android.arch.lifecycle.LifecycleFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
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
import com.example.artem.cashregister.sale.FreeGoods.FreeGoods;

public class SaleFragment extends LifecycleFragment {

    public interface SaleFragmentListener{

    }

    private AutoCompleteTextView mAutoCompleteTextView;
    private SaleFragmentListener mListener;
    private Double totalAmount = 0.00;

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

        final String[] mProducts = { "Хлеб", "Вода", "Хлопок", "Водка",
                "Печенье", "Пенсне", "Вобла","Хлор","Перо","Персик" };

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        mAutoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.fragment_sale__autoCompleteTextView_for_search);
        mAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,mProducts));
        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long row) {

                mAutoCompleteTextView.setCursorVisible(true);
                Toast.makeText(getActivity(), "Yep! Works!",
                        Toast.LENGTH_LONG).show();
                        mAutoCompleteTextView.setText("");
                        mAutoCompleteTextView.setCursorVisible(false);
            }
        });

        return view;
    }

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
