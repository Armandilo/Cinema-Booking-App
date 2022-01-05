package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                OpenLogin();
            }
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

}