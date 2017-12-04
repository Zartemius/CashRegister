package com.example.artem.cashregister.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.data.Product;

public class AddProductDialogFragment extends DialogFragment {

    public interface Listener{
        void addProduct(Product product);
    }

    private Listener mlistener;
    private EditText productName;
    private EditText productPrice;
    private EditText productCode;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder result = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_product_in_list,null);

        result.setTitle("Add a product")
                .setView(view)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name  = productName.getText().toString();
                        String price = productPrice.getText().toString();
                        String code = productCode.getText().toString();
                        Double parsedPrice = Double.parseDouble(price);
                        Product product = new Product(name,code,parsedPrice);
                        mlistener.addProduct(product);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        productName = view.findViewById(R.id.dialog_add_product_in_list_user_input_name);
        productPrice = view.findViewById(R.id.dialog_add_product_in_list_user_input_price);
        productCode = view.findViewById(R.id.dialog_add_product_in_list_user_input_code);

        return result.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mlistener = (Listener) context;
    }
}
