package com.example.MaaKaKhana.ui.OrderHistory;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.ui.OrderHistory.MyAdapter3;
import com.example.MaaKaKhana.ui.login_home.FoodItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryFragment extends Fragment {

    private OrderHistoryViewModel mViewModel;
    private List<ListData3> listData3;
    private RecyclerView rv3;
    private MyAdapter3 adapter3;
    private ListData3 l;


    public static OrderHistoryFragment newInstance() {
        return new OrderHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.order_history_fragment, container, false);
        rv3 = (RecyclerView) view.findViewById(R.id.rev2);
        rv3.setHasFixedSize(true);
        rv3.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData3 = new ArrayList<>();

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyOrders");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        l = npsnapshot.getValue(ListData3.class);
                        l.setId(npsnapshot.getKey());
                        listData3.add(l);
                        //total=total+(l.getFood_price()*l.getFood_quantity());
                        //t_price=(TextView)view.findViewById(R.id.priceTitle);
                        //String t1=Double.toString(total);
                        //Log.d("IGV",t1);
                        //t_price.setText("Rs. "+t1);

                    }
                    adapter3 = new MyAdapter3(listData3);
                    rv3.setAdapter(adapter3);

                } else {
                    Toast.makeText(getActivity(), "Sorry!! No food item available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderHistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}
