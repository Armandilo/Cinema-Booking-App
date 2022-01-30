package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserProfilePage extends AppCompatActivity {
    private Button buttonchangepassword;
    private Button buttoneditprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);
        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}





        /*buttonchangepassword = (Button) findViewById(R.id.button4);
        buttonchangepassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                OpenChangePassword();
            }
        });

        buttoneditprofile = (Button) findViewById(R.id.button3);
        buttoneditprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { OpenEditProfiler(); }
        });
*/




    }
    //public void OpenChangePassword(){
     //   Intent intent = new Intent(this,ChangePassword.class);
     //   startActivity(intent);
   // }

   /* public void OpenEditProfiler(){
        Intent intent = new Intent(this,EditProfile.class);
        startActivity(intent);
    }*/

}

