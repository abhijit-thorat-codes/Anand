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

public class Page3 extends AppCompatActivity {
    ImageView img;
    final String imgURL  = "https://www.yuvavarta.com/Page3.png";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        img= (ImageView) findViewById(R.id.page3);
        progressDialog.show(Page3.this,"Page 3","Loading...",true);
        new Page3.DownLoadImageTask3(img).execute(imgURL);

    }

    private class DownLoadImageTask3 extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        public DownLoadImageTask3(ImageView imageView){
            this.imageView = imageView;
        }
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            progressDialog.dismiss();
            imageView.setImageBitmap(result);
        }
    }
}
