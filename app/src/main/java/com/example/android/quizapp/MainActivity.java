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
    RadioGroup radioGroupGender;
    RadioButton radioButtonGender;
    String imageStored ="Drawable";
    String extentionName ="xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public String orderSummary(String hasName, String hasGroupLayoutOptionEditView ,Boolean hasGroupLayoutOptionTextView, String hasNameOfFolderImagesStored, Boolean hasGroupLayoutOptionLinearLayoutView, Boolean hasGroupLayoutOptionRelativeView){
        String displayMessage = "Hi and welcome " + hasName;
               displayMessage += "\nFor Every question Answered correctly you score 1 point these are your answer and your score is recorded below ";
               displayMessage += "\nName of folder where Images are stored: " + hasNameOfFolderImagesStored;
               displayMessage += "\nIs a relatve layout a group View?  " + hasGroupLayoutOptionRelativeView;
               displayMessage +="\nDoes a TextView from part of a group view? " + hasGroupLayoutOptionTextView;
               displayMessage += "\nDoes a EditView form pat of a Group view? " + hasGroupLayoutOptionEditView;
               displayMessage +=  "\nDoes a LinearLayout form part of a group view? " + hasGroupLayoutOptionLinearLayoutView;
               displayMessage += "\nTherefore your score is " + Score;
//               displayMessage += "\nyour sex";
//        displayMessage = displayMessage + "\nWe are happy to announce another " + "female "+" just joined on our revolution lets see how smart you really are"+"\nBesides joining our revolution you already made one correct choice";
        return displayMessage;
    }


    public void submit(View view){

        CheckBox groupLayoutOptionEditView = (CheckBox) findViewById(R.id.editViewOption);
        boolean hasGroupLayoutOptionEditView = groupLayoutOptionEditView.isChecked();

        CheckBox groupLayoutOptionRelativeView = (CheckBox) findViewById(R.id.relativeViewOption);
        boolean hasGroupLayoutOptionRelativeView = groupLayoutOptionRelativeView.isChecked();

        CheckBox groupLayoutOptionTextView = (CheckBox) findViewById(R.id.textViewOption);
        boolean hasGroupLayoutOptionTextView = groupLayoutOptionTextView.isChecked();

        CheckBox groupLayoutOptionLinearLayoutView = (CheckBox) findViewById(R.id.linearLayoutOption);
        boolean hasGroupLayoutOptionLinearLayoutView = groupLayoutOptionLinearLayoutView.isChecked();

        // this finds the name of the person taking the quizz
        EditText getName = (EditText) findViewById(R.id.name);
        final String hasName = getName.getText().toString();

        if (hasName != null){
            Score = Score +1;
            Toast.makeText(this, "This is my Toast message! hooray and Welcome " + hasName,
                    Toast.LENGTH_LONG).show();
        }


        //gets name of female actor Ross
        EditText folderImagesStored = (EditText) findViewById(R.id.folderImagesAreStored);
        String hasNameOfFolderImagesStored = folderImagesStored.getText().toString();

        if(hasNameOfFolderImagesStored.equals(imageStored)){
            Score = Score +1;
            Log.i("MainActivity.java", hasNameOfFolderImagesStored);
            Toast.makeText(this, "That is correct the name of the folder is called " + imageStored,
                    Toast.LENGTH_LONG).show();
        }else if (!hasNameOfFolderImagesStored.equals(imageStored)){
            Toast.makeText(this, "Did you know the name the folder is called " + imageStored,
                    Toast.LENGTH_LONG).show();
        }



        //gets the name of the male actor Jack
        EditText fileExtentionName = (EditText) findViewById(R.id.extentionName);
        String hasExtentionName = fileExtentionName.getText().toString();

        if (hasExtentionName.equals(extentionName)){
            Score = Score +1;
            Log.i("MainActivity.java", hasExtentionName);
            Toast.makeText(this, " That is correct his name is Jack",
                    Toast.LENGTH_LONG).show();
        }else if(!hasExtentionName.equals(extentionName)){
            Log.i("MainActivity.java", hasExtentionName);
            Toast.makeText(this, " That is incorrect his name is Jack",
                    Toast.LENGTH_LONG).show();
        }


displayTotalScored(Score);

        final String displayResults = orderSummary(hasName ,hasExtentionName ,hasGroupLayoutOptionTextView, hasNameOfFolderImagesStored, hasGroupLayoutOptionEditView, hasGroupLayoutOptionRelativeView);

        //this checks the radio button that has been checked inside the groupView of gender


        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroupGender = findViewById(R.id.radioGroupSex);
                int radioIdForSex = radioGroupGender.getCheckedRadioButtonId();
                radioButtonGender = findViewById(radioIdForSex);
                String gender = radioButtonGender.getText().toString();
                String yourChoice = "your choice";
                genderRadioDisplay(yourChoice +": " + gender);
            }
        });



        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                radioGroup = findViewById(R.id.titanic_sank);
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String yearSelected = "Titanic Sank ";
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
