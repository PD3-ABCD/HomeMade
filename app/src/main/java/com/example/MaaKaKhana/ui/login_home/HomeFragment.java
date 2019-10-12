package com.example.MaaKaKhana.ui.login_home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MaaKaKhana.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    ViewFlipper v_fliper;
    TextView tv;

//    private DatabaseReference mDatabase;
//    private ListView mfoodlist;
//    private ArrayList<String> mlist;
//    ArrayAdapter<String> adapter;
//    datainsert di;

    private List<ListData>listData;
    private RecyclerView rv;
    private MyAdapter adapter;

    public HomeFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        //Flipper part
        View view= inflater.inflate(R.layout.fragment_loginhome,container,false);
        v_fliper = view.findViewById(R.id.v_fliper);

        int images[]= {R.drawable.imag1,R.drawable.imag2,R.drawable.imag3};

        v_fliper = view.findViewById(R.id.v_fliper);

        for(int image:images){
            fliperImages(image);
        }


        //Address bar
        tv=(TextView)view.findViewById(R.id.location_details);
        address_view();


        //Recycler part
        rv=(RecyclerView)view.findViewById(R.id.rev);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData=new ArrayList<>();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("FoodItems");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        ListData l=npsnapshot.getValue(ListData.class);
                        l.setId(npsnapshot.getKey());
                        listData.add(l);
                    }
                    adapter=new MyAdapter(listData);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    public void fliperImages(int image){
        ImageView imageView=new ImageView(getContext());
        imageView.setImageResource(image);

        v_fliper.addView(imageView);
        v_fliper.setFlipInterval(3000);
        v_fliper.setAutoStart(true);


        //ANIMATION
        v_fliper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        v_fliper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }

    public void address_view(){
        DatabaseReference l = FirebaseDatabase.getInstance().getReference().child("Loc").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("address");
        l.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a="Location: "+dataSnapshot.getValue(String.class);
                tv.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}