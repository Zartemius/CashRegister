package com.example.artem.cashregister;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.example.artem.cashregister.Fragments.AddProductDialogFragment;
import com.example.artem.cashregister.Fragments.SaleFragment;
import com.example.artem.cashregister.data.Product;
import com.example.artem.cashregister.data.ProductAdapter;
import com.example.artem.cashregister.data.ProductsModel;

public class MainActivity extends AppCompatActivity implements AddProductDialogFragment.Listener,
        SaleFragment.SaleFragmentListener {

    private final ProductsModel productModel= new ProductsModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SaleFragment saleFragment = new SaleFragment();
        fragmentTransaction.add(R.id.main_activity__container_for_sale_fragment,saleFragment);
        fragmentTransaction.commit();
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
