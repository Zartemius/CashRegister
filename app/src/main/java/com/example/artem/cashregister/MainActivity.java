package com.example.artem.cashregister;

import android.drm.DrmStore;
import android.support.v7.app.ActionBar;
import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.artem.cashregister.Fragments.AddProductDialogFragment;
import com.example.artem.cashregister.Fragments.SaleFragment;
import com.example.artem.cashregister.data.Product;
import com.example.artem.cashregister.data.ProductAdapter;
import com.example.artem.cashregister.data.ProductsModel;

public class MainActivity extends AppCompatActivity implements AddProductDialogFragment.Listener,
        SaleFragment.SaleFragmentListener,ActionBar.TabListener {

    private final ProductsModel productModel= new ProductsModel();
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public ProductsModel getProductsModel() {
        return productModel;
    }

    @Override
    public void requestAddDialogFragment() {
        openDialog();
    }

    @Override
    public void addProduct(Product product) {
        productModel.addProduct(product);
    }

    private void openDialog() {
        AddProductDialogFragment addProductDialogFragment = new AddProductDialogFragment();
        addProductDialogFragment.show(getSupportFragmentManager(),"Add product dialog");
    }
}


