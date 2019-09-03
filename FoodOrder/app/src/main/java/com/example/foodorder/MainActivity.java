package com.example.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button pizzaButton,burgerButton, pastryButton;

    String choices = "";
    Double price = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizzaButton = (Button) findViewById(R.id.pizzaButton);
        burgerButton = (Button) findViewById(R.id.burgerButton);
        pastryButton = (Button) findViewById(R.id.pastryButton);
    }

    public void add_to_list(View view){
        if (view == findViewById(R.id.pizzaButton)){
            choices = choices + "Pizza" + "\n";
            price = price+1200;
        }
       else if (view == findViewById(R.id.burgerButton)){
            choices = choices + "Burger" + "\n";
            price = price+200;
        }
        else if (view == findViewById(R.id.pastryButton)){
            choices = choices + "Pastry" + "\n";
            price = price+80;
        }
    }

    public void pleaseOrder(View view){
        Intent i = new Intent(MainActivity.this,OrderDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("choices",choices);
        bundle.putDouble("price",price);
        i.putExtras(bundle);
        startActivity(i);
    }
}
