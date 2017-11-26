package com.kenya.nyumbakumi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final ImageView imgView = findViewById(R.id.imgSplash);
        final Animation blink = AnimationUtils.loadAnimation(getBaseContext(), R.anim.blink);
        @SuppressLint("PrivateResource") final Animation fade_out = AnimationUtils.loadAnimation(getApplication(), R.anim.abc_fade_out);

        try{
            imgView.startAnimation(blink);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Splash Error Occurred", Toast.LENGTH_SHORT).show();
            goToNext(getApplicationContext());
        }
        blink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgView.startAnimation(fade_out);
                goToNext(getApplicationContext());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void goToNext(Context context){
        Intent toMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(toMain);
        finish();
    }
}
