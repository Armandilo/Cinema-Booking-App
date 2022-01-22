package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.cinemabookingapp.adapter.CurrentMoviesAdapter;
import com.example.cinemabookingapp.adapter.TicketsAdapter;
import com.example.cinemabookingapp.adapter.TicketsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Tickets extends AppCompatActivity {

    private RecyclerView ticketsRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Tickets Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_tickets);


        ticketsRecycler = findViewById(R.id.ticketsRecycler);
        ticketsRecycler.setHasFixedSize(true);
        ticketsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Integer> currentTicket = new ArrayList<>();

        currentTicket.add(R.drawable.shangchi_ticket);
        currentTicket.add(R.drawable.shangchi_ticket);

        TicketsAdapter ticketAdapter = new TicketsAdapter(currentTicket);
        ticketsRecycler.setAdapter(ticketAdapter);

    }


}