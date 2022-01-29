package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TicketDetails extends AppCompatActivity {

    ListView listView;
    ArrayList<String> movieNameItem, movieDateItem, paymentMethodItem, paymentStatusItem;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
    }
}