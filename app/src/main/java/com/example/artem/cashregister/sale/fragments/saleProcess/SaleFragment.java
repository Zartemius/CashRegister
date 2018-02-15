package com.example.artem.cashregister.sale.fragments.saleProcess;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.artem.cashregister.R;
import com.example.artem.cashregister.dataBase.GoodsInReceipt;
import com.example.artem.cashregister.dataBase.Product;
import com.example.artem.cashregister.sale.fragments.receipt.ProductsInReceiptViewModel;
import com.example.artem.cashregister.sale.fragments.saleProcess.platesOfFastAccess.Plate;
import com.example.artem.cashregister.sale.fragments.saleProcess.platesOfFastAccess.PlateAdapter;
import com.example.artem.cashregister.sale.freeGoods.FreeGoods;
import com.example.artem.cashregister.sale.payment.Payment;

import java.util.ArrayList;
import java.util.List;

public class SaleFragment extends LifecycleFragment {

    public interface KindOfCalculationInSale {
        void setKindOfCalculation(boolean calculationInsideFragment);
    }

    private KindOfCalculationInSale mListener;
    private ProductsInReceiptViewModel viewModel;
    private double finalTotalAmount;
    private  SearchedProductListAdapter adapter;
    private DataBaseRepository dataBaseRepository = new DataBaseRepository();
    private AutoCompleteTextView mAutoCompleteTextView;
    private TextView totalAmount;
    private boolean calculationInsideFragment;
    private ArrayList<Plate> listOfPlates;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale, container,false);

        LinearLayout addFreeProduct = view.findViewById(R.id.fragment_sale__button_add_free_product);
        addFreeProduct.setOnClickListener(new OnButtonAddProductClicked());

        Button buttonAcceptPayment = (Button) view.findViewById(R.id.fragment_sale__button_calculation);
        buttonAcceptPayment.setOnClickListener(new OnButtonAcceptPaymentClicked());

        totalAmount = (TextView) view.findViewById(R.id.fragment_sale__result_figure);

        listOfPlates= new ArrayList<>();
        listOfPlates.add(new Plate(R.drawable.potato, "Картошка",23.00));
        listOfPlates.add(new Plate(R.drawable.cucumber, "Огурцы",67.00));
        listOfPlates.add(new Plate(R.drawable.carrot, "Морковь",44.00));
        listOfPlates.add(new Plate(R.drawable.radish,"Редис",36.00));
        listOfPlates.add(new Plate(R.drawable.cabbage, "Капуста белокачанная",29.00));

        PlateAdapter plateAdapter = new PlateAdapter(listOfPlates);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_sale__recycler_view_for_plates);
        recyclerView.setAdapter(plateAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        plateAdapter.setOnItemClickListener(new PlateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                calculationInsideFragment = true;
                mListener.setKindOfCalculation(calculationInsideFragment);
                String name = listOfPlates.get(position).getNameOfProduct();
                double price = listOfPlates.get(position).getPriceOfProduct();
                String convertedPrice = String.valueOf(price);
                dataBaseRepository.addItemInReceiptDataBase(getActivity(),new Product(name,"0",convertedPrice));

                Toast toast = Toast.makeText(getActivity(), R.string.sale_fragment_confirmation_that_goods_were_added_in_receipt,
                        Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
            }
        });

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        mAutoCompleteTextView = (AutoCompleteTextView)
                view.findViewById(R.id.fragment_sale__autoCompleteTextView_for_search);
        List<Product> products = new ArrayList<>();
        adapter = new SearchedProductListAdapter
                (getActivity(),R.layout.search_item_view_holder, products);
        mAutoCompleteTextView.setAdapter(adapter);
        mAutoCompleteTextView.setOnItemClickListener(onItemClickListener);
        mAutoCompleteTextView.setDropDownAnchor(R.id.fragment_sale__layout_of_search_view);

        viewModel = ViewModelProviders.of(this).get(ProductsInReceiptViewModel.class);
        viewModel.getReceiptProductsList().observe(SaleFragment.this, new Observer<List<GoodsInReceipt>>() {
            @Override
            public void onChanged(@Nullable List<GoodsInReceipt> goodsInReceipt) {

                if(goodsInReceipt.isEmpty()){
                    finalTotalAmount = 0.00;
                } else  {
                    if(calculationInsideFragment) {
                        GoodsInReceipt lastElement = goodsInReceipt.get(goodsInReceipt.size() - 1);
                        String priceOfProduct = lastElement.getPrice();
                        double convertedInDoublePrice = Double.parseDouble(priceOfProduct);
                        finalTotalAmount += convertedInDoublePrice;
                    }else{
                        for(GoodsInReceipt list: goodsInReceipt){
                            String priceOfProduct  = list.getPrice();
                            double convertedInDoublePrice = Double.parseDouble(priceOfProduct);
                            finalTotalAmount+=convertedInDoublePrice;
                        }
                    }
                }
                String convertedInStringResult = String.valueOf(finalTotalAmount);
                totalAmount.setText(convertedInStringResult);
            }
        });

        return view;
    }

    private AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener(){

            @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                calculationInsideFragment = true;
                mListener.setKindOfCalculation(calculationInsideFragment);
                 Product  product = (Product) parent.getAdapter().getItem(i);
                  dataBaseRepository.addItemInReceiptDataBase(getActivity(),product);
                    mAutoCompleteTextView.setText("");

                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    View v = getActivity().getCurrentFocus();
                    if(v != null){
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                Toast toast = Toast.makeText(getActivity(),R.string.sale_fragment_confirmation_that_goods_were_added_in_receipt,
                        Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
                }
            };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (KindOfCalculationInSale) context;
    }

    public class OnButtonAddProductClicked implements  View.OnClickListener {
        @Override
        public void onClick(View view) {
            calculationInsideFragment = false;
            mListener.setKindOfCalculation(calculationInsideFragment);

            Intent intent = new Intent(getActivity(), FreeGoods.class);
            getActivity().startActivity(intent);
        }
    }

    public class OnButtonAcceptPaymentClicked implements  View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(finalTotalAmount == 0.0) {

                Toast toast = Toast.makeText(getActivity(), R.string.fragment_receipt_toast_if_clicked_on_button_make_calculation,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
            }
            else{
                Intent intent = new Intent(getActivity(), Payment.class);
                getActivity().startActivity(intent);
            }
        }
    }
}
