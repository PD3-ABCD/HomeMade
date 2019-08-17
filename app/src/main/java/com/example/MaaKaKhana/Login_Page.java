package com.example.MaaKaKhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Page extends AppCompatActivity {

    EditText text_email_id,text_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        text_email_id = (EditText) findViewById(R.id.etEmailId);
        text_password = (EditText)findViewById(R.id.etPassword);
        btn_login = (Button) findViewById(R.id.btnLogin);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Get User Details

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                })
            }
        });



    }
        public void register(View view)
        {
            Intent intent=new Intent(this, Registration.class);
            startActivity(intent);

        }


}
