package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class EditProfile extends AppCompatActivity {
    EditText username, Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        viewInitializations();
    }

    void viewInitializations() {
        username = findViewById(R.id.textInputLayout3);
        Email = findViewById(R.id.textInputLayout4);

        // To show back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Checking if the input in form is valid
    boolean validateInput() {
        if (username.getText().toString().equals("")) {
            username.setError("Please Enter your  username");
            return false;
        }


        if (Email.getText().toString().equals("")) {
            Email.setError("Please Enter Email");
            return false;
        }

        // checking the proper email format

        if (!isEmailValid(Email.getText().toString())) {
            Email.setError("Please Enter Valid Email");
            return false;
        }

        return true;
    }

    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public void performEditProfile (View v) {
        if (validateInput()) {

            // Input is valid, here send data to your server

            String firstName = username.getText().toString();

            String email = Email.getText().toString();

            Toast.makeText(this,"Profile Update Successfully",Toast.LENGTH_SHORT).show();
            // Here you can call you API

        }
    }
}
