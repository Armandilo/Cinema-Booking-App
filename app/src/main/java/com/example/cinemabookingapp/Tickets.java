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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tickets extends AppCompatActivity {

    private RecyclerView ticketsRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    DatabaseReference ticketRef;
    ListView listView;
    ScrollView scrollView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ArrayList<Item> currentTicket;
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
        Intent callerIntent = getIntent();

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
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), UserProfilePage.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        });
        listView = findViewById(R.id.listView);
        scrollView = findViewById(R.id.scroll);
        currentTicket = new ArrayList<Item>();
        bottomNavigationView.setSelectedItemId(R.id.nav_tickets);
        ticketRef = FirebaseDatabase.getInstance("https://cinemabooking-7588f-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Tickets");
        //Set Tickets Selected
        if(movieName != null)
        {

            Item ticket1 = new Item();
            ticket1.setText1(movieName);
            ticket1.setText2(finalDate);
            ticket1.setText3(CinemaLocation);
            ticket1.setText4(chosenSeat);
            ticketRef.push().setValue(ticket1);

        }

        ticketsRecycler = findViewById(R.id.ticketsRecycler);
        ticketsRecycler.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this);
        mAdapter = new TicketsAdapter(this,currentTicket);
        ticketsRecycler.setLayoutManager(mLayout);
        ticketsRecycler.setAdapter(mAdapter);
        ticketRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if(dataSnapshot.exists())
                    {
                        Item ticket = dataSnapshot.getValue(Item.class);
                        currentTicket.add(ticket);
                        mAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








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