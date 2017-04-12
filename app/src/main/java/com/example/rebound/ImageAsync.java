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

public class ImageAsync extends AsyncTask<Void,Void,Bitmap>
{

    String url;
    ImageView imageView;

    public ImageAsync(String url, ImageView imageView)
    {
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... voids)
    {
        try
        {
            URL urlConnection = null;
            urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap)
    {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }
}
