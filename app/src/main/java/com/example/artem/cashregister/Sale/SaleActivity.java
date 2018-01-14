package com.example.artem.cashregister.Sale;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.Sale.fragments.keyboard.FreeGoods;
import com.example.artem.cashregister.Sale.fragments.receipt.ProductInReceipt;
import com.example.artem.cashregister.Sale.fragments.receipt.ReceiptFragment;
import com.example.artem.cashregister.baseOfProducts.ProductsDataBase;
import com.example.artem.cashregister.Sale.fragments.saleProcess.AddProductDialogFragment;
import com.example.artem.cashregister.Sale.fragments.saleProcess.SaleFragment;
import com.example.artem.cashregister.Sale.fragments.receipt.ProductInReceiptModel;
import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.Product;

import java.util.List;

public class SaleActivity extends AppCompatActivity implements
        SaleFragment.SaleFragmentListener,ReceiptFragment.ReceiptFragmentListener,
        AddProductDialogFragment.AddProductDialogFragmentListener {

    private ProductInReceiptModel productInReceiptModel= new ProductInReceiptModel();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id == R.id.nav_sale){
                    Intent intent = new Intent(SaleActivity.this, SaleActivity.class);
                    SaleActivity.this.startActivity(intent);

                }
                else if (id == R.id.nav_goodsBase){
                    Intent intent = new Intent(SaleActivity.this, ProductsDataBase.class);
                    SaleActivity.this.startActivity(intent);
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


        FragmentManager fragmentManager = getSupportFragmentManager();
        RootFragment rootFragment = new RootFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_sale__container_of_called_fragment, rootFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public ProductInReceiptModel getProductsModel() {
        return productInReceiptModel;
    }

    @Override
    public void requestAddDialogFragment() {
        openDialog();
    }

    @Override
    public void addProduct(ProductInReceipt productInReceipt) {
       productInReceiptModel.addProduct(productInReceipt);
    }

    @Override
    public Product findProductInDataBase(String nameOfProduct) {

        Product product;

        AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "DataBaseOfProducts")
                .allowMainThreadQueries()
                .build();
        try {
            product = db.productDao().findByName(nameOfProduct);
        } finally {
            db.close();
        }
        return product;
    }


    private void openDialog() {
        AddProductDialogFragment addProductDialogFragment = new AddProductDialogFragment();
        addProductDialogFragment.show(getSupportFragmentManager(),"Add product dialog");
    }


}


