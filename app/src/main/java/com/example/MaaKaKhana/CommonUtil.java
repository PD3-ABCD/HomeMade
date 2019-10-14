package com.example.MaaKaKhana;

import androidx.annotation.NonNull;

import com.example.MaaKaKhana.ui.login_home.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;

public class CommonUtil {
    private static User user;

    public static User getCurrentUser(){
         FirebaseDatabase.getInstance().getReference("Registration")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                 .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(User.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        return user;
    }
}
