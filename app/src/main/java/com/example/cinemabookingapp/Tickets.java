package com.example.cinemabookingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cinemabookingapp.adapter.CurrentMoviesAdapter;
import com.example.cinemabookingapp.adapter.TicketsAdapter;
import com.example.cinemabookingapp.adapter.TicketsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Tickets extends AppCompatActivity {

    private RecyclerView ticketsRecycler;
    ListView listView;
    ScrollView scrollView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}


        //Get intent from CheckoutPage.class

        //Get name of movie
        Intent i = getIntent();
        String movieName = i.getStringExtra("movieName");

        //Get time of movie
        Intent j = getIntent();
        String movieDate = j.getStringExtra("movieDate");

        //Get payment method
        Intent k = getIntent();
        String paymentMethod = k.getStringExtra("paymentMode");

        //Get payment status
        Intent l = getIntent();
        String paymentStatus = l.getStringExtra("paymentStatus");

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        listView = findViewById(R.id.listView);
        scrollView = findViewById(R.id.scroll);

        //Set Tickets Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_tickets);

        ticketsRecycler = findViewById(R.id.ticketsRecycler);
        ticketsRecycler.setHasFixedSize(true);
        ticketsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Integer> currentTicket = new ArrayList<>();

        currentTicket.add(R.drawable.shangchi_ticket);
        currentTicket.add(R.drawable.eternals_ticket);

        TicketsAdapter ticketAdapter = new TicketsAdapter(currentTicket);
        ticketsRecycler.setAdapter(ticketAdapter);

        listItems = new ArrayList<>();
        //Dummy Data
        listItems.add("Shang-Chi" + "\n" + "20 NOV\n" +"Payment Method: Cash\n"+"Status: Pending");

        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item_row, listItems );
        listView.setAdapter(adapter);

        //ListView inside ScrollView
        listView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        //Disable ScrollView to intercept touch events
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        //Allow ScrollView to intercept touch events
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                //Handle ListView touch events
                v.onTouchEvent(event);
                return true;
            }
        });

        //Add items to list dynamically
        if(!movieName.isEmpty() && !movieDate.isEmpty() && !paymentMethod.isEmpty() && !paymentStatus.isEmpty()){
            addItem(movieName + "\n" + movieDate + "\nPayment Method: " + paymentMethod
                    + "\nStatus: " + paymentStatus);
        }

    }


        public void addItem(String item){
            listItems.add(item);
            listView.setAdapter(adapter);
    }

}