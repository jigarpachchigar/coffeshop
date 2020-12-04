package com.order.coffeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    EditText etqty,ettamt,etfamt;
    TextView tvmsg;
    RadioButton rb1,rb2,rb3;
    CheckBox cb1,cb2,cb3;
    Button btnorder;

    double coffeeprice = 0;
    double coffeesize = 0;
    double subtotal=0;
    double tax = 0.13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etqty = findViewById(R.id.etqty);
        ettamt = findViewById(R.id.ettotal);
        etfamt = findViewById(R.id.etfamt);

        btnorder = findViewById(R.id.btnorder);

        btnorder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                double qty = Double.parseDouble(etqty.getText().toString());
                subtotal = Double.parseDouble(ettamt.getText().toString());
                subtotal = subtotal * qty;
                subtotal += tax;
                etfamt.setText(String.format("%.2f", subtotal));
            }
        });

        tvmsg = findViewById(R.id.tvnote);
        rb1 = findViewById(R.id.rbsmall);
        rb2 = findViewById(R.id.rbmed);
        rb3 = findViewById(R.id.rbextra);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);


        cb1 = findViewById(R.id.cbexcm);
        cb2 = findViewById(R.id.cbexsug);
        cb3 = findViewById(R.id.cbexmilk);

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);


        Spinner sp = findViewById(R.id.sptypes);
        ArrayList<String> drinkList = new ArrayList<>();
        drinkList.add("Coffee");
        drinkList.add("Hot Choclate");
        drinkList.add("Tea");
        drinkList.add("Cappuccino");
        drinkList.add("Espresso");

        ArrayAdapter<String> drinkAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, drinkList);
        drinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(drinkAdapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);

                coffeeprice = 0;
                ettamt.setText(String.format("%.2f", coffeeprice));

                String drinkName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + drinkName, Toast.LENGTH_LONG).show();

                int getid = parent.getSelectedItemPosition();

                if (getid == 0)
                {
                    ettamt.setText("");
                    coffeeprice = 1.7;
                    ettamt.setText(String.format("%.2f",coffeeprice));
                }

                else if (getid == 1)
                {
                    ettamt.setText("");
                    coffeeprice = 1.3;
                    ettamt.setText(String.format("%.2f",coffeeprice));
                }

                else if (getid == 2)
                {
                    ettamt.setText("");
                    coffeeprice = 1.5;
                    ettamt.setText(String.format("%.2f",coffeeprice));
                }

                else if (getid == 3)
                {
                    ettamt.setText("");
                    coffeeprice = 2.2;
                    ettamt.setText(String.format("%.2f",coffeeprice));
                }

                else
                {
                    ettamt.setText("");
                    coffeeprice = 0.9;
                    ettamt.setText(String.format("%.2f",coffeeprice));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        if(rb1.isChecked()) {
            coffeesize = 0;
            double p1 = Double.parseDouble(ettamt.getText().toString());
            coffeeprice = p1 * coffeesize;
            ettamt.setText(String.format("%.2f", coffeeprice));
        }

        else if(rb2.isChecked()) {
            coffeesize = 1.5;
            double p2 = Double.parseDouble(ettamt.getText().toString());
            coffeeprice = p2 * coffeesize;
            ettamt.setText(String.format("%.2f", coffeeprice));
        }

        else if(rb3.isChecked()) {
            coffeesize = 2.0;
            double p3 = Double.parseDouble(ettamt.getText().toString());
            coffeeprice = p3 * coffeesize;
            ettamt.setText(String.format("%.2f", coffeeprice));
        }

        else
        {
            tvmsg.setText("Select valid option");
        }


        if(cb1.isChecked()) {
            double c1 = Double.parseDouble(ettamt.getText().toString());
            double ex1 = c1 + 0.5;
            coffeesize = ex1;
            ettamt.setText(String.format("%.2f", coffeeprice));

        }

        if(cb2.isChecked()) {
            double c2 = Double.parseDouble(ettamt.getText().toString());
            double ex2 = c2 + 0;
            coffeesize = ex2;
            ettamt.setText(String.format("%.2f", coffeeprice));

        }

        if(cb3.isChecked()) {
            double c3 = Double.parseDouble(ettamt.getText().toString());
            double ex3 = c3 + 0.25;
            coffeesize = ex3;
            ettamt.setText(String.format("%.2f", coffeeprice));

        }


        }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        double coffeeprice = Double.parseDouble(ettamt.getText().toString());

        if(compoundButton.getId()==R.id.cbexcm) {
            if (cb1.isChecked())
                coffeeprice += 0.5;
            else
                coffeeprice -= 0.5;

            ettamt.setText(String.format("%.2f", coffeeprice));
        }

        if(compoundButton.getId()==R.id.cbexsug) {
            if (cb2.isChecked())
                coffeeprice += 0;
            else
                coffeeprice -= 0;

            ettamt.setText(String.format("%.2f", coffeeprice));
        }

        if(compoundButton.getId()==R.id.cbexmilk){
            if(cb3.isChecked())
                coffeeprice += 0.25;
            else
                coffeeprice -= 0.25;
            ettamt.setText(String.format("%.2f",coffeeprice));
        }

        if(compoundButton.getId()==R.id.rbsmall){
            if(rb1.isSelected()) {
                coffeesize = 0;
                double p1 = Double.parseDouble(ettamt.getText().toString());
                coffeeprice = p1 * coffeesize;
                ettamt.setText(String.format("%.2f", coffeeprice));
            }
            else {
                coffeesize = 0;
                ettamt.setText(String.format("%.2f", coffeeprice));
            }
        }

        if(compoundButton.getId()==R.id.rbmed){
            if(rb2.isSelected()) {
                coffeesize = 0;
                double p2 = Double.parseDouble(ettamt.getText().toString());
                coffeeprice = p2 * coffeesize;
                ettamt.setText(String.format("%.2f", coffeeprice));
            }
            else {
                coffeesize = 0;
                ettamt.setText(String.format("%.2f", coffeeprice));
            }
        }

        if(compoundButton.getId()==R.id.rbextra){
            if(rb3.isSelected()) {
                coffeesize = 0;
                double p3 = Double.parseDouble(ettamt.getText().toString());
                coffeeprice = p3 * coffeesize;
                ettamt.setText(String.format("%.2f", coffeeprice));
            }
            else {
                coffeesize = 0;
                ettamt.setText(String.format("%.2f", coffeeprice));
            }
        }


    }

}