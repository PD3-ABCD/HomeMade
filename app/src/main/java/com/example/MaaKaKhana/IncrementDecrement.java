package com.example.MaaKaKhana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class IncrementDecrement extends AppCompatActivity {

    ElegantNumberButton elegantNumberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_data);

        elegantNumberButton = (ElegantNumberButton)findViewById(R.id.myButton);
        elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=elegantNumberButton.getNumber();
                ret_count();
                //Log.e("Number",num);
                //Toast.makeText(IncrementDecrement.this, "Quantity is: "+num, Toast.LENGTH_SHORT).show();
            }
        });
    }

    int ret_count(){
        String num=elegantNumberButton.getNumber();
        int n = Integer.parseInt(num);
        return n;
    }
}