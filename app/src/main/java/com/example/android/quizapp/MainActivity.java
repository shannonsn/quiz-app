package com.example.android.quizapp;

import android.content.Intent;
import android.content.pm.FeatureGroupInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    int Score = 0;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String rose ="Rose";
    String jack ="Jack";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public String orderSummary(String hasName, String hasNameJack , String hasNameRose, Boolean hasFavouriteActorJack , Boolean hasFavouriteActorRose){
        String displayMessage = "Hi and welcome " + hasName + "\nName of male actor is " + hasNameJack + "\nName of female actor is "+hasNameRose;
               displayMessage += "\nIs you Favourite male " + hasFavouriteActorJack;
               displayMessage += "\nIs your Favour Actor Female " +hasFavouriteActorRose;
               displayMessage += "\nTherefore your score is " + Score;
//        displayMessage = displayMessage + "\nWe are happy to announce another " + "female "+" just joined on our revolution lets see how smart you really are"+"\nBesides joining our revolution you already made one correct choice";
        return displayMessage;
    }

//public int totalScore(View view){
//        return Score;
//}
    public void submit(View view){

        CheckBox actorJack = (CheckBox) findViewById(R.id.jack);
        boolean hasFavouriteActorJack = actorJack.isChecked();

        CheckBox actorRose = (CheckBox) findViewById(R.id.rose);
        Boolean hasFavouriteActorRose = actorRose.isChecked();


        // this finds the name of the person taking the quizz
        EditText getName = (EditText) findViewById(R.id.name);
        final String hasName = getName.getText().toString();

        if (hasName != null){
            Toast.makeText(this, "This is my Toast message! hooray and Welcome " + hasName,
                    Toast.LENGTH_LONG).show();
        }


        //gets name of female actor Ross
        EditText femaleActorName = (EditText) findViewById(R.id.female);
        String hasNameRose = femaleActorName.getText().toString();

        if(hasNameRose.equals(rose)){
            Score = Score +1;
            Log.i("MainActivity.java", hasNameRose);
            Toast.makeText(this, "That is correct her name is " + rose,
                    Toast.LENGTH_LONG).show();
        }else if (!hasNameRose.equals(rose)){
            Toast.makeText(this, "Did you know her name is " + rose,
                    Toast.LENGTH_LONG).show();
        }



        //gets the name of the male actor Jack
        EditText maleActorName = (EditText) findViewById(R.id.male);
        String hasNameJack = maleActorName.getText().toString();

        if (hasNameJack.equals(jack)){
            Score = Score +1;
            Log.i("MainActivity.java", hasNameJack);
            Toast.makeText(this, " That is correct his name is Jack",
                    Toast.LENGTH_LONG).show();
        }else if(!hasNameJack.equals(jack)){
            Log.i("MainActivity.java", hasNameJack);
            Toast.makeText(this, " That is incorrect his name is Jack",
                    Toast.LENGTH_LONG).show();
        }


displayTotalScored(Score);

        final String displayResults = orderSummary(hasName ,hasNameJack , hasNameRose , hasFavouriteActorJack, hasFavouriteActorRose);

        //this checks the radio button that has been checked inside the groupView of gender


        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup = findViewById(R.id.radioGroupSex);
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String yourChoice = "your choice";
                genderRadioDisplay(yourChoice +": " +radioButton.getText());
            }
        });


            Button sank = findViewById(R.id.submit);
        sank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                radioGroup = findViewById(R.id.titanic_sank);
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String yearSelected = "year selected for Titanic Sank";
                displayTitanicSank(yearSelected + radioButton.getText());
            }
        });




        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                radioGroup = findViewById(R.id.titanic_made);
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                displayResult("Selected " + radioButton.getText());
            }
        });

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz App Results ");
        intent.putExtra(intent.EXTRA_TEXT, displayResults);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



    public void displayTotalScored(int score){
        TextView scoreResults = (TextView) findViewById(R.id.scores);
        scoreResults.setText(String.valueOf(score));
    }

    public void displayResult(String view){
        TextView resultsScore = (TextView) findViewById(R.id.results);
        resultsScore.setText(String.valueOf(view));
    }

    public void displayTitanicSank(String view){
        TextView YearSank = (TextView) findViewById(R.id.yearSank);
        YearSank.setText(String.valueOf(view));
    }
    public void genderRadioDisplay(String view){
        TextView displayRadioGender = (TextView) findViewById(R.id.genderDisplay);
        displayRadioGender.setText(String.valueOf(view));
    }

    }
