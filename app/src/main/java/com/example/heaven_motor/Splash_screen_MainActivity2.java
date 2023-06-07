package com.example.heaven_motor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_screen_MainActivity2 extends AppCompatActivity {
    Animation zoomIn, slideIn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sreen_main2);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom);

        ImageView logo = findViewById(R.id.logo);
        ImageView animVec = findViewById(R.id.animatedVector);
        TextView title = findViewById(R.id.title);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo.setVisibility(View.VISIBLE);
                animVec.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) animVec.getDrawable();
                    drawable.start();
                }
                logo.startAnimation(zoomIn);
                title.startAnimation(slideIn);
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash_screen_MainActivity2.this, Login_MainActivity2.class);
                startActivity(i);
            }
        }, 5000);
    }
}