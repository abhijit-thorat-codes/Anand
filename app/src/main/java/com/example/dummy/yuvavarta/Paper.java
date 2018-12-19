package com.example.dummy.yuvavarta;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Paper extends AppCompatActivity {
TextView date;
    ImageView p1,p2,p3,p4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        date=(TextView) findViewById(R.id.textView2);
        SimpleDateFormat sdf=new SimpleDateFormat("EEEE, d MMM yyyy");
        final String currentDate=sdf.format(new Date());
        date.setText(""+currentDate);
        p1=(ImageView)findViewById(R.id.pg1);
        p2=(ImageView)findViewById(R.id.pg2);
        p3=(ImageView)findViewById(R.id.pg3);
        p4=(ImageView)findViewById(R.id.pg4);

        p1.setClickable(true);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Paper.this,Page1.class);
                char a[]=new char[1];
                intent.putExtra("imgUrl",a[0]);
                startActivity(intent);

            }
        });
        p2.setClickable(true);
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Paper.this,Page2.class);
                startActivity(intent);

            }
        });
        p3.setClickable(true);
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Paper.this,Page3.class);
                startActivity(intent);
            }
        });
        p4.setClickable(true);
        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Paper.this,Page4.class);
                startActivity(intent);
            }
        });

    }
}
