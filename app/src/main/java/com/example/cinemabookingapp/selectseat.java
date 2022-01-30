package com.example.cinemabookingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import java.util.Random;

public class selectseat extends AppCompatActivity {
    private Button button;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectseat);

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        final String[] chosenSeat = {""};
        final Integer[] counter = {0};
        String moviename = getIntent().getStringExtra("moviename");
        String chosenDate = getIntent().getStringExtra("chosenDate");
        String chosenTime = getIntent().getStringExtra("chosenTime");
        String chosenPlace = getIntent().getStringExtra("chosenPlace");

        ((TextView)findViewById(R.id.moviename)).setText(moviename);
        ((TextView)findViewById(R.id.place)).setText(chosenPlace);
        Random rand = new Random();
        int max = 10;
        int min = 1;
        int hallnumber = rand.nextInt((max - min) + 1) + min;

        String hn = String.valueOf(hallnumber);
        String hall = "Hall " + hn;
        ((TextView)findViewById(R.id.hall)).setText(hall);

        String date = chosenDate + ", " + chosenTime;
        ((TextView)findViewById(R.id.datetime)).setText(date);

        ToggleButton a1,a2,a3,a4,a5,a6,a7,
                     b1,b2,b3,b4,b5,b6,b7,
                     c1,c2,c3,c4,c5,c6,c7,
                     d1,d2,d3,d4,d5,d6,d7;

        a1 = (ToggleButton) findViewById(R.id.a1);a2 = (ToggleButton) findViewById(R.id.a2);a3 = (ToggleButton) findViewById(R.id.a3);a4 = (ToggleButton) findViewById(R.id.a4);a5 = (ToggleButton) findViewById(R.id.a5);a6 = (ToggleButton) findViewById(R.id.a6);a7 = (ToggleButton) findViewById(R.id.a7);
        b1 = (ToggleButton) findViewById(R.id.b1);b2 = (ToggleButton) findViewById(R.id.b2);b3 = (ToggleButton) findViewById(R.id.b3);b4 = (ToggleButton) findViewById(R.id.b4);b5 = (ToggleButton) findViewById(R.id.b5);b6 = (ToggleButton) findViewById(R.id.b6);b7 = (ToggleButton) findViewById(R.id.b7);
        c1 = (ToggleButton) findViewById(R.id.c1);c2 = (ToggleButton) findViewById(R.id.c2);c3 = (ToggleButton) findViewById(R.id.c3);c4 = (ToggleButton) findViewById(R.id.c4);c5 = (ToggleButton) findViewById(R.id.c5);c6 = (ToggleButton) findViewById(R.id.c6);c7 = (ToggleButton) findViewById(R.id.c7);
        d1 = (ToggleButton) findViewById(R.id.d1);d2 = (ToggleButton) findViewById(R.id.d2);d3 = (ToggleButton) findViewById(R.id.d3);d4 = (ToggleButton) findViewById(R.id.d4);d5 = (ToggleButton) findViewById(R.id.d5);d6 = (ToggleButton) findViewById(R.id.d6);d7 = (ToggleButton) findViewById(R.id.d7);




        button = (Button) findViewById(R.id.button6);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(a1.isChecked())
                {
                    chosenSeat[0] += "A1,";
                    a1.setEnabled(false);
                    counter[0]++;
                }
                if(a2.isChecked())
                {
                    chosenSeat[0] += "A2,";
                    a2.setEnabled(false);
                    counter[0]++;
                }
                if(a3.isChecked())
                {
                    chosenSeat[0] += "A3,";
                    a3.setEnabled(false);
                    counter[0]++;
                }
                if(a4.isChecked())
                {
                    chosenSeat[0] += "A4,";
                    a4.setEnabled(false);
                    counter[0]++;
                }
                if(a5.isChecked())
                {
                    chosenSeat[0] += "A5,";
                    a5.setEnabled(false);
                    counter[0]++;

                }
                if(a6.isChecked())
                {
                    chosenSeat[0] += "A6,";
                    a6.setEnabled(false);
                    counter[0]++;

                }
                if(a7.isChecked())
                {
                    chosenSeat[0] += "A7,";
                    a7.setEnabled(false);
                    counter[0]++;
                }
                if(b1.isChecked())
                {

                    chosenSeat[0] += "B1,";
                    b1.setEnabled(false);
                    counter[0]++;
                }
                if(b2.isChecked())
                {
                    chosenSeat[0] += "B2,";
                    b2.setEnabled(false);
                    counter[0]++;

                }
                if(b3.isChecked())
                {
                    chosenSeat[0] += "B3,";
                    b3.setEnabled(false);
                    counter[0]++;

                }
                if(b4.isChecked())
                {
                    chosenSeat[0] += "B4,";
                    b4.setEnabled(false);
                    counter[0]++;

                }
                if(b5.isChecked())
                {
                    chosenSeat[0] += "B5,";
                    b5.setEnabled(false);
                    counter[0]++;

                }
                if(b6.isChecked())
                {
                    chosenSeat[0] += "B6,";
                    b6.setEnabled(false);
                    counter[0]++;

                }
                if(b7.isChecked())
                {
                    chosenSeat[0] += "B7,";
                    b7.setEnabled(false);
                    counter[0]++;

                }
                if(c1.isChecked())
                {
                    chosenSeat[0] += "C1,";
                    c1.setEnabled(false);
                    counter[0]++;

                }
                if(c2.isChecked())
                {
                    chosenSeat[0] += "C2,";
                    c2.setEnabled(false);
                    counter[0]++;

                }
                if(c3.isChecked())
                {
                    chosenSeat[0] += "C3,";
                    c3.setEnabled(false);
                    counter[0]++;

                }
                if(c4.isChecked())
                {
                    chosenSeat[0] += "C4,";
                    c4.setEnabled(false);
                    counter[0]++;

                }
                if(c5.isChecked())
                {
                    chosenSeat[0] += "C5,";
                    c5.setEnabled(false);
                    counter[0]++;

                }
                if(c6.isChecked())
                {
                    chosenSeat[0] += "C6,";
                    c6.setEnabled(false);
                    counter[0]++;

                }
                if(c7.isChecked())
                {
                    chosenSeat[0] += "C7,";
                    c7.setEnabled(false);
                    counter[0]++;

                }
                if(d1.isChecked())
                {
                    chosenSeat[0] += "D1,";
                    d1.setEnabled(false);
                    counter[0]++;

                }
                if(d2.isChecked())
                {
                    chosenSeat[0] += "D2,";
                    d2.setEnabled(false);
                    counter[0]++;

                }
                if(d3.isChecked())
                {
                    chosenSeat[0] += "D3,";
                    d3.setEnabled(false);
                    counter[0]++;

                }
                if(d4.isChecked())
                {
                    chosenSeat[0] += "D4,";
                    d4.setEnabled(false);
                    counter[0]++;

                }
                if(d5.isChecked())
                {
                    chosenSeat[0] += "D5,";
                    d5.setEnabled(false);
                    counter[0]++;

                }
                if(d6.isChecked())
                {
                    chosenSeat[0] += "D6,";
                    d6.setEnabled(false);
                    counter[0]++;

                }
                if(d7.isChecked())
                {
                    chosenSeat[0] += "D7,";
                    d7.setEnabled(false);
                    counter[0]++;

                }

                String chosenSeatFinal = "";
                if(chosenSeat[0] != "")
                {
                    chosenSeatFinal = chosenSeat[0].substring(0, chosenSeat[0].lastIndexOf(","));
                }

                OpenBookingDetails(moviename,chosenTime, chosenPlace, chosenSeatFinal, counter[0], chosenDate,hall);
            }
        });





    }

    public void OpenBookingDetails(String moviename, String chosenTime, String chosenPlace, String chosenSeatFinal, Integer counter, String chosenDate, String hall){
        Intent intent = new Intent(this, BookingDetails.class);

        if(chosenSeatFinal == "" )
        {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Error")
                    .setMessage("Make sure to select seats")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

        }
        else
        {
            Bundle bundle = new Bundle();
            bundle.putString("chosenTime", chosenTime);
            intent.putExtra("moviename", moviename);
            intent.putExtra("mypackage", bundle);
            intent.putExtra("chosenPlace",chosenPlace);
            intent.putExtra("chosenSeat", chosenSeatFinal);
            intent.putExtra("quantitySeat", counter);
            intent.putExtra("chosenDate", chosenDate);
            intent.putExtra("hall", hall);
            startActivity(intent);
        }

    }






}