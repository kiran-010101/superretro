package com.example.kiran.superhero;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();//handler is a class to delay activity for certain time..to go from one to another delay for some time
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();//to make sure that we dont go to splash activity when we click back from main activity as splash is supposed to come only once at the start
            }
        },2500);//time to delay in millisecond i.e 2500 =2.5s


    }
}
