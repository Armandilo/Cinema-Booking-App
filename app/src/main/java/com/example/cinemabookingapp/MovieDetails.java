package com.example.cinemabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetails extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        String moviename = "ESCAPE ROOM";





        int position = getIntent().getIntExtra("movie", 0);

        switch(position)
        {
            case 0 :
                moviename = "SHANG CHI";
                ((TextView)findViewById(R.id.textView11)).setText("SHANG CHI");
                ((TextView)findViewById(R.id.textView12)).setText("Marvel Studios` Shang-Chi and The Legend of The Ten Rings stars Simu Liu as Shang-Chi, who must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organisation. The film also stars Tony Leung as Wenwu, Awkwafina as Shang-Chi`s friend Katy and Michelle Yeoh as Jiang Nan, as well as Fala Chen, Meng`er Zhang, Florian Munteanu and Ronny Chieng.");
                ((ImageView)findViewById(R.id.imageView3)).setImageResource(R.drawable.shangchi);
                break;
            case 1 :
                moviename = "ETERNALS";
                ((TextView)findViewById(R.id.textView11)).setText("ETERNALS");
                ((TextView)findViewById(R.id.textView12)).setText("A race of immortal beings known as the Eternals, which are created by the Celestials, has lived on Earth for thousands of years. They not only shape the planet`s civilisations and history, they also protect humans from the evil Deviants.");
                ((ImageView)findViewById(R.id.imageView3)).setImageResource(R.drawable.eternals);
            case 3 :
                break;
        }

        button = (Button) findViewById(R.id.button5);

        String finalMoviename = moviename;
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                OpenSelectDate(finalMoviename);
            }
        });

    }

    public void OpenSelectDate(String moviename){
        Intent intent = new Intent(this, selectdatetime.class);
        intent.putExtra("moviename", moviename);
        startActivity(intent);
    }
}