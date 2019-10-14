package com.example.MaaKaKhana.ui.MyFoodItems;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.ui.MyFoodItems.ListData1;
import com.example.MaaKaKhana.ui.MyFoodItems.MyAdapter1;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyFoodItemsFragment extends Fragment {

    private MyFoodItemsViewModel mViewModel;

    private List<ListData1> listData1;
    private RecyclerView rv1;
    private MyAdapter1 adapter1;
    private ListData1 l;

    public MyFoodItemsFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.my_food_items_fragment, container, false);
        View view = inflater.inflate(R.layout.my_food_items_fragment, container, false);

        rv1 = (RecyclerView) view.findViewById(R.id.rev1);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData1 = new ArrayList<>();


        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyFoodItems");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        l = npsnapshot.getValue(ListData1.class);
                        l.setId(npsnapshot.getKey());

                        listData1.add(l);
                    }
                    adapter1 = new MyAdapter1(listData1);
                    rv1.setAdapter(adapter1);
                } else {
                    Toast.makeText(getActivity(), "Sorry!! No food item available", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(getActivity(), "Sorry"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyFoodItemsViewModel.class);
        // TODO: Use the ViewModel


    }


}