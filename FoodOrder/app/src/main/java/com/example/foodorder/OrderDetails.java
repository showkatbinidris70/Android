package com.example.foodorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetails extends AppCompatActivity {

    TextView listView, priceView;
    String list_choice;
    Double price_bd, price_usd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        listView = (TextView) findViewById(R.id.listView);
        priceView = (TextView) findViewById(R.id.priceView);


        Bundle bundle = getIntent().getExtras();
        list_choice = bundle.getString("choices");
        price_bd = bundle.getDouble("price");

        price_usd = price_bd*79.0;


        listView.setText(list_choice);
        priceView.setText("BDT :" + price_bd.toString()+ "USD :" + price_usd.toString());
    }
}
