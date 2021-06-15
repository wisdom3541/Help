package com.example.help;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;

import java.util.Timer;

public class splashscreen extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);

        //hooks
        intent = new Intent(this, home.class);


        //timer to home page
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                //do  nothing
            }

            public void onFinish() {
              startActivity(intent);
              finish();
            }
        }.start();

    }
}