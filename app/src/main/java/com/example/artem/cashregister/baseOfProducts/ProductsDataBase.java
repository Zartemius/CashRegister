package com.example.artem.cashregister.baseOfProducts;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.sale.SaleActivity;
import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsDataBase extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_data_base);

        if(Build.VERSION.SDK_INT >=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.base_of_products_status_bar));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_products_data_base__toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


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



            NavigationView navigationView = findViewById(R.id.navigation);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    int id = item.getItemId();
                    if (id == R.id.nav_sale) {
                        Intent intent = new Intent(ProductsDataBase.this, SaleActivity.class);
                        ProductsDataBase.this.startActivity(intent);

                    } else if (id == R.id.nav_goodsBase) {
                        Intent intent = new Intent(ProductsDataBase.this, ProductsDataBase.class);
                        ProductsDataBase.this.startActivity(intent);
                    }
                    return true;
                }
            });

            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerToggle = new ActionBarDrawerToggle(
                    this,
                    mDrawerLayout,
                    R.string.drawer_open,
                    R.string.drawer_close
            ) {


                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    getSupportActionBar().setTitle(mTitle);
                }


                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    getSupportActionBar().setTitle(mDrawerTitle);
                }
            };


            mDrawerLayout.setDrawerListener(mDrawerToggle);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        @Override
        protected void onPostCreate (Bundle savedInstanceState){
            super.onPostCreate(savedInstanceState);
            mDrawerToggle.syncState();
        }

        @Override
        public void onConfigurationChanged (Configuration newConfig){
            super.onConfigurationChanged(newConfig);
            mDrawerToggle.onConfigurationChanged(newConfig);
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    public class OnAddClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ProductsDataBase.this, ProductCreation.class);
            ProductsDataBase.this.startActivity(intent);
        }
    }

}



