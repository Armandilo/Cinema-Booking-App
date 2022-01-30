package com.example.cinemabookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Pattern;


public class RegisterPage extends AppCompatActivity {
    public static final Pattern EMAIL_ADDRESS = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                                                                "\\@"+
                                                                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                                                                "(" +
                                                                    "\\." +
                                                                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                                                                ")+"
                                            );

    private TextInputEditText regUsername;
    private TextInputEditText regEmail;
    private TextInputEditText regPassword;
    private TextInputEditText regConfirmPassword;
    FirebaseAuth mAuth;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        regUsername = findViewById(R.id.regUsername);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regConfirmPassword = findViewById(R.id.regConfirmPassword);
        mAuth = FirebaseAuth.getInstance();
        register = (Button) findViewById(R.id.button3);

        register.setOnClickListener(view ->
        {
            createUser();
        });


    }

    private void createUser(){
        String email = regEmail.getText().toString();
        String password = regPassword.getText().toString();
        String username = regUsername.getText().toString();

        if (TextUtils.isEmpty(email)){
            regEmail.setError("Email cannot be empty");
            regEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            regPassword.setError("Password cannot be empty");
            regPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username).build();

                        user.updateProfile(profileUpdates);
                        startActivity(new Intent(RegisterPage.this, LoginPage.class));
                    }else{
                        Toast.makeText(RegisterPage.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}