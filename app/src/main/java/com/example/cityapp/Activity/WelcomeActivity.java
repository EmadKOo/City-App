package com.example.cityapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.cityapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

         new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}