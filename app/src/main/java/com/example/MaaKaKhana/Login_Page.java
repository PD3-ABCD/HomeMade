package com.example.MaaKaKhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
                //Progress Dialog (Loading Dialog)




                mAuth = FirebaseAuth.getInstance();
                String email_id = text_email_id.getText().toString();
                String password = text_password.getText().toString();

                if(TextUtils.isEmpty(email_id))
                {
                    text_email_id.setError("Email Id cannot be blank");
                }
                else if(TextUtils.isEmpty(password))
                {
                    text_password.setError("Password cannot be blank");
                }
                else
                {
                    final ProgressDialog progressDialog=new ProgressDialog(Login_Page.this);
                    progressDialog.setMessage("Please wait....");
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(email_id, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        progressDialog.dismiss();
                                        // Sign in success, update UI with the signed-in user's information
                                        Intent intent = new Intent(Login_Page.this, MapsActivity.class);
                                        startActivity(intent);
                                        FirebaseUser user = mAuth.getCurrentUser();

                                    } else {

                                        progressDialog.dismiss();
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getApplicationContext(), "Sorry!! " + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
            }


        });

    }
        public void register (View view)
        {
            Intent intent = new Intent(this, Registration.class);
            startActivity(intent);
        }


}
