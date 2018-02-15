package com.example.artem.cashregister.sale.payment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.dataBase.GoodsInReceipt;

import java.util.List;

public class Payment extends AppCompatActivity implements FinishedPaymentWithMoney.FinishedPaymentListener{
    private CustomKeyboardForPayment customKeyboard;
    private EditText windowForAmountReceivedFromBuyer;
    private EditText windowForAmountForPayment;
    private PaymentViewModel viewModel;
    private double result = 0.0;
    FinishedPaymentWithMoney finishedPaymentWithMoney = new FinishedPaymentWithMoney();
    FinishedPaymentWithoutMoney finishedPaymentWithoutMoney = new FinishedPaymentWithoutMoney();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Toolbar toolbar = findViewById(R.id.activity_payment__toolbar);
        setSupportActionBar(toolbar);

        customKeyboard = new CustomKeyboardForPayment(this, R.id.activity_payment__keyboardView,
                R.layout.keyboard_of_activity_payment,R.id.activity_payment__amount_for_payment);

        windowForAmountForPayment = (EditText) findViewById(R.id.activity_payment__amount_for_payment);
        windowForAmountReceivedFromBuyer = (EditText) findViewById( R.id.activity_payment__amount_received_from_buyer);
        customKeyboard.registerEditText(windowForAmountReceivedFromBuyer);
        windowForAmountForPayment.setFocusable(false);
        windowForAmountReceivedFromBuyer.requestFocus();
        windowForAmountReceivedFromBuyer.setCursorVisible(true);
        Button button = findViewById(R.id.activity_payment__button_to_finish_payment_operation);
        button.setOnClickListener(new OnButtonFinishPaymentClicked());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.payment);

        viewModel = ViewModelProviders.of(this).get(PaymentViewModel.class);
        viewModel.getReceiptProductsList().observe(Payment.this, new Observer<List<GoodsInReceipt>>() {
            @Override
            public void onChanged(@Nullable List<GoodsInReceipt> goodsInReceipts) {

                for(GoodsInReceipt list: goodsInReceipts){
                    String priceOfProduct  = list.getPrice();
                    double convertedInDoublePrice = Double.parseDouble(priceOfProduct);
                    result+=convertedInDoublePrice;
                }

                String convertedInStringResult = String.valueOf(result);
                windowForAmountForPayment.setText(convertedInStringResult);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String calculateAmountOfChange(){
        String amount;
        String amountForPayment = windowForAmountForPayment.getText().toString();
        String amountReceived = windowForAmountReceivedFromBuyer.getText().toString();
        double convertedAmountForPayment = Double.parseDouble(amountForPayment);
        double convertedAmountReceived = Double.parseDouble(amountReceived);
        double result = convertedAmountReceived - convertedAmountForPayment;
        amount = String.valueOf(result);
        return amount;
    }

    @Override
    public String getAmountOfChange(){
        String result = calculateAmountOfChange();
        return result;
    }

    public class OnButtonFinishPaymentClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            String amountForPayment = windowForAmountForPayment.getText().toString();
            String amountReceived = windowForAmountReceivedFromBuyer.getText().toString();

            if (amountForPayment.equals("") || amountReceived.equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.payment_not_enough_information_to_make_payment,
                        Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            } else{

                double convertedAmountForPayment = Double.parseDouble(amountForPayment);
                double convertedAmountReceived = Double.parseDouble(amountReceived);

                if (convertedAmountReceived < convertedAmountForPayment) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            R.string.activity_payment_toast_message_after_button_clicked,
                            Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
                if (convertedAmountReceived > convertedAmountForPayment) {
                    fillContainerWithFragment(finishedPaymentWithMoney);
                }
                if (convertedAmountReceived == convertedAmountForPayment) {
                    fillContainerWithFragment(finishedPaymentWithoutMoney);
                }
            }
        }

        private void fillContainerWithFragment(Fragment passedFragment){
            FrameLayout framelayout = findViewById(R.id.activity_payment__container_for_fragment);
            framelayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = passedFragment;
            fragmentTransaction.add(R.id.activity_payment__container_for_fragment, fragment);
            fragmentTransaction.commit();
            viewModel.deleteAllGoodsFromReceipt();
        }
    }
}
