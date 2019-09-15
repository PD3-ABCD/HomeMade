package com.example.MaaKaKhana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private static int timeout=3000;
TextView txt;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.welcome);
        img=findViewById(R.id.splashimg);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.splash_anim);
        img.startAnimation(animation);
        txt.startAnimation(animation);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (isUserLogin()) {
                    intent = new Intent(MainActivity.this, Main2Activity.class);
                }
                else{
                    intent = new Intent(MainActivity.this, Login_Page.class);
                }
                startActivity(intent);
                finish();
            }
        },timeout);
    }

    private boolean isUserLogin() {
        SharedPreferences sp = getSharedPreferences("session", MODE_PRIVATE);
        return !sp.getString("user","").isEmpty();
    }
}
