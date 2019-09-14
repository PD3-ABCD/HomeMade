package com.example.MaaKaKhana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void logout(View view) {
        getSharedPreferences("session",MODE_PRIVATE).edit().clear().apply();
        Intent intent =new Intent(Home_screen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
