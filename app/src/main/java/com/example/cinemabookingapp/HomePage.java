package com.example.cinemabookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemabookingapp.adapter.CurrentMoviesAdapter;
import com.example.cinemabookingapp.adapter.PromosAdapter;
import com.example.cinemabookingapp.adapter.UpcomingMoviesAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements CurrentMoviesAdapter.OnPosterListener {

    private RecyclerView movieRecyclerView, promoRecyclerView, upcomingMovieRecyclerView;
    TextView usernameDisplay;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_USERNAME = "myusername";
    private static final String USERNAME_KEY = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        usernameDisplay = findViewById(R.id.username_display);

        sharedPreferences = getSharedPreferences(SHARED_PREF_USERNAME, MODE_PRIVATE);

        String username = sharedPreferences.getString(USERNAME_KEY, null);

        if (username != null){
            usernameDisplay.setText(username);
        }

        /*/Get username
        Intent i = getIntent();
        String username = i.getStringExtra("username");

        if(username.isEmpty()){
            ((TextView)findViewById(R.id.username_display)).setText("User");
        }
        else
            ((TextView)findViewById(R.id.username_display)).setText(username); *///Display username

        //Hide action bar
        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        //Location spinner
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.location, R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Search Movie
        String[] searchmovies = getResources().getStringArray(R.array.searchmovies);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.editText);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searchmovies);
        autoCompleteTextView.setAdapter(arrayAdapter);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

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
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), UserProfilePage.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        //movie RecyclerView
        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Integer> currentMoviesList = new ArrayList<>();

        currentMoviesList.add(R.drawable.shangchi);
        currentMoviesList.add(R.drawable.eternals);
        currentMoviesList.add(R.drawable.escaperoom);

        CurrentMoviesAdapter currentMoviesAdapter = new CurrentMoviesAdapter(currentMoviesList,this);
        movieRecyclerView.setAdapter(currentMoviesAdapter);

        //promo RecyclerView
        promoRecyclerView = findViewById(R.id.promoRecyclerView);
        promoRecyclerView.setHasFixedSize(true);
        promoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Integer> promosList = new ArrayList<>();

        promosList.add(R.drawable.promo_1);
        promosList.add(R.drawable.promo_2);
        promosList.add(R.drawable.promo_3);

        PromosAdapter promosAdapter = new PromosAdapter(promosList);
        promoRecyclerView.setAdapter(promosAdapter);

        //upcoming movie RecyclerView
        upcomingMovieRecyclerView = findViewById(R.id.upcomingmovieRecyclerView);
        upcomingMovieRecyclerView.setHasFixedSize(true);
        upcomingMovieRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.jujutsukaisen);
        imageList.add(R.drawable.batman);
        imageList.add(R.drawable.blackphone);
        imageList.add(R.drawable.monkeyking);

        UpcomingMoviesAdapter upcomingMoviesAdapter = new UpcomingMoviesAdapter(imageList);
        upcomingMovieRecyclerView.setAdapter(upcomingMoviesAdapter);
    }

    @Override
    public void onPosterClick(int position) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie", position);
        startActivity(intent);
    }
}