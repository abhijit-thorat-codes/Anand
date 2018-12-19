package com.example.dummy.anand;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class Page4 extends AppCompatActivity {
    ImageView img;
    final String imgURL  = "https://www.yuvavarta.com/Page4.png";
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        img= (ImageView) findViewById(R.id.page4);
        progressDialog.show(Page4.this,"Page 4","Loading...",true);
        new Page4.DownLoadImageTask4(img).execute(imgURL);

    }

    private class DownLoadImageTask4 extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        public DownLoadImageTask4(ImageView imageView){
            this.imageView = imageView;
        }
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }
        protected void onPostExecute(Bitmap result){
            progressDialog.dismiss();
            imageView.setImageBitmap(result);
        }
    }
}
