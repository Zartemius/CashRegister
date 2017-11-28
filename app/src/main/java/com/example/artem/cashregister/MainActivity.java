package com.example.artem.cashregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addProductButton = (Button) findViewById(R.id.main_activity__button_add_product_in_list);
        addProductButton.setOnClickListener(new OnAddClicked());
    }

    public class OnAddClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            AddProductDialogFragment dialog = new AddProductDialogFragment();
            dialog.show(getSupportFragmentManager(), "Custom");
        }
    }
}


