package com.example.artem.cashregister.sale.fragments.receipt;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.dataBase.GoodsInReceipt;

import java.util.ArrayList;
import java.util.List;

public class ReceiptFragment extends LifecycleFragment{

    public interface ReceiptFragmentListener {
    }

    ProductsInReceiptViewModel viewModel;
    ProductInReceiptAdapter productInReceiptAdapter;
    ReceiptFragmentListener mListener;
    TextView clearReceiptButton;
    TextView receiptTotalAmount;
    double result = 0.0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_receipt, container,false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_sale__products_in_receipt_recycle_view);
        productInReceiptAdapter = new ProductInReceiptAdapter( new ArrayList<GoodsInReceipt>());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(productInReceiptAdapter);


        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);


        viewModel = ViewModelProviders.of(this).get(ProductsInReceiptViewModel.class);

        receiptTotalAmount = view.findViewById(R.id.fragment_sale__products_in_receipt_result_figure);

        viewModel.getReceiptProductsList().observe(ReceiptFragment.this, new Observer<List<GoodsInReceipt>>() {
            @Override
            public void onChanged(@Nullable List<GoodsInReceipt> goodsInReceipt) {

                productInReceiptAdapter.addItems(goodsInReceipt);

                if(!goodsInReceipt.isEmpty()){
                    clearReceiptButton.setVisibility(View.VISIBLE);
                }

                for(GoodsInReceipt list: goodsInReceipt){
                    String priceOfProduct  = list.getPrice();
                    double convertedInDoublePrice = Double.parseDouble(priceOfProduct);
                    result+=convertedInDoublePrice;
                }

                String convertedInStringResult = String.valueOf(result);

                receiptTotalAmount.setText(convertedInStringResult);
            }
        });

        clearReceiptButton= view.findViewById(R.id.fragment_sale__products_in_receipt_text_view);
        clearReceiptButton.setOnClickListener(new OnClearListButton());

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
            viewModel.deleteAllGoodsFromReceipt();
            clearReceiptButton.setVisibility(View.GONE);
            result = 0.0;
        }
    }
}
