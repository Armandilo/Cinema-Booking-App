package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class selectseat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectseat);

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

    }






}