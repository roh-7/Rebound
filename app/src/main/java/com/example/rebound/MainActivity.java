package com.example.rebound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;

public class MainActivity extends AppCompatActivity
{
    private final BaseSpringSystem baseSpringSystem = SpringSystem.create();
    private final BaseSpringSystem _baseSpringSystem = SpringSystem.create();
    private ImageView imageView;
    private ImageView _imageView;
    private Spring spring;
    private Spring _spring;
    public final String url = "https://static.xx.fbcdn.net/rsrc.php/v1/yB/r/i9VUkiHf940.jpg";
    public final String _url = "https://static.xx.fbcdn.net/rsrc.php/v1/yB/r/i9VUkiHf940.jpg";

    @Override
    protected void onStart()
    {
        final String LOG_TAG = "onStart";
        super.onStart();
        Log.v(LOG_TAG,"img");
        Log.v(LOG_TAG,"img1");
        new ImageAsync(_url,_imageView).execute();
        new ImageAsync(url,imageView).execute();
        Log.v(LOG_TAG,"img2");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String LOG_TAG = "onCreate";
        spring = baseSpringSystem.createSpring();
        _spring = _baseSpringSystem.createSpring();
        imageView = (ImageView)findViewById(R.id.image_view1);
        _imageView = (ImageView) findViewById(R.id._image_view);
        spring.addListener(new SpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float)spring.getCurrentValue();
                float mappedValue = 1f - (0.5f*value);
                imageView.setScaleX(mappedValue);
                imageView.setScaleY(mappedValue);
            }

            @Override
            public void onSpringAtRest(Spring spring) {

            }

            @Override
            public void onSpringActivate(Spring spring) {

            }

            @Override
            public void onSpringEndStateChange(Spring spring) {

            }
        });

        _spring.addListener(new SpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float)spring.getCurrentValue();
                float mappedValue = 1f - (0.5f*value);
                _imageView.setScaleY(mappedValue);
                _imageView.setScaleX(mappedValue);
            }

            @Override
            public void onSpringAtRest(Spring spring) {

            }

            @Override
            public void onSpringActivate(Spring spring) {

            }

            @Override
            public void onSpringEndStateChange(Spring spring) {

            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                Log.v(LOG_TAG,"1");
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        spring.setEndValue(1);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        spring.setEndValue(0);
                        break;
                }
                return true;
            }
        });

        _imageView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                Log.v(LOG_TAG,"2");
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        _spring.setEndValue(1);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        _spring.setEndValue(0);
                        break;
                }
                return true;
            }
        });
    }
}