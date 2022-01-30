package com.example.cinemabookingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Tickets extends AppCompatActivity {

    private RecyclerView ticketsRecycler;
    ListView listView;
    ScrollView scrollView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    String movieName=null, movieDate=null, paymentMethod=null, paymentStatus=null;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_MOVNAME = "myMovieName";
    private static final String MOVNAME_KEY = "movieName";
    //private static final String SHARED_PREF_CHOSENDATE = "myChosenDate";
    private static final String CHOSENDATE_KEY = "chosenDate";
    //private static final String SHARED_PREF_PAYMODE = "myPayMode";
    private static final String PAYMODE_KEY = "payMode";
    //private static final String SHARED_PREF_PAYSTATUS = "myPayStatus";
    private static final String PAYSTATUS_KEY = "payStatus";

    @SuppressLint("ClickableViewAccessibility")
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

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_tickets);

        //Perform ItemSelectedList
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_tickets:
                    startActivity(new Intent(getApplicationContext(), Tickets.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        sharedPreferences = getSharedPreferences(SHARED_PREF_MOVNAME, MODE_PRIVATE);
        //sharedPreferences = getSharedPreferences(SHARED_PREF_CHOSENDATE, MODE_PRIVATE);
        //sharedPreferences = getSharedPreferences(SHARED_PREF_PAYMODE, MODE_PRIVATE);
        //sharedPreferences = getSharedPreferences(SHARED_PREF_PAYSTATUS, MODE_PRIVATE);

        movieName = sharedPreferences.getString(MOVNAME_KEY, null);
        movieDate = sharedPreferences.getString(CHOSENDATE_KEY, null);
        paymentMethod = sharedPreferences.getString(PAYMODE_KEY, null);
        paymentStatus = sharedPreferences.getString(PAYSTATUS_KEY, null);

        if (movieName != null && movieDate != null && paymentMethod != null && paymentStatus != null){
            //Initialize and assign variable
            listView = findViewById(R.id.listView);
            scrollView = findViewById(R.id.scroll);

            ticketsRecycler = findViewById(R.id.ticketsRecycler);
            ticketsRecycler.setHasFixedSize(true);
            ticketsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            List<Integer> currentTicket = new ArrayList<>();

            currentTicket.add(R.drawable.shangchi_ticket);
            currentTicket.add(R.drawable.eternals_ticket);

            TicketsAdapter ticketAdapter = new TicketsAdapter(currentTicket);
            ticketsRecycler.setAdapter(ticketAdapter);

            //loadData();
            listItems = new ArrayList<>();
            //Dummy Data
            //listItems.add("Shang-Chi" + "\n" + "20 NOV\n" +"Payment Method: Cash\n"+"Status: Pending");

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
            addItem(movieName + "\n" + movieDate + "\nPayment Method: " + paymentMethod + "\nStatus: " + paymentStatus);
            //saveData();
        }


    }


    public void addItem(String item){
            listItems.add(item);
            listView.setAdapter(adapter);
    }
/*
    private void saveData(){
        sharedPreferences = getSharedPreferences("Transaction", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listItems);
        editor.putString("Transaction List",json);
        editor.apply();
    }

    private void loadData(){
        sharedPreferences = getSharedPreferences("Transaction", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Transaction List",null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        listItems = gson.fromJson(json, type);

        if(listItems == null){
            listItems = new ArrayList<>();
        }
    }*/
}