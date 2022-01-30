package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfilePage extends AppCompatActivity {
    private Button buttonlogout;
    private Button buttoneditprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        Intent callerIntent = getIntent();
        Bundle packagefromcaller = callerIntent.getBundleExtra("mypackage");
        String Username = getIntent().getStringExtra("Username");
        String Email = getIntent().getStringExtra("Email");

        //Set username
        ((TextView)findViewById(R.id.textInputLayout3)).setText(Username);

        //Set email
        ((TextView)findViewById(R.id.textInputLayout7)).setText(Email);


        buttonlogout = (Button) findViewById(R.id.button2);
        buttonlogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                OpenLogin();
            }
        });



        buttoneditprofile = (Button) findViewById(R.id.button3);
        buttoneditprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { OpenEditProfiler(); }
        });



        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

    }


    public void OpenEditProfiler(){
        Intent intent = new Intent(this,EditProfile.class);
        startActivity(intent);
    }
    public void OpenLogin(){
        Intent intent = new Intent(this,LoginPage.class);
        startActivity(intent);
    }

}

