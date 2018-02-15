package com.example.artem.cashregister.sale.payment;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CustomKeyboardForPayment {
    private final static int BACKSPACE = -2;
    private final static int SET_AMOUNT = -1;
    private final static int FIVE_HUNDRED = -123;
    private final static int ONE_THOUSAND = -124;
    private final static int FIVE_THOUSAND = -125;
    private final String numberFiveHundred = "500";
    private final String numberOneThousand = "1000";
    private final String numberFiveThousand = "5000";
    private Activity mHostActivity;
    private KeyboardView mKeyboardView;
    private EditText amountForPayment;

    CustomKeyboardForPayment(Activity host, int keyBoardViewId, int keyBoardId, int editTextAmountForPaymentId){
        mHostActivity = host;
        mKeyboardView = (KeyboardView)mHostActivity.findViewById(keyBoardViewId);
        mKeyboardView.setKeyboard(new Keyboard(mHostActivity,keyBoardId));
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);
        mHostActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        amountForPayment = mHostActivity.findViewById(editTextAmountForPaymentId);
    }

    public boolean isCustomKeyboardVisible(){
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }

    public void showCustomKeyboard(View v){
        mKeyboardView.setVisibility(View.VISIBLE);
        mKeyboardView.setEnabled(true);
        if(v != null){
            ((InputMethodManager)mHostActivity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void registerEditText(EditText editText) {

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                showCustomKeyboard(view);
            }
        });

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText edittext = (EditText) v;
                int inType = edittext.getInputType();       // Backup the input type
                edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
                edittext.onTouchEvent(event);               // Call native handler
                edittext.setInputType(inType);              // Restore input type
                return true; // Consume touch event
            }
        });

        editText.setInputType(editText.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }

    private KeyboardView.OnKeyboardActionListener mOnKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int i) {

        }

        @Override
        public void onRelease(int i) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            long eventTime = System.currentTimeMillis();
            KeyEvent event = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0, KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);
            mHostActivity.dispatchKeyEvent(event);

            if(primaryCode == BACKSPACE){
                View focusCurrent = mHostActivity.getWindow().getCurrentFocus();
                if (focusCurrent instanceof EditText) {
                    EditText edittext = (EditText) focusCurrent;
                    int start = edittext.getSelectionStart();
                    Editable editable = edittext.getText();
                    if(editable!=null && start > 0) {
                        editable.delete(start-1,start);
                    }
                }
            }

            if (primaryCode == SET_AMOUNT) {
                View focusCurrent = mHostActivity.getWindow().getCurrentFocus();
                if (focusCurrent instanceof EditText) {
                    EditText edittext = (EditText) focusCurrent;
                    String dataFromEditTextAmountOfPayment = amountForPayment.getText().toString();
                    edittext.setText(dataFromEditTextAmountOfPayment);
                    int position = edittext.length();
                    Editable text = edittext.getText();
                    Selection.setSelection(text,position);
                }
            }

            if(primaryCode == FIVE_HUNDRED){
                View focusCurrent = mHostActivity.getWindow().getCurrentFocus();
                if (focusCurrent instanceof EditText) {
                    EditText edittext = (EditText) focusCurrent;
                    edittext.setText(numberFiveHundred);
                    int position = edittext.length();
                    Editable text = edittext.getText();
                    Selection.setSelection(text,position);
                }
            }

            if(primaryCode == ONE_THOUSAND){
                View focusCurrent = mHostActivity.getWindow().getCurrentFocus();
                if (focusCurrent instanceof EditText) {
                    EditText edittext = (EditText) focusCurrent;
                    edittext.setText(numberOneThousand);
                    int position = edittext.length();
                    Editable text = edittext.getText();
                    Selection.setSelection(text,position);
                }
            }

            if(primaryCode == FIVE_THOUSAND){
                View focusCurrent = mHostActivity.getWindow().getCurrentFocus();
                if (focusCurrent instanceof EditText) {
                    EditText edittext = (EditText) focusCurrent;
                    edittext.setText(numberFiveThousand);
                    int position = edittext.length();
                    Editable text = edittext.getText();
                    Selection.setSelection(text,position);

                }
            }
        }

        @Override
        public void onText(CharSequence charSequence) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };
}
