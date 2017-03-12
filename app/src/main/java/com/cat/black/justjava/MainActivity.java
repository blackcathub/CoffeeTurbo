package com.cat.black.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int priceForOne = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Check button click method - display order summery on screen
    public void showOrder(View view) {
        displayMessage(createOrderSummary());
    }

    //Send button click method - send order summary by email
    public void sendOrder(View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.summary_order_for) + ((EditText) findViewById(R.id.enter_name)).getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary ());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // Quantity handler methods
    public void increment (View view) {
        String limit = getString(R.string.toast_bigorder);
        quantity += 1;
        if (quantity > 100) {quantity = 100; quantityToast(limit);}
        displayQuantity(quantity);
    }

    public void decrement (View view) {
        String limit = getString(R.string.toast_tinyorder);
        quantity -= 1;
        if (quantity < 1) {quantity = 1; quantityToast(limit);}
        displayQuantity(quantity);
    }

    // Quantity bounds reached Toast method
    private void quantityToast (String info) {
        //Custom layout inflater and selector
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        //Text handler
        TextView text = (TextView) layout.findViewById(R.id.toast_txt);
        text.setText(info);
        //Toast creator
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 96);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    // Topping displays
    public String whippedState = "NO";
    public String pepsiState = "NO";
    public String chockoState = "NO";

    // Toppings checked state checker method
    public void onCheckboxChecked(View v) {
        boolean checked = ((CheckBox) v).isChecked();

        switch(v.getId()) {
            case R.id.topping_whipped_cream:
                if (checked) {whippedState = "YES"; priceForOne += 1;}
                else {whippedState = "NO"; priceForOne -= 1;}
                break;
            case R.id.topping_pepsi:
                if (checked) {pepsiState = "YES"; priceForOne += 1;}
                else {pepsiState = "NO"; priceForOne -= 1;}
                break;
            case R.id.topping_chockolate:
                if (checked) {chockoState = "YES"; priceForOne += 2;}
                else {chockoState = "NO"; priceForOne -= 2;}
                break;
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price of the order
     */
    private int calculatePrice() {
        return quantity * priceForOne;
    }

    private String createOrderSummary () {
        String name = ((EditText) findViewById(R.id.enter_name)).getText().toString();
        String summary = getString(R.string.summary_name, name);
        summary += getString(R.string.summary_divider);
        summary += getString(R.string.summary_cream, whippedState);
        summary += getString(R.string.summary_pepsi, pepsiState);
        summary += getString(R.string.summary_choco, chockoState);
        summary += getString(R.string.summary_divider);
        summary += getString(R.string.summary_quantity, String.valueOf(quantity));
        summary += getString(R.string.summary_total, String.valueOf(calculatePrice()));
        summary += getString(R.string.summary_thanks);
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}