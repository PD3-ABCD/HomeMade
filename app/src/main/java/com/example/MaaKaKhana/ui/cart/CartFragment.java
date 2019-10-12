package com.example.MaaKaKhana.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MaaKaKhana.MeraUPI;
import com.example.MaaKaKhana.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

   // private CartViewModel cartViewModel;
    private List<ListData2> listData2;
    private RecyclerView rv2;
    private MyAdapter2 adapter2;
    private ListData2 l;
    private LinearLayout ll;
    private Button b1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rv2 = (RecyclerView) view.findViewById(R.id.rev2);
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData2 = new ArrayList<>();
        ll = (LinearLayout)view.findViewById(R.id.linearLayout2);
        b1=(Button)view.findViewById(R.id.btnPay);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MeraUPI.class);
                startActivity(intent);
            }
        });

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("My Cart");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    ll.setVisibility(View.VISIBLE);

                    b1.setVisibility(View.VISIBLE);
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        l = npsnapshot.getValue(ListData2.class);
                        l.setId(npsnapshot.getKey());
                        listData2.add(l);
                    }
                    adapter2 = new MyAdapter2(listData2);
                    rv2.setAdapter(adapter2);

                } else {
                    ll.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Sorry!! No food item available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return view;
    }
}