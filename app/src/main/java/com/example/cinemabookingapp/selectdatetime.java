package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class selectdatetime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdatetime);
    }

    public void onCustomToggleClick(View view) {
        Toast.makeText(this, "CustomToggle", Toast.LENGTH_SHORT).show();
    }
}