package com.example.artem.cashregister.sale.payment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.example.artem.cashregister.R;
import com.example.artem.cashregister.sale.SaleActivity;

public class FinishedPaymentWithoutMoney extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view;
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.yellow));
        }

        view = inflater.inflate(R.layout.payment_finished_version_without_money, container, false);
        Button finishedOperation = view.findViewById(R.id.payment_finished_version_without_money_button_finish_operation);
        finishedOperation.setOnClickListener(new OnButtonFinishedOperationClicked());
        return view;
    }

    public class OnButtonFinishedOperationClicked implements  View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), SaleActivity.class);
            getActivity().startActivity(intent);
        }
    }
}
