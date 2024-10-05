package com.example.intent_order;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private EditText drinkQuentity,waffleQ,pancakeQ,mulffinQ,steakQ;
    private RadioGroup drinkGroup;
    private RadioButton menuCola,menuTea,menuCoffee;
    private CheckBox checkWaffle,checkPancake,checkMulffin,checkSteak;
    private Button buttonCancel,buttonCheckOut;
    private TextView textResult;
    private String choseDrink;
    private int drinkprice,sum;
    private final int OrderCode=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Order");


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        textResult = (TextView) findViewById(R.id.textView_order);

        drinkQuentity = (EditText) findViewById(R.id.editTextNumber_drinkNum);
        waffleQ = (EditText) findViewById(R.id.editTextNumber_waffle);
        pancakeQ = (EditText) findViewById(R.id.editTextNumber_pancake);
        mulffinQ = (EditText) findViewById(R.id.editTextNumber_mulffin);
        steakQ = (EditText) findViewById(R.id.editTextNumber_steak);

        drinkGroup = (RadioGroup) findViewById(R.id.radioGroup_drink);
        menuCola = (RadioButton) findViewById(R.id.radioButton_cola);
        menuTea = (RadioButton) findViewById(R.id.radioButton_tea);
        menuCoffee = (RadioButton) findViewById(R.id.radioButton_coffee);

        checkWaffle = (CheckBox) findViewById(R.id.checkBox_Waffle);
        checkPancake = (CheckBox) findViewById(R.id.checkBox_pancake);
        checkMulffin = (CheckBox) findViewById(R.id.checkBox_Mulffin);
        checkSteak = (CheckBox) findViewById(R.id.checkBox_Steak);

        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonCheckOut = (Button) findViewById(R.id.button_checkout);

        buttonCancel.setOnClickListener(new MyButton());
        buttonCheckOut.setOnClickListener(new MyButton());


        drinkprice = 0;
        drinkGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_cola:
                        choseDrink = "Cola $50";
                        drinkprice = 50;
                        break;
                    case R.id.radioButton_tea:
                        choseDrink = "Tea $60";
                        drinkprice = 60;
                        break;
                    case R.id.radioButton_coffee:
                        choseDrink = "Coffee $80";
                        drinkprice = 80;
                        break;
                }
            }
        });

    }

    private void Ordered() {
        if(drinkQuentity.length()==0){
            Toast.makeText(OrderActivity.this, "Please input number", Toast.LENGTH_SHORT).show();
        }else {
            textResult.setText("Your order is : \n");
            sum = 0;
            if(drinkprice == 0) {
                Toast.makeText(OrderActivity.this, "Please select drink", Toast.LENGTH_SHORT).show();
            }else {
                sum += drinkprice * Integer.parseInt(drinkQuentity.getText().toString());
                textResult.append("Drink : " + choseDrink+ " x "+ drinkQuentity.getText()+"\n");
                Log.d("main","sum="+sum);
                FoodOrder(sum);
            }
        }
    }

    private void FoodOrder( int sum) {
        textResult.append("Food : \n");
        if(checkWaffle.isChecked()){
            if(waffleQ.length() == 0) {
                Toast.makeText(OrderActivity.this, "Please input Waffle Number", Toast.LENGTH_SHORT).show();
            }else {
                sum += 100 * Integer.parseInt(waffleQ.getText().toString());
                textResult.append("Waffle ,$100 x " + waffleQ.getText() + "\n");
            }
        }
        if(checkPancake.isChecked()){
            if(pancakeQ.length() == 0) {
                Toast.makeText(OrderActivity.this, "Please input Pancake Number", Toast.LENGTH_SHORT).show();
            }else {
                sum += 120 * Integer.parseInt(pancakeQ.getText().toString());
                textResult.append("Pancake ,$120 x " + pancakeQ.getText() + "\n");
            }
        }
        if(checkMulffin.isChecked()){
            if(mulffinQ.length() == 0) {
                Toast.makeText(OrderActivity.this, "Please input Mulffin Number", Toast.LENGTH_SHORT).show();
            }else {
                sum += 150 * Integer.parseInt(mulffinQ.getText().toString());
                textResult.append("Mulffin ,$150 x " + mulffinQ.getText() + "\n");
            }
        }
        if(checkSteak.isChecked()){
            if(steakQ.length() == 0) {
                Toast.makeText(OrderActivity.this, "Please input Steak Number", Toast.LENGTH_SHORT).show();
            }else {
                sum += 200 * Integer.parseInt(steakQ.getText().toString());
                textResult.append("Steak ,$200 x " + steakQ.getText() + "\n");
            }
        }
        textResult.append("Total price : "+ sum);

        AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
        builder.setTitle("Checkout");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage(textResult.getText().toString());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.putExtra("order",textResult.getText().toString());
                setResult(OrderCode,intent);
                dialog.dismiss();
                finish();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    private void clear() {
        mulffinQ.setText("");
        steakQ.setText("");
        pancakeQ.setText("");
        waffleQ.setText("");
        drinkQuentity.setText("");
        textResult.setText("");
        checkMulffin.setChecked(false);
        checkPancake.setChecked(false);
        checkSteak.setChecked(false);
        checkWaffle.setChecked(false);
        drinkGroup.clearCheck();
    }

    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_cancel:
                    clear();
                    break;
                case R.id.button_checkout:
                    textResult.setVisibility(View.INVISIBLE);
                    Ordered();
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}