package com.example.artem.cashregister;


import android.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;


public class AddProductDialogFragment extends DialogFragment {

        @NonNull
        public Dialog onCreateDialog(Bundle savedInctanceState){

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder.setTitle("Add new product").
                    setIcon(android.R.drawable.ic_dialog_dialer).
                    setView(R.layout.dialog_add_product_in_list).
                    setPositiveButton("Ок",null).
                    create();
        }
}
