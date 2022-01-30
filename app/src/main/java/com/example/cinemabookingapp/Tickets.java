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
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
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

        String movieName = getIntent().getStringExtra("movieName");

        //Get time of movie

        String chosenDate = getIntent().getStringExtra("chosenDate");
        String chosenTime = getIntent().getStringExtra("chosenTime");
        String chosenSeat = getIntent().getStringExtra("chosenSeat");
        String hall = getIntent().getStringExtra("hall");
        String finalDate = chosenTime + " " + hall + " | " + chosenDate + " 2021";

        //Get payment method
        String CinemaLocation = getIntent().getStringExtra("CinemaLocation");

        String paymentMethod = getIntent().getStringExtra("paymentMode");

        //Get payment status

        String paymentStatus = getIntent().getStringExtra("paymentStatus");



        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_tickets);

        //Perform ItemSelectedList
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
        listView = findViewById(R.id.listView);
        scrollView = findViewById(R.id.scroll);

        //Set Tickets Selected
        if(movieName != null)
        {
            bottomNavigationView.setSelectedItemId(R.id.nav_tickets);
            ArrayList<Item> currentTicket = new ArrayList<Item>();
            currentTicket.add(new Item(movieName,finalDate,CinemaLocation));
            ticketsRecycler = findViewById(R.id.ticketsRecycler);
            ticketsRecycler.setHasFixedSize(true);
            mLayout = new LinearLayoutManager(this);
            mAdapter = new TicketsAdapter(currentTicket);
            ticketsRecycler.setLayoutManager(mLayout);
            ticketsRecycler.setAdapter(mAdapter);

        }





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
      //  if(!movieName.isEmpty() && !chosenDate.isEmpty() && !paymentMethod.isEmpty() && !paymentStatus.isEmpty()){
       //     addItem(movieName + "\n" + chosenDate + "\nPayment Method: " + paymentMethod
        //            + "\nStatus: " + paymentStatus);
      //  }

    }


        public void addItem(String item){
            listItems.add(item);
            listView.setAdapter(adapter);
    }

}