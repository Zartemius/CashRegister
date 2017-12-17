package com.example.artem.cashregister.Sale.fragments.saleProcess;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.artem.cashregister.R;
import com.example.artem.cashregister.Sale.fragments.receipt.ProductInReceipt;
import com.example.artem.cashregister.dataBase.Product;

public class AddProductDialogFragment extends DialogFragment {

    public interface AddProductDialogFragmentListener{
        void addProduct(ProductInReceipt productInReceipt);
        Product findProductInDataBase(String nameOfProduct);
    }

    private AddProductDialogFragmentListener mlistener;
    private EditText productName;
    private Product productFromDataBase;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder result = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_product_in_list,null);

        result.setTitle("Add a product")
                .setView(view)
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name  = productName.getText().toString();

                        if(name.equals("")) {
                            Toast.makeText(getContext(), "Строка для ввода данных осталась пустой",Toast.LENGTH_LONG).show();
                        }
                        else {
                            productFromDataBase = mlistener.findProductInDataBase(name);
                            ProductInReceipt productForAddingToReceipt = new ProductInReceipt(productFromDataBase);
                            mlistener.addProduct(productForAddingToReceipt);
                        }
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        productName = view.findViewById(R.id.dialog_add_product_in_list_user_input_name);

        return result.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       mlistener = (AddProductDialogFragmentListener) context;
    }
}


