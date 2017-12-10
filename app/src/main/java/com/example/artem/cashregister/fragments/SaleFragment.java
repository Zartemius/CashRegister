package com.example.artem.cashregister.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.artem.cashregister.MainActivity;
import com.example.artem.cashregister.R;
import com.example.artem.cashregister.data.ProductAdapter;
import com.example.artem.cashregister.data.ProductsModel;

public class SaleFragment extends Fragment {

    public interface SaleFragmentListener{
        ProductsModel getProductsModel();
        void requestAddDialogFragment();
    }

    SaleFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale, container,false);

        Button addProductButton = (Button) view.findViewById(R.id.fragment_sale__button_add_product_in_list);
        addProductButton.setOnClickListener(new OnAddClicked());

        ProductAdapter productAdapter = new ProductAdapter(mListener.getProductsModel());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_sale__productsInReceipt_recycle_view);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));

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
            mListener.requestAddDialogFragment();
        }
    }
}
