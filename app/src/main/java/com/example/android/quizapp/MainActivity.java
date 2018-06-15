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
    String rose = "Rose";
    String jack = "Jack";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public String orderSummary(String hasName, String hasNameJack , String hasNameRose){
        String displayMessage = "Hi and welcome " + hasName + " name of male actor " + hasNameJack + " name of female actor "+hasNameRose;

//        displayMessage = displayMessage + "\nWe are happy to announce another " + "female "+" just joined on our revolution lets see how smart you really are"+"\nBesides joining our revolution you already made one correct choice";
        return displayMessage;
    }


    public void submit(View view){
        // this finds the name of the person taking the quizz
        EditText getName = (EditText) findViewById(R.id.name);
        final String hasName = getName.getText().toString();

        if (hasName != null){
            Toast.makeText(this, "This is my Toast message! hooray and Welcome " + hasName,
                    Toast.LENGTH_LONG).show();
        }


        //gets name of female actor Ross
        EditText femaleActorName = (EditText) findViewById(R.id.female);
        String  hasNameRose = femaleActorName.getText().toString();

        if(hasNameRose != rose){
            Toast.makeText(this, "Did you know that the female character was named" + rose,
                    Toast.LENGTH_LONG).show();
        }else if (hasNameRose == rose){
            Toast.makeText(this, "Thats Correct her name is Rose",
                    Toast.LENGTH_LONG).show();
            Score = + 1;
        }else if(hasNameRose == null){
            Toast.makeText(this, "please enter a name",
                    Toast.LENGTH_LONG).show();
        }

        //gets the name of the male actor Jack
        EditText maleActorName = (EditText) findViewById(R.id.male);
        String hasNameJack = maleActorName.getText().toString();

        if (hasNameJack != jack){
            Log.i("MainActivity.java", hasNameRose);
            Toast.makeText(this, "that is incorrect his name is Jack",
                    Toast.LENGTH_LONG).show();
        }else{
            Log.i("MainActivity.java", hasNameJack);
            Toast.makeText(this, "that is correct his name is Jack",
                    Toast.LENGTH_LONG).show();
        }



        final String dispayResults = orderSummary(hasName ,hasNameJack , hasNameRose);
        //this checks the radio button that has been checked inside the groupView of gender
        radioGroup = findViewById(R.id.radioGroupSex);
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String yourChoice = "your choice";
                dispayResult(dispayResults + yourChoice +": " +radioButton.getText());
            }
        });
//
        radioGroup = findViewById(R.id.titanic_sank);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String yearSelected = "year Selected";
                dispayResult(yearSelected + radioButton.getText());
            }
        });
        radioGroup = findViewById(R.id.tatanic_made);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String yearSelected = "year Selected";
                dispayResult(yearSelected + radioButton.getText());
            }
        });

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz App Results ");
//        intent.putExtra(intent.EXTRA_TEXT, dispayResults);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
    }
    public void dispayResult(String view){
        TextView resultsScore = (TextView) findViewById(R.id.results);
        resultsScore.setText(String.valueOf(view));
    }
}
