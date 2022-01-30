package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class UserProfilePage extends AppCompatActivity {
    private Button Logout;
    public FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public String username;
    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);
        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        username = user.getDisplayName();
        email = user.getEmail();

        ((TextView)findViewById(R.id.username_field)).setText(email);
        ((TextView)findViewById(R.id.fullname_field)).setText(username);
        TextInputEditText username_field;
        username_field = (TextInputEditText) findViewById(R.id.username);
        username_field.setText(username);

        TextInputEditText email_field;
        email_field = (TextInputEditText) findViewById(R.id.email);
        email_field.setText(email);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

        //Perform ItemSelectedList
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_tickets:
                    startActivity(new Intent(getApplicationContext(),Tickets.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        Logout = (Button) findViewById(R.id.button2);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReturnMainPage();
            }
        });



    }

    public void ReturnMainPage()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}

