package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}
    }
}

