package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    private Button buttonhomepage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //Set button to home page
        buttonhomepage = (Button) findViewById(R.id.button3);
        buttonhomepage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { OpenHomePage(); }
        });

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}
    }

    //Open home page when button is clicked
    public void OpenHomePage(){
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }
}