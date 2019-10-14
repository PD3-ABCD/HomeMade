package com.example.MaaKaKhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MaaKaKhana.ui.OrderDetails.ListData4;
import com.example.MaaKaKhana.ui.OrderDetails.MyAdapter4;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Order_details extends AppCompatActivity {
    //private OrderDetailsViewModel mViewModel;
    private List<ListData4> listData4;
    private RecyclerView rv3;
    private MyAdapter4 adapter4;
    private ListData4 l;
    private TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        t1=(TextView)findViewById(R.id.MeraSubTitle);

        rv3 = (RecyclerView) findViewById(R.id.rev2);
        rv3.setHasFixedSize(true);
        rv3.setLayoutManager(new LinearLayoutManager(Order_details.this));
        listData4 = new ArrayList<>();
        //Log.d("Hi",message);
        t1.setText(message);
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyOrders").child(message);
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        l = npsnapshot.getValue(ListData4.class);
                        l.setId(npsnapshot.getKey());

                        listData4.add(l);
                        //total=total+(l.getFood_price()*l.getFood_quantity());
                        //t_price=(TextView)view.findViewById(R.id.priceTitle);
                        //String t1=Double.toString(total);
                        //Log.d("IGV",t1);
                        //t_price.setText("Rs. "+t1);

                    }
                    adapter4 = new MyAdapter4(listData4);
                    rv3.setAdapter(adapter4);

                } else {
                    Toast.makeText(Order_details.this, "Sorry!! No food item available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
