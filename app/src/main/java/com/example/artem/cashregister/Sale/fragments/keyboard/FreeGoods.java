package com.example.artem.cashregister.Sale.fragments.keyboard;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.artem.cashregister.R;
import com.example.artem.cashregister.Sale.SaleActivity;

public class FreeGoods extends Fragment {

    String defaultNameForGoodsNameWindow = "Товар";
    CustomKeyboardForFreeGoods customKeyboard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_free_goods, container, false);

        customKeyboard = new CustomKeyboardForFreeGoods(getActivity(),view, R.id.keyboardView, R.layout.keyboard);
        EditText windowForPriceOfGoods = (EditText) view.findViewById(R.id.activity_free_goods__price_window);
        EditText windowForNameOfGoods = (EditText) view.findViewById(R.id.activity_free_goods__product_name_window);
        customKeyboard.registerEditText(windowForPriceOfGoods);

        windowForPriceOfGoods.requestFocus();
        windowForPriceOfGoods.setCursorVisible(true);
        windowForNameOfGoods.setText(defaultNameForGoodsNameWindow);

        return view;
    }
}
