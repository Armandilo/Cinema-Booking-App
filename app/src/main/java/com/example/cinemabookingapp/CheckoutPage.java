package com.example.cinemabookingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
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
    public String paymentMode=null, paymentStatus=null, movieName="Eternals", movieDate="20 NOV";//You should convert movieName to movieDate to get Intent
    //Get intent from BookingDetails for movieName, movieDate (for now, I included dummy data for this)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);

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
                                    Intent intent = new Intent(CheckoutPage.this, Tickets.class);
                                    //Get and send payment mode and status
                                    paymentMode = "Cash";
                                    paymentStatus = "Pending";
                                    movieName = "Eternals"; //Change this to the data from previous activity
                                    movieDate = "20 NOV"; //This too
                                    intent.putExtra("paymentMode", paymentMode);
                                    intent.putExtra("paymentStatus", paymentStatus);
                                    intent.putExtra("movieName",movieName);
                                    intent.putExtra("movieDate", movieDate);
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
                                        Intent intent = new Intent(CheckoutPage.this, Tickets.class);
                                        //Get and send payment mode and status
                                        paymentMode = "Debit Card";
                                        paymentStatus = "Successful";
                                        movieName = "Eternals"; //Change this to the data from previous activity
                                        movieDate = "20 NOV"; //This too
                                        intent.putExtra("paymentMode", paymentMode);
                                        intent.putExtra("paymentStatus", paymentStatus);
                                        intent.putExtra("movieName",movieName);
                                        intent.putExtra("movieDate", movieDate);
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