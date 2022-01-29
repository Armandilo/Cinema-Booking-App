package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;


public class RegisterPage extends AppCompatActivity {
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                                                                    "(?=.*[0-9])" + //at least 1 digit
                                                                    "(?=.*[a-z])" + //at least 1 lowercase letter
                                                                    "(?=.*[A-Z])" + //at least 1 uppercase letter
                                                                    "(?=.*[@#$%^&+=])" + //at least 1 special character
                                                                    "(?=\\S+$)" + //no white spaces
                                                                    ".{6,}" + //at least 6 characters
                                                                    "$");
    public static final Pattern EMAIL_ADDRESS = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                                                                "\\@"+
                                                                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                                                                "(" +
                                                                    "\\." +
                                                                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                                                                ")+"
                                            );

    private TextInputLayout regUsername;
    private TextInputLayout regEmail;
    private TextInputLayout regPassword;
    private TextInputLayout regConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        regUsername = findViewById(R.id.textInputLayout3);
        regEmail = findViewById(R.id.textInputLayout7);
        regPassword = findViewById(R.id.textInputLayout2);
        regConfirmPassword = findViewById(R.id.textInputLayout4);
    }

    //Input validation for username
    private Boolean validateUsername(){
        String usernameInput = regUsername.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }else if (usernameInput.length() > 10){
            regUsername.setError("Username too long");
            return false;
        }else{
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    //Input validation for email
    private Boolean validateEmail(){
        String emailInput = regEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }else if(!EMAIL_ADDRESS.matcher(emailInput).matches()){
            regEmail.setError("Please enter a valid email address.");
            return false;
        }
        else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }

    }

    //Input validation for password
    private Boolean validatePassword(){
        String passwordInput = regPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            regPassword.setError("Password too weak");
            return false;
        }
        else{
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }

    }

    //Input validation for password confirmation
    private Boolean validateConfirmPassword(){
        String passwordConfirmInput = regConfirmPassword.getEditText().getText().toString().trim();

        if (passwordConfirmInput.isEmpty()){
            regConfirmPassword.setError("Field cannot be empty");
            return false;
        }else if (passwordConfirmInput != regPassword.getEditText().getText().toString().trim()){
            regConfirmPassword.setError("Password is not the same.");
            return false;
        }else{
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }

    }

    //Confirm all input
    public void confirmInput(View v){
        if (!validateUsername() | !validateEmail() | !validatePassword() | validateConfirmPassword() ){
            return;
        }

        Toast.makeText(this, "Registration successful, please login.", Toast.LENGTH_SHORT).show();
    }
}