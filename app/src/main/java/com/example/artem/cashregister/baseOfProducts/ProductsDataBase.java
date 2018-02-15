package com.example.artem.cashregister.baseOfProducts;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.example.artem.cashregister.R;
import com.example.artem.cashregister.baseActivity.BaseActivity;
import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductsDataBase extends BaseActivity {
    List<Product> products = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.base_of_products_status_bar));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_products_data_base__toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Товар");

        FloatingActionButton floatButton = findViewById(R.id.activity_products_data_base__floating_button);
        floatButton.setOnClickListener(new OnAddClicked());

        AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "dataBase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

            try {
                products = db.productDao().getAll();
            } finally {
                    db.close();
            }

            RecyclerView recyclerView = findViewById(R.id.activity_products_data_base__recycle_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ProductAdapter productAdapter = new ProductAdapter(products);
            recyclerView.setAdapter(productAdapter);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);
    }

     @Override
     protected int getLayoutId(){
        int resource = R.layout.activity_products_data_base;
        return resource;
    }

    @Override
    protected int getToolBar(){
         int resource =  R.id.activity_products_data_base__toolbar;
         return resource;
    }

    public class OnAddClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ProductsDataBase.this, ProductCreation.class);
            ProductsDataBase.this.startActivity(intent);
        }
    }
}



