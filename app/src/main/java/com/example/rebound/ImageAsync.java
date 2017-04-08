package com.example.rebound;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rohitramaswamy on 24/02/17.
 */

public class ImageAsync extends AsyncTask<Void,Void,Bitmap> {

    String url;
    ImageView imageView;

    public ImageAsync(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        final String LOG_TAG = "doInBackground";
        try {
            URL urlConnection = null;
            Log.v(LOG_TAG,url);
            urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Log.v("input",input.toString());
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        final String LOG_TAG = "onPostExecute";
        super.onPostExecute(bitmap);
        Log.v(LOG_TAG,"1");
        imageView.setImageBitmap(bitmap);
        Log.v(LOG_TAG,"2");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


}
