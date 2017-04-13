package com.example.rebound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;

public class MainActivity extends AppCompatActivity 
{
    // BaseSpringSystem is a system to run the physics loop for a set of springs.
    private final BaseSpringSystem baseSpringSystem = SpringSystem.create();
    // user defined class that extends SimpleSpringListener.
    private final ExampleSpringListener exampleSpringListener = new ExampleSpringListener();
    private ImageView imageView;
    private FrameLayout frameLayout;
    // this is the spring used for the animations.
    private Spring spring;
    public String url = "http://siesgst.tk/static/images/assets/stab_logo.png";

    @Override
    protected void onStart() 
    {
        super.onStart();
        imageView = (ImageView)findViewById(R.id.image_view);
        // populating the image using an async task in the background.
        new ImageAsync(url,imageView).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout)findViewById(R.id.root);
        // initialising the spring.
        spring = baseSpringSystem.createSpring();
        frameLayout.setOnTouchListener(new View.OnTouchListener()
         {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) 
            {
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
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // adding the listener once the activity resumes.
        spring.addListener(exampleSpringListener);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        // removing the listener once the activity is paused
        spring.removeListener(exampleSpringListener);
    }

    private class ExampleSpringListener extends SimpleSpringListener
    {
        // class is used to get the mapped value and update the scale
        @Override
        public void onSpringUpdate(Spring spring) 
        {
            // taking value from the spring
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
            // setting scale of the images along X and Y.
            imageView.setScaleX(mappedValue);
            imageView.setScaleY(mappedValue);
        }
    }
}