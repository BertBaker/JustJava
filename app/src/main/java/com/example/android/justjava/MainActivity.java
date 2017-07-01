package com.example.android.justjava;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;

public class MainActivity
        extends AppCompatActivity
{
    int quantity = 0;

    private int calculatePrice(int paramInt1, int paramInt2)
    {
        return paramInt1 * paramInt2;
    }

    private String createOrderSummary(int paramInt)
    {
        boolean bool1 = ((CheckBox)findViewById(R.id.whipped_cream_checkbox)).isChecked();
        int i = paramInt;
        if (bool1) {
            i = paramInt + this.quantity * 1;
        }
        boolean bool2 = ((CheckBox)findViewById(R.id.chocolate_checkbox)).isChecked();
        paramInt = i;
        if (bool2) {
            paramInt = i + this.quantity * 2;
        }
        String str5 = getResources().getString(R.string.order_summary_whipped_cream);
        String str4 = getResources().getString(R.string.order_summary_chocolate);
        String str2 = getResources().getString(R.string.order_summary_price);
        String str3 = getResources().getString(R.string.order_summary_quantity);
        String str1 = getResources().getString(R.string.thank_you);
        str5 = "\n" + str5 + bool1;
        str4 = str5 + "\n" + str4 + bool2;
        str3 = str4 + "\n" + str3 + this.quantity;
        str2 = str3 + "\n" + str2 + paramInt;
        return str2 + "\n" + str1;
    }

    private void displayQuantity(int paramInt)
    {
        ((TextView)findViewById(R.id.quantity_text_view)).setText("" + paramInt);
    }

    public void decrement(View paramView)
    {
        if (this.quantity > 1) {
            this.quantity -= 1;
            displayQuantity(this.quantity);
            return;}
        else
        {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
    }

    public void increment(View paramView)
    {
        if (this.quantity < 100) {
            this.quantity += 1;
            displayQuantity(this.quantity);
            return;}
        else
        {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view)
    {
        Editable paramView = ((EditText) findViewById(R.id.customer_name)).getText();
        String str1 = "Name: " + paramView + createOrderSummary(calculatePrice(this.quantity, 5));
        Intent localIntent = new Intent("android.intent.action.SENDTO");
        localIntent.setData(Uri.parse("mailto:"));
        String str2 = getResources().getString(R.string.order_summary_email_subject);
        localIntent.putExtra("android.intent.extra.SUBJECT", str2 + paramView);
        localIntent.putExtra("android.intent.extra.TEXT", str1);
        if (localIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(localIntent);
        }
    }
}

