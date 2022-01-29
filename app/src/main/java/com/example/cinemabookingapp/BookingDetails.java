package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BookingDetails extends AppCompatActivity {

    private Button buttoncheckoutpage, redeemPromoCode;
    private CheckBox termsCondition;
    public Double priceDeducted = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        /*/Get intent from previous activity
        //Get number of tickets
        Intent k = getIntent();
        Integer ticketNum = k.getIntExtra("ticketNum", 1);

        //Get name of movie
        Intent i = getIntent();
        String movieName = i.getStringExtra("movieName");

        //Get time of movie
        Intent j = getIntent();
        String movieTime = j.getStringExtra("movieTime");

        //Get date of movie
        Intent l = getIntent();
        String movieDate = l.getStringExtra("movieDate");

        //Set name of movie in activity
        ((TextView)findViewById(R.id.movieNameDisplay)).setText(movieName);

        //Set time of movie in activity
        ((TextView)findViewById(R.id.movieTimeDisplay)).setText(movieTime);*/

        //Promo Code
        EditText promoCode = (EditText) findViewById(R.id.promoCode);
        redeemPromoCode = (Button) findViewById(R.id.button4);

        //Click Redeem button
        redeemPromoCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(promoCode.getText().toString()) == 24587){
                    ((TextView)findViewById(R.id.promoPrice)).setText("- RM5.00");
                    priceDeducted = 5.0;
                }else if(Integer.valueOf(promoCode.getText().toString()) == 29876){
                    ((TextView)findViewById(R.id.promoPrice)).setText("- RM2.00");
                    priceDeducted = 2.0;
                }else if(Integer.valueOf(promoCode.getText().toString()) == 23765){
                    ((TextView)findViewById(R.id.promoPrice)).setText("- RM4.00");
                    priceDeducted = 4.0;
                } else{
                    Toast.makeText(BookingDetails.this, "Invalid promo code.", Toast.LENGTH_LONG).show();
            }
            }
        });


        /*/Total Price
        Double totalPrice = 0.0;
        totalPrice = (Double.valueOf(ticketNum) * 14) + priceDeducted;
        BigDecimal bd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
        double newTotalPrice = bd.doubleValue();
        ((TextView)findViewById(R.id.totalPrice)).setText("RM" + newTotalPrice);

        //Set button to checkout page*/
        buttoncheckoutpage = (Button) findViewById(R.id.button6);
        termsCondition = (CheckBox) findViewById(R.id.checkBox);
        buttoncheckoutpage.setVisibility(View.GONE);

        termsCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(termsCondition.isChecked()){
                    buttoncheckoutpage.setVisibility(View.VISIBLE);
                }
                else{
                    buttoncheckoutpage.setVisibility(View.GONE);
                }
            }
        });

        buttoncheckoutpage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { OpenCheckoutPage(); }
        });

    }

    //Open checkout page when button is clicked
    public void OpenCheckoutPage(){
        Intent intent = new Intent(this,CheckoutPage.class);
        startActivity(intent);
    }
}