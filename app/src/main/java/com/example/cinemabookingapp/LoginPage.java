package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends AppCompatActivity {

    private Button buttonhomepage;
    private Button buttonuserprofile;
    private TextInputLayout loginUsername;
    private TextInputLayout loginPassword;
    SharedPreferences sharedPreferences;

    //Shared preferences name and key name
    private static final String SHARED_PREF_USERNAME = "myusername";
    private static final String USERNAME_KEY = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginUsername = findViewById(R.id.textInputLayout3);
        loginPassword = findViewById(R.id.textInputLayout2);

        sharedPreferences = getSharedPreferences(SHARED_PREF_USERNAME,MODE_PRIVATE);

        //When open activity, check if data in SharedPreferences is available or not
        String username = sharedPreferences.getString(USERNAME_KEY, null);

        if (username != null){
            //If data is available, directly call on HomePage
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }

        //Set button to home page
        buttonhomepage = (Button) findViewById(R.id.button3);
        /*buttonhomepage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { OpenHomePage(); }
        });*/


        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}
    }

    //Input validation for username
    private Boolean validateUsername(){
        String usernameInput = loginUsername.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()){
            loginUsername.setError("Field cannot be empty");
            return false;
        }else if (usernameInput.length() > 10){
            loginUsername.setError("Username too long than supposed to");
            return false;
        }else{
            loginUsername.setError(null);
            loginUsername.setErrorEnabled(false);
            return true;
        }
    }

    //Input validation for password
    private Boolean validatePassword(){
        String passwordInput = loginPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()){
            loginPassword.setError("Field cannot be empty");
            return false;
        }else{
            loginPassword.setError(null);
            loginPassword.setErrorEnabled(false);
            return true;
        }

    }

    /*/Confirm all input
    public void confirmInput(View v){
        if (!validateUsername() |  !validatePassword() ){
            return;
        }
    }*/

    //Confirm input and open home page when button is clicked
    public void OpenHomePage(View v){
        if (!validateUsername() |  !validatePassword() ){
            return;
        }
        //Get and send username
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY,loginUsername.getEditText().getText().toString());
        editor.apply();
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);

        Toast.makeText(this, "Welcome back to FlixPrime " + loginUsername.getEditText().getText().toString(), Toast.LENGTH_LONG).show();

    }


}