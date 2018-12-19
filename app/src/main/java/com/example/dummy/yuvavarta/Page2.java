package com.example.dummy.yuvavarta;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Page2 extends AppCompatActivity {
    ImageView img;
    final String imgURL  = "https://www.yuvavarta.com/Page2.png";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        img= (ImageView) findViewById(R.id.page2);
        progressDialog = ProgressDialog.show(Page2.this, "Page 2",
                "Loading...", true);
        new DownLoadImageTask2(img).execute(imgURL);
        PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(img);
        photoViewAttacher.update();
    }

    private class DownLoadImageTask2 extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        public DownLoadImageTask2(ImageView imageView){
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
