package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonlogin;
    private Button buttonregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonlogin = (Button) findViewById(R.id.button2);
        buttonlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                OpenLogin();
            }
        });

        buttonregister = (Button) findViewById(R.id.button);
        buttonregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { OpenRegister(); }
        });

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

    }
    public void OpenLogin(){
        Intent intent = new Intent(this,LoginPage.class);
        startActivity(intent);
    }

    public void OpenRegister(){
        Intent intent = new Intent(this,RegisterPage.class);
        startActivity(intent);
    }

}