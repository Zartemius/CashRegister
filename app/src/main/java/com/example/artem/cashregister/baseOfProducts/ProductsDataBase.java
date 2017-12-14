package com.example.artem.cashregister.baseOfProducts;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.Configuration;
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

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.SaleActivity;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_products_data_base__toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton floatButton = findViewById(R.id.activity_products_data_base__floating_button);
        floatButton.setOnClickListener(new OnAddClicked());

        AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "DataBaseOfProducts")
                .allowMainThreadQueries()
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
                    this,                  /* host Activity */
                    mDrawerLayout,         /* DrawerLayout object */
                    R.string.drawer_open,  /* "open drawer" description */
                    R.string.drawer_close  /* "close drawer" description */
            ) {

                /**
                 * Called when a drawer has settled in a completely closed state.
                 */
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    getSupportActionBar().setTitle(mTitle);
                }

                /**
                 * Called when a drawer has settled in a completely open state.
                 */
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    getSupportActionBar().setTitle(mDrawerTitle);
                }
            };

            // Set the drawer toggle as the DrawerListener
            mDrawerLayout.setDrawerListener(mDrawerToggle);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        @Override
        protected void onPostCreate (Bundle savedInstanceState){
            super.onPostCreate(savedInstanceState);
            // Sync the toggle state after onRestoreInstanceState has occurred.
            mDrawerToggle.syncState();
        }

        @Override
        public void onConfigurationChanged (Configuration newConfig){
            super.onConfigurationChanged(newConfig);
            mDrawerToggle.onConfigurationChanged(newConfig);
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Pass the event to ActionBarDrawerToggle, if it returns
            // true, then it has handled the app icon touch event
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
            // Handle your other action bar items...

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



