package com.example.artem.cashregister;

import android.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.artem.cashregister.data.Product;
import com.example.artem.cashregister.data.ProductAdapter;
import com.example.artem.cashregister.data.ProductsModel;

public class MainActivity extends AppCompatActivity implements AddProductDialogFragment.Listener {

    private final ProductsModel productModel= new ProductsModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addProductButton = (Button) findViewById(R.id.main_activity__button_add_product_in_list);
        addProductButton.setOnClickListener(new OnAddClicked());

        ProductAdapter productAdapter = new ProductAdapter(productModel);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_activity__productsInReceipt_recycle_view);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
    }

    @Override
    public void addProduct(Product product) {
        productModel.addProduct(product);
    }

    private void openDialog() {
        AddProductDialogFragment addProductDialogFragment = new AddProductDialogFragment();
        addProductDialogFragment.show(getSupportFragmentManager(),"Add product dialog");
    }

    public class OnAddClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            openDialog();
        }
    }
}
