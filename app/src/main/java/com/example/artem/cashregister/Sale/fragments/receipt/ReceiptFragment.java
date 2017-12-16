package com.example.artem.cashregister.Sale.fragments.receipt;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.artem.cashregister.R;

public class ReceiptFragment extends Fragment{

    public interface ReceiptFragmentListener {
        ProductInReceiptModel getProductsModel();
        void clearListOfPurchases();
    }

    ReceiptFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_receipt, container,false);
        ProductInReceiptAdapter productInReceiptAdapter = new ProductInReceiptAdapter(mListener.getProductsModel());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_sale__products_in_receipt_recycle_view);
        recyclerView.setAdapter(productInReceiptAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Button buttonToDeletePurchaseson = (Button) view.findViewById(R.id.fragment_sale__products_in_receipt_button);
        buttonToDeletePurchaseson.setOnClickListener(new OnClearListButton());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ReceiptFragmentListener) context;
    }

    public class OnClearListButton implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            mListener.clearListOfPurchases();
        }
    }
}
