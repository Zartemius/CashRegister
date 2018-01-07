package com.example.artem.cashregister.Sale.fragments.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import com.example.artem.cashregister.R;

public class FreeGoods extends AppCompatActivity {

    String defaultNameForGoodsNameWindow = "Товар";
    CustomKeyboardForFreeGoods customKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_goods);

        customKeyboard = new CustomKeyboardForFreeGoods(this,R.id.keyboardView,R.layout.keyboard );
        EditText windowForPriceOfGoods = (EditText) findViewById(R.id.activity_free_goods__price_window);
        EditText windowForNameOfGoods = (EditText) findViewById(R.id.activity_free_goods__product_name_window);
        customKeyboard.registerEditText(windowForPriceOfGoods);

        windowForPriceOfGoods.requestFocus();
        windowForPriceOfGoods.setCursorVisible(true);
        windowForNameOfGoods.setText(defaultNameForGoodsNameWindow);
    }
}
