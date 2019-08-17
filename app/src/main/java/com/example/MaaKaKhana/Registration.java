package com.example.MaaKaKhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText text_first_name, text_last_name,text_email_id,text_password,text_contact_no;
    Button btn_register;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        //Casting Views

        text_first_name=(EditText)findViewById(R.id.etFirstName);
        text_last_name=(EditText)findViewById(R.id.etLastName);
        text_email_id=(EditText)findViewById(R.id.etEmailId);
        text_password=(EditText)findViewById(R.id.etPassword);
        text_contact_no=(EditText)findViewById(R.id.etContactNo);
        btn_register=(Button) findViewById(R.id.btnRegister);


        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String first_name=text_first_name.getText().toString();
                final String last_name = text_last_name.getText().toString();
                final String email_id = text_email_id.getText().toString();
                final String password = text_password.getText().toString();
                final String contact_no = text_contact_no.getText().toString();

                if(TextUtils.isEmpty(first_name))
                {
                    //Toast.makeText(Registration.this, "Please Enter First Name",Toast.LENGTH_SHORT).show();
                    text_first_name.setError("First Name cannot be blank");

                }

                else if(TextUtils.isEmpty(last_name))
                {
                    //Toast.makeText(Registration.this, "Please Enter Last Name",Toast.LENGTH_SHORT).show();
                    text_last_name.setError("Last Name cannot be blank");
                }

                else if(TextUtils.isEmpty(email_id))
                {
                    //Toast.makeText(Registration.this, "Please Enter Email Id",Toast.LENGTH_SHORT).show();
                    text_email_id.setError("Email Id cannot be blank");
                }

                else if(TextUtils.isEmpty(password))
                {
                    //Toast.makeText(Registration.this, "Please Enter Password",Toast.LENGTH_SHORT).show();
                    text_password.setError("Password cannot be blank");
                }

                else if(TextUtils.isEmpty(contact_no))
                {
                    //Toast.makeText(Registration.this, "Please Enter Contact Number",Toast.LENGTH_SHORT).show();
                    text_contact_no.setError("Contact Number cannot be blank");
                }

                else
                {
                    firebaseAuth.createUserWithEmailAndPassword(email_id, password)
                            .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        registration_details information = new registration_details(
                                                first_name,
                                                last_name,
                                                email_id,
                                                password,
                                                contact_no
                                        );

                                        FirebaseDatabase.getInstance().getReference("Registration")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),Login_Page.class));
                                            }
                                        });
                                    } else {

                                    }

                                    // ...
                                }
                            });
                }

            }
        });
    }


}
