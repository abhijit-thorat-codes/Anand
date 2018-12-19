package com.example.dummy.yuvavarta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Page extends AppCompatActivity {
    ImageView img;
    ProgressDialog progressDialog;
    Intent getData=getIntent();
    final String imgURL = getData.getStringExtra("imgUrl");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        img= (ImageView) findViewById(R.id.page);
        progressDialog = ProgressDialog.show(Page.this, "Loading...",
                "Loading...", true);
        new Page.DownLoadImageTask(img).execute(imgURL);
        PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(img);
        photoViewAttacher.update();

    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){
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
