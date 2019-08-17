package com.example.MaaKaKhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Page extends AppCompatActivity {

    EditText text_email_id,text_password;
    Button btn_login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        text_email_id = (EditText) findViewById(R.id.etEmailId);
        text_password = (EditText) findViewById(R.id.etPassword);
        btn_login = (Button) findViewById(R.id.btnLogin);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                String email_id = text_email_id.getText().toString();
                String password = text_password.getText().toString();

                mAuth.signInWithEmailAndPassword(email_id, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent intent = new Intent(Login_Page.this, Home_Screen.class);
                                    startActivity(intent);
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(), "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }


        });

    }
        public void register (View view)
        {
            Intent intent = new Intent(this, Registration.class);
            startActivity(intent);
        }


}
