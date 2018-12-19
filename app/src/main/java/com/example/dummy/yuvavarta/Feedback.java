package com.example.dummy.yuvavarta;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

    public class Feedback extends AppCompatActivity {
        private RatingBar ratingBar;
        RadioGroup radioGroup;
        EditText comment;
        private Button btnSubmit;
        String mailTo="dailyyuvavarta@gmail.com",readerName,readerPhone,readerCity;
        String masterDetails;
        int radioItem,rating;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_feedback);
            Toolbar toolbar=(Toolbar)findViewById(R.id.myBar);
            setSupportActionBar(toolbar);
            radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
            comment=(EditText) findViewById(R.id.comment);
            ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            btnSubmit = (Button) findViewById(R.id.submit);

            radioItem=radioGroup.getCheckedRadioButtonId();
            Intent getData=getIntent();
            readerName=getData.getStringExtra("Name");
            readerCity=getData.getStringExtra("City");
            readerPhone=getData.getStringExtra("Phone");
            rating = (int) ratingBar.getRating();
            masterDetails="Reader's Information :";
            masterDetails=masterDetails+"\n\nName :  "+readerName;
            masterDetails=masterDetails+"\nCity  :  "+readerCity;
            masterDetails=masterDetails+"\nPhone :  "+readerPhone;
            masterDetails=masterDetails+"\nRatimgs Given :  "+rating;

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(radioItem==0){
                        masterDetails=masterDetails+"\nStandard of News Content :  Not Good";
                    }
                    else if(radioItem==1){
                        masterDetails=masterDetails+"\nStandard of News Content :  Moderate";
                    }else if(radioItem==2){
                        masterDetails=masterDetails+"\nStandard of News Content :  Good";
                    }else{
                        masterDetails=masterDetails+"\nStandard of News Content :  Excellent";
                    }

                    masterDetails=masterDetails+"\nComment : "+comment.getText().toString();

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setType("message/rfc822");
                    email.setPackage("com.google.android.gm");
                    email.setType("text/plain");
                    email.setData(Uri.parse(mailTo));
                    email.putExtra(Intent.EXTRA_SUBJECT,"Reader's Feedback");
                    email.putExtra(Intent.EXTRA_TEXT,masterDetails);

                }

            });

        }
    }
