package com.example.artem.cashregister.baseOfProducts;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.Product;

public class ProductCreation extends AppCompatActivity {

    private EditText productName;
    private EditText code;
    private EditText price;
    Button button;
    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_product);

        if(Build.VERSION.SDK_INT >=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.base_of_products_status_bar));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.creation_product__toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        productName = findViewById(R.id.creation_product__add_product_name);
        code = findViewById(R.id.creation_product__add_product_code);
        price = findViewById(R.id.creation_product__add_product_price);
        button = findViewById(R.id.creation_product__add_button);

        button.setOnClickListener(new OnDataBaseEditTextFieldsClicked());
    }

    public void showWarningMessage(String message){
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }

    public class OnDataBaseEditTextFieldsClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            String receivedName = productName.getText().toString();
            String receivedCode = code.getText().toString();
            String receivedPrice = price.getText().toString();

            if(receivedName.equals("") || receivedCode.equals("")|| receivedPrice.equals("")) {
                showWarningMessage("Не хватает данных для добавления продукта в базу");
            }
            else{
                Product productWithReceivedData = new Product(receivedName, receivedCode, receivedPrice);
                db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "DataBaseOfProducts")
                        .allowMainThreadQueries()
                        .build();
                try {
                    db.productDao().insertAll(productWithReceivedData);
                } finally {
                    db.close();
                }
                Intent intent = new Intent(ProductCreation.this, ProductsDataBase.class);
                ProductCreation.this.startActivity(intent);
            }
        }
    }
}
