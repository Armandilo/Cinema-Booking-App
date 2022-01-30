package com.example.cinemabookingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class selectdatetime extends AppCompatActivity {


    private Button button;

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdatetime);

        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        String moviename = getIntent().getStringExtra("moviename");

        final String[] movieTime = new String[1];
        final String[] CinemaLocation = new String[1];





        button = (Button) findViewById(R.id.toseatpage);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup1);
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
                String movieDate = (String) radioButton.getText();

                RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radiogroup2);
                int radioButtonID2 = radioGroup1.getCheckedRadioButtonId();
                if(radioButtonID2 == -1)
                {
                    RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radiogroup3);
                    int radioButtonID3 = radioGroup2.getCheckedRadioButtonId();
                    RadioButton radioButton3 = (RadioButton) radioGroup2.findViewById(radioButtonID3);
                    movieTime[0] = (String) radioButton3.getText();
                    CinemaLocation[0] = (String) radioButton3.getContentDescription();

                }
                else
                {
                    RadioButton radioButton2 = (RadioButton) radioGroup1.findViewById(radioButtonID2);
                    movieTime[0] = (String) radioButton2.getText();
                    CinemaLocation[0] = (String) radioButton2.getContentDescription();

                }
                OpenSelectSeat(moviename,movieDate, movieTime[0], CinemaLocation[0]);
            }
        });


    }

    public void OpenSelectSeat(String moviename, String movieDate,String movieTime, String CinemaLocation){
        Intent intent = new Intent(this, selectseat.class);

        if(movieDate == null || movieTime == null)
        {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Error")
                    .setMessage("Make sure to select both Date and Time to continue")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

        }
        else
        {
            intent.putExtra("moviename", moviename);
            intent.putExtra("chosenDate",movieDate);
            intent.putExtra("chosenTime",movieTime);
            intent.putExtra("chosenPlace",CinemaLocation);
            startActivity(intent);
        }

    }



}