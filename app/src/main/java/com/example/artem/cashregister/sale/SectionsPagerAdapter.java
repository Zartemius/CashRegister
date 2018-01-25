package com.example.artem.cashregister.sale;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.artem.cashregister.sale.fragments.receipt.ReceiptFragment;
import com.example.artem.cashregister.sale.fragments.saleProcess.SaleFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch(position){
            case 0:
                fragment = new SaleFragment();
                break;
            case 1:
                fragment = new ReceiptFragment();
                break;
        }
        return fragment;


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0){
            return "ТОВАРЫ";
        }
        if(position == 1){

            return "ЧЕК";
        }
        return null;
    }
}
