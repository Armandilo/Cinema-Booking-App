package com.example.cinemabookingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class selectdatetime extends AppCompatActivity {

    private RadioButton rb1 , rb2, rb3, rb4, rb5, rb6, rb7, rb8,rb9,rb10,rb11,rb12,rb13,rb14;
    private Button button;

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdatetime);

        String moviename = getIntent().getStringExtra("moviename");

        String chosenDate;
        String chosenTime;

        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        rb5 = (RadioButton) findViewById(R.id.radioButton5);
        rb6 = (RadioButton) findViewById(R.id.radioButton6);
        rb7 = (RadioButton) findViewById(R.id.radioButton7);
        rb8 = (RadioButton) findViewById(R.id.radioButton8);

        if(rb1.isChecked())
            chosenDate = "11 NOV";
        else if(rb2.isChecked())
            chosenDate = "12 NOV";
        else if(rb3.isChecked())
            chosenDate = "13 NOV";
        else if(rb4.isChecked())
            chosenDate = "14 NOV";
        else if(rb5.isChecked())
            chosenDate = "15 NOV";
        else if(rb6.isChecked())
            chosenDate = "16 NOV";
        else if(rb7.isChecked())
            chosenDate = "17 NOV";
        else if(rb8.isChecked())
            chosenDate = "18 NOV";
        else
            chosenDate = "null";

        rb9 = (RadioButton) findViewById(R.id.radioButton10);
        rb10 = (RadioButton) findViewById(R.id.radioButton9);
        rb11 = (RadioButton) findViewById(R.id.radioButton11);
        rb12 = (RadioButton) findViewById(R.id.radioButton12);
        rb13 = (RadioButton) findViewById(R.id.radioButton14);
        rb14 = (RadioButton) findViewById(R.id.radioButton13);

        String chosenPlace = "null";
        if(rb9.isChecked())
        {
            chosenTime = "09:00";
            chosenPlace = "Putrajaya - IOI City Mall";
        }
        else if(rb10.isChecked())
        {
            chosenTime = "12:00";
            chosenPlace = "Putrajaya - IOI City Mall";
        }
        else if(rb11.isChecked())
        {
            chosenTime = "16:00";
            chosenPlace = "Putrajaya - IOI City Mall";
        }
        else if(rb12.isChecked())
        {
            chosenTime = "09:00";
            chosenPlace = "Putrajaya - Alamanda";
        }
        else if(rb13.isChecked())
        {
            chosenTime = "12:00";
            chosenPlace = "Putrajaya - Alamanda";
        }
        else if(rb14.isChecked())
        {
            chosenTime = "16:00";
            chosenPlace = "Putrajaya - Alamanda";
        }
        else
            chosenTime = "null";

        button = (Button) findViewById(R.id.toseatpage);
        String finalChosenPlace = chosenPlace;
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                OpenSelectSeat(moviename,chosenDate,chosenTime, finalChosenPlace);
            }
        });


    }

    public void OpenSelectSeat(String moviename, String chosenDate,String chosenTime, String chosenPlace){
        Intent intent = new Intent(this, selectseat.class);

        if(chosenDate == "null" || chosenTime == "null")
        {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Error")
                    .setMessage("Make sure to select both Date and Time to continue")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

        }
        else
        {
            intent.putExtra("moviename", moviename);
            intent.putExtra("chosenDate",chosenDate);
            intent.putExtra("chosenTime",chosenTime);
            intent.putExtra("chosenPlace",chosenPlace);
            startActivity(intent);
        }

    }



}