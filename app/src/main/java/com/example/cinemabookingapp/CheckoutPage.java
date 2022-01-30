package com.example.cinemabookingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class CheckoutPage extends AppCompatActivity {

    //Object declaration
    CardForm cardForm;
    Button pay;
    AlertDialog.Builder alertBuilder, alertBuilder2;
    private CheckBox payCash, payDebitCard;
    public String paymentMode=null, paymentStatus=null, chosenDate=null, movieName=null;

    SharedPreferences sharedPreferences;

    //Shared preferences name and key name for paymentMode, paymentStatus, chosenDate, movieName
    private static final String SHARED_PREF_MOVNAME = "myMovieName";
    private static final String MOVNAME_KEY = "movieName";
    //private static final String SHARED_PREF_CHOSENDATE = "myChosenDate";
    private static final String CHOSENDATE_KEY = "chosenDate";
    //private static final String SHARED_PREF_PAYMODE = "myPayMode";
    private static final String PAYMODE_KEY = "payMode";
    //private static final String SHARED_PREF_PAYSTATUS = "myPayStatus";
    private static final String PAYSTATUS_KEY = "payStatus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);

        sharedPreferences = getSharedPreferences(SHARED_PREF_MOVNAME,MODE_PRIVATE);
        //sharedPreferences = getSharedPreferences(SHARED_PREF_CHOSENDATE,MODE_PRIVATE);
        //sharedPreferences = getSharedPreferences(SHARED_PREF_PAYMODE,MODE_PRIVATE);
        //sharedPreferences = getSharedPreferences(SHARED_PREF_PAYSTATUS,MODE_PRIVATE);

        //Check box
        payCash = findViewById(R.id.checkBox2);
        payDebitCard = findViewById(R.id.checkBox3);
        pay = findViewById(R.id.payBtn);

        //cardForm.setCardholderNameIcon();

        cardForm = findViewById(R.id.card_form);
        cardForm.cardRequired(true).cardholderName(CardForm.FIELD_REQUIRED).expirationRequired(true).cvvRequired(true).setup(CheckoutPage.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        cardForm.setEnabled(false);

        //Execute when payCash checkbox is checked
        payCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (payCash.isChecked()) {
                    //Disable payDebitCard layout
                    ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout7);
                    for (int i = 0; i < layout.getChildCount(); i++) {
                        View child = layout.getChildAt(i);
                        child.setEnabled(false);
                    }
                    //Execute when pay button is clicked
                    pay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view4) {
                            alertBuilder = new AlertDialog.Builder(CheckoutPage.this);
                            alertBuilder.setTitle("Confirm before purchase");
                            alertBuilder.setMessage("You choose to pay by cash at the cinema counter.");
                            alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Toast.makeText(CheckoutPage.this, "Payment must be made at the cinema counter before watching the movie.", Toast.LENGTH_LONG).show();
                                    OpenTicketsPage();
                                }

                                private void OpenTicketsPage() {
                                    paymentMode = "Cash";
                                    paymentStatus = "Pending";
                                    chosenDate = getIntent().getStringExtra("chosenDate");
                                    movieName = getIntent().getStringExtra("movieName");
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(MOVNAME_KEY,movieName);
                                    editor.putString(CHOSENDATE_KEY,chosenDate);
                                    editor.putString(PAYMODE_KEY,paymentMode);
                                    editor.putString(PAYSTATUS_KEY,paymentStatus);
                                    editor.apply();
                                    Intent intent = new Intent(CheckoutPage.this, Tickets.class);
                                    startActivity(intent);
                                }
                            });
                            alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alertDialog = alertBuilder.create();
                            alertDialog.show();
                        }
                    });
                } else {
                    //Enable payDebitCard layout when button is unchecked
                    ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout7);
                    for (int i = 0; i < layout.getChildCount(); i++) {
                        View child = layout.getChildAt(i);
                        child.setEnabled(true);
                    }
                }
            }
        });

        //Execute when payDebitCard checkbox is checked
        payDebitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                if (payDebitCard.isChecked()) {
                    //Disable payCash layout
                    ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout5);
                    ConstraintLayout layout2 = (ConstraintLayout) findViewById(R.id.constraintLayout7);
                    for (int i = 0; i < layout.getChildCount(); i++) {
                        View child = layout.getChildAt(i);
                        child.setEnabled(false);
                    }
                    //Enable payDebitCard layout
                    for (int i = 0; i < layout2.getChildCount(); i++) {
                        View child = layout2.getChildAt(i);
                        child.setEnabled(true);
                    }
                    //Execute when pay button is clicked
                    pay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view5) {
                            //User input validation
                            if (cardForm.isValid()){
                                alertBuilder2 = new AlertDialog.Builder(CheckoutPage.this);
                                alertBuilder2.setTitle("Confirm before purchase");
                                alertBuilder2.setMessage("Name: " + cardForm.getCardholderName() + "\n" +
                                        "Card number: " + cardForm.getCardNumber() + "\n" +
                                        "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                                        "Card CVV: " + cardForm.getCvv() + "\n");
                                alertBuilder2.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        Toast.makeText(CheckoutPage.this, "Payment successful, your account has been deducted.", Toast.LENGTH_LONG).show();
                                        OpenTicketsPage();
                                    }
                                    private void OpenTicketsPage() {
                                        paymentMode = "Credit/Debit Card";
                                        paymentStatus = "Successful";
                                        chosenDate = getIntent().getStringExtra("chosenDate");
                                        movieName = getIntent().getStringExtra("movieName");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(MOVNAME_KEY,movieName);
                                        editor.putString(CHOSENDATE_KEY,chosenDate);
                                        editor.putString(PAYMODE_KEY,paymentMode);
                                        editor.putString(PAYSTATUS_KEY,paymentStatus);
                                        editor.apply();
                                        Intent intent = new Intent(CheckoutPage.this, Tickets.class);
                                        startActivity(intent);
                                    }
                                });
                                alertBuilder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                AlertDialog alertDialog = alertBuilder2.create();
                                alertDialog.show();
                            }else {
                                Toast.makeText(CheckoutPage.this, "Please complete the form.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    //Enable payCash layout
                    ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout5);
                    for (int i = 0; i < layout.getChildCount(); i++) {
                        View child = layout.getChildAt(i);
                        child.setEnabled(true);
                    }
                }
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                //Input Validation
                if (!payCash.isChecked() && !payDebitCard.isChecked()) {
                    pay.setEnabled(false);
                    Toast.makeText(CheckoutPage.this, "Please choose one payment option.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}