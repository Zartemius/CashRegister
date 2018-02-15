package com.example.artem.cashregister.sale.freeGoods;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.artem.cashregister.R;
import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.GoodsInReceipt;

public class FreeGoods extends AppCompatActivity {
    private final String defaultNameForGoodsNameWindow = "Товар";
    private CustomKeyboardForFreeGoods customKeyboard;
    private EditText windowForPriceOfGoods;
    private EditText windowForNameOfGoods;
    private AddProductViewModel addProductViewModel;
    private AppDataBase db;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_goods);

        Toolbar toolbar = findViewById(R.id.activity_free_goods___toolbar);
        setSupportActionBar(toolbar);

        customKeyboard = new CustomKeyboardForFreeGoods(this, R.id.keyboardView, R.layout.keyboard);
        windowForPriceOfGoods = (EditText) findViewById(R.id.activity_free_goods__price_window);
        windowForNameOfGoods = (EditText) findViewById(R.id.activity_free_goods__product_name_window);
        customKeyboard.registerEditText(windowForPriceOfGoods);
        addProductViewModel = ViewModelProviders.of(this).get(AddProductViewModel.class);

        windowForPriceOfGoods.requestFocus();
        windowForPriceOfGoods.setCursorVisible(true);
        windowForNameOfGoods.setText(defaultNameForGoodsNameWindow);
        windowForNameOfGoods.setFocusable(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.free_goods_title_free_goods_in_receipt);

        Button buttonAddFreeProductsInReceipt= findViewById(R.id.activity_free_goods__button_to_add_free_product);
        buttonAddFreeProductsInReceipt.setOnClickListener(new ButtonAddProductClickListener());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public class ButtonAddProductClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String productName = windowForNameOfGoods.getText().toString();
            String productPrice = windowForPriceOfGoods.getText().toString();

            if(productName.isEmpty() || productPrice.isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(), R.string.free_goods_not_enough_information_to_add_goods_in_receipt,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
            }
            else {
                addProductViewModel.addProduct(new GoodsInReceipt(productName, productPrice));

                if(productName.equals(defaultNameForGoodsNameWindow)){
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.free_goods_goods_with_price
                                    + productPrice + R.string.free_goods_goods_was_added_in_receipt,
                            Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.free_goods_goods_goods + productName + R.string.free_goods_with_price +
                                    productPrice +R.string.free_goods_goods_was_added_in_receipt,
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();
                }
            }
            }
        }
}
