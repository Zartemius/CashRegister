package com.example.artem.cashregister.sale.fragments.receipt;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.artem.cashregister.R;
import com.example.artem.cashregister.dataBase.GoodsInReceipt;
import com.example.artem.cashregister.sale.payment.Payment;
import java.util.ArrayList;
import java.util.List;

public class ReceiptFragment extends LifecycleFragment{

    public interface KindOfCalculationInReceipt{
        boolean getKindOfCalculation();
    }

    private KindOfCalculationInReceipt mListener;
    private ProductsInReceiptViewModel viewModel;
    private ProductInReceiptAdapter productInReceiptAdapter;
    private TextView clearReceiptButton;
    private TextView receiptTotalAmount;
    private Button calculationOfPayment;
    private double totalAmount = 0.0;
    private boolean calculationInsideFragment;

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

                if(!goodsInReceipt.isEmpty()) {

                    clearReceiptButton.setVisibility(View.VISIBLE);
                    calculationInsideFragment = mListener.getKindOfCalculation();

                    if (calculationInsideFragment) {
                        GoodsInReceipt lastElement = goodsInReceipt.get(goodsInReceipt.size() - 1);
                        String priceOfProduct = lastElement.getPrice();
                        double convertedInDoublePrice = Double.parseDouble(priceOfProduct);
                        totalAmount += convertedInDoublePrice;
                    }else{
                        for(GoodsInReceipt list: goodsInReceipt){
                            String priceOfProduct  = list.getPrice();
                            double convertedInDoublePrice = Double.parseDouble(priceOfProduct);
                            totalAmount+=convertedInDoublePrice;
                        }
                    }
                }
                String convertedInStringResult = String.valueOf(totalAmount);
                receiptTotalAmount.setText(convertedInStringResult);
            }
        });

        clearReceiptButton= view.findViewById(R.id.fragment_sale__products_in_receipt_text_view);
        clearReceiptButton.setOnClickListener(new OnClearListButtonClicked());

        calculationOfPayment = (Button) view.findViewById(R.id.fragment_sale__products_in_receipt_button_calculation);
        calculationOfPayment.setOnClickListener(new OnButtonCalculationOfPaymentClicked());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (KindOfCalculationInReceipt) context;
    }

    public class OnClearListButtonClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            viewModel.deleteAllGoodsFromReceipt();
            clearReceiptButton.setVisibility(View.GONE);
            totalAmount = 0.0;
            String convertedInStringResult = String.valueOf(totalAmount);
            receiptTotalAmount.setText(convertedInStringResult);
        }
    }

    public class OnButtonCalculationOfPaymentClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            if(totalAmount == 0.0) {
                Toast toast = Toast.makeText(getActivity(), R.string.fragment_receipt_toast_if_clicked_on_button_make_calculation,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
            }
            else{
                Intent intent = new Intent(getActivity(), Payment.class);
                getActivity().startActivity(intent);
            }
        }
    }
}
