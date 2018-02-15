package com.example.artem.cashregister.baseActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.baseOfProducts.ProductsDataBase;
import com.example.artem.cashregister.sale.SaleActivity;

public class LauncherActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.base_activity__status_bar));
        }
    }

    @Override
    protected int getLayoutId(){
        int resource = R.layout.activity_launcher;
        return resource;
    }

    @Override
    protected int getToolBar(){
        int resource =  R.id.activity_launcher__toolbar;
        return resource;
    }
}
