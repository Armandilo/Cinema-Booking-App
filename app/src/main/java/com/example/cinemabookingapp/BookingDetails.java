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

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BookingDetails extends AppCompatActivity {

    private Button buttoncheckoutpage, redeemPromoCode;
    private CheckBox termsCondition;
    public Double priceDeducted = 0.0;
    //Get intent from previous activity for movieName, movieDate, movieTime, ticketNum

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        //Get intent from previous activity
        //Get number of tickets
        Intent callerIntent = getIntent();
        Bundle packagefromcaller = callerIntent.getBundleExtra("mypackage");
        String chosenTime = packagefromcaller.getString("chosenTime");
        String chosenSeat = getIntent().getStringExtra("chosenSeat");
        Integer ticketNum = getIntent().getIntExtra("quantitySeat", 1);
        String chosenDate = getIntent().getStringExtra("chosenDate");
        String hall = getIntent().getStringExtra("hall");

        String movieName = getIntent().getStringExtra("moviename");

        //Get time of movie


        String CinemaLocation = getIntent().getStringExtra("chosenPlace");



        //Set name of movie in activity
        ((TextView)findViewById(R.id.movieNameDisplay)).setText(movieName);

        //Set time of movie in activity
        ((TextView)findViewById(R.id.movieTimeDisplay)).setText(chosenTime);

        //Set Location of Cinema in Activity
        ((TextView)findViewById(R.id.CinemaLocation)).setText(CinemaLocation);
        ((TextView)findViewById(R.id.quantity)).setText(String.valueOf(ticketNum));

        //Promo Code
        EditText promoCode = (EditText) findViewById(R.id.promoCode);
        redeemPromoCode = (Button) findViewById(R.id.button4);

        ((TextView)findViewById(R.id.promoPrice)).setText("- RM0.00");
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


        //Total Price
        Double totalPrice = 0.0;
        totalPrice = (ticketNum * 14) - priceDeducted;
        BigDecimal bd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
        double newTotalPrice = bd.doubleValue();
        ((TextView)findViewById(R.id.totalPrice)).setText("RM" + String.valueOf(newTotalPrice));

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
            public void onClick(View v) { OpenCheckoutPage(movieName,chosenTime,chosenSeat,CinemaLocation,chosenDate,hall); }
        });

    }

    //Open checkout page when button is clicked
    public void OpenCheckoutPage(String movieName,String chosenTime, String chosenSeat, String CinemaLocation,String chosenDate,String hall){
        Intent intent = new Intent(this,CheckoutPage.class);
        intent.putExtra("movieName", movieName);
        intent.putExtra("chosenTime", chosenTime);
        intent.putExtra("chosenSeat", chosenSeat);
        intent.putExtra("CinemaLocation", CinemaLocation);
        intent.putExtra("chosenDate", chosenDate);
        intent.putExtra("hall",hall);
        startActivity(intent);
    }
}