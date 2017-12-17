package com.example.artem.cashregister.Sale.fragments.saleProcess;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.Sale.fragments.receipt.ProductInReceipt;
import com.example.artem.cashregister.Sale.fragments.receipt.ProductInReceiptModel;

public class SaleFragment extends Fragment {

    public interface SaleFragmentListener{
        void requestAddDialogFragment();
    }

    SaleFragmentListener mListener;
    Double totalAmount = 0.00;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale, container,false);

        ImageView addproduct = view.findViewById(R.id.fragment_sale__button_add_product_in_list);
        addproduct.setOnClickListener(new OnAddClicked());

        TextView totalAmout = (TextView) view.findViewById(R.id.fragment_sale__result_figure);

        String parsedIntoStringAmount = String.valueOf(totalAmount);
        totalAmout.setText(parsedIntoStringAmount);

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
