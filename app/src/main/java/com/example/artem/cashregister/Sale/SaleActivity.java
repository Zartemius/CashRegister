package com.example.artem.cashregister.Sale;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.Sale.fragments.receipt.ProductInReceipt;
import com.example.artem.cashregister.Sale.fragments.receipt.ReceiptFragment;
import com.example.artem.cashregister.baseOfProducts.ProductsDataBase;
import com.example.artem.cashregister.Sale.fragments.saleProcess.AddProductDialogFragment;
import com.example.artem.cashregister.Sale.fragments.saleProcess.SaleFragment;
import com.example.artem.cashregister.Sale.fragments.receipt.ProductInReceiptModel;
import com.example.artem.cashregister.dataBase.AppDataBase;
import com.example.artem.cashregister.dataBase.Product;

public class SaleActivity extends AppCompatActivity implements
        SaleFragment.SaleFragmentListener,ActionBar.TabListener,ReceiptFragment.ReceiptFragmentListener,
        AddProductDialogFragment.AddProductDialogFragmentListener {

    //AddProductDialogFragment.Listener

    private ProductInReceiptModel productInReceiptModel= new ProductInReceiptModel();
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for(int i = 0; i<mSectionsPagerAdapter.getCount(); i++){
            actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
        }

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
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
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
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

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

    @Override
    public void clearListOfPurchases() {
        productInReceiptModel.clearListOfProducts();
    }

    @Override
    public double getTotalAmout() {

        Double result = productInReceiptModel.gainSumOfPurcases();

        return result;
    }

    private void openDialog() {
        AddProductDialogFragment addProductDialogFragment = new AddProductDialogFragment();
        addProductDialogFragment.show(getSupportFragmentManager(),"Add product dialog");
    }
}


