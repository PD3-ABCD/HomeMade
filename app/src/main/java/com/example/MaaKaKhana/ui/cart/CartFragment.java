package com.example.MaaKaKhana.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.ui.MyFoodItems.ListData1;
import com.example.MaaKaKhana.ui.MyFoodItems.MyAdapter1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;
    private List<ListData2> listData2;
    private RecyclerView rv2;
    private MyAdapter2 adapter2;
    private ListData2 l;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rv2 = (RecyclerView) view.findViewById(R.id.rev2);
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData2 = new ArrayList<>();

     //   cartViewModel =
       //         ViewModelProviders.of(this).get(CartViewModel.class);
      //  View view = inflater.inflate(R.layout.fragment_cart, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        //cartViewModel.getText().observe(this, new Observer<String>() {
       //     @Override
         //   public void onChanged(@Nullable String s) {
           //     textView.setText(s);
            //}
        //});

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("My Cart");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        l = npsnapshot.getValue(ListData2.class);
                        l.setId(npsnapshot.getKey());
                        listData2.add(l);
                    }
                    adapter2 = new MyAdapter2(listData2);
                    rv2.setAdapter(adapter2);
                } else {
                    Toast.makeText(getActivity(), "Sorry!! No food item available", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});
        return view;
    }
}