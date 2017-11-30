package com.example.artem.cashregister;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;

import com.example.artem.cashregister.data.Product;


public class AddProductDialogFragment extends DialogFragment {

    public interface Listener{
        void addProduct(Product product);
    }

    private Listener mListener;
    private EditText productName;
    private EditText productPrice;
    private EditText productCode;

        @NonNull
        public Dialog onCreateDialog(Bundle savedInctanceState){

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            Dialog result = builder.setTitle("Add new product").
                    setIcon(android.R.drawable.ic_dialog_dialer).
                    setView(R.layout.dialog_add_product_in_list).
                    setPositiveButton("Ок",okclickListener).
                    create();


            productName = (EditText) result.findViewById(R.id.dialog_add_product_in_list_user_input_name);
            productPrice = (EditText) result.findViewById(R.id.dialog_add_product_in_list_user_input_price);
            productCode = (EditText) result.findViewById(R.id.dialog_add_product_in_list_user_input_code);

            return result;

        }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        boolean isActivityImplementsListenerInterface = activity instanceof Listener;
        if(isActivityImplementsListenerInterface) {
            mListener = (Listener) activity;
        }
    }

    private final DialogInterface.OnClickListener okclickListener = new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(mListener != null) {
                    String name  = productName.getText().toString();
                    String price = productPrice.getText().toString();
                    String code = productCode.getText().toString();
                    Double parsedPrice = Double.parseDouble(price);
                    Product product = new Product(name,code,parsedPrice);
                    mListener.addProduct(product);
                }
            }
        };
}
