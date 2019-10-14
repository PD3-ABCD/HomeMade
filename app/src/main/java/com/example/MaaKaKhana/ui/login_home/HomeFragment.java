package com.example.MaaKaKhana.ui.login_home;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.MaaKaKhana.CommonUtil;
import com.example.MaaKaKhana.MapsActivity;
import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.Loc;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {


    ViewFlipper v_fliper;
    TextView tv;

//    private DatabaseReference mDatabase;
//    private ListView mfoodlist;
//    private ArrayList<String> mlist;
//    ArrayAdapter<String> adapter;
//    datainsert di;

    private List<FoodItem>listData;
    private RecyclerView rv;
    private MyAdapter adapter;
    User currentUser;

    public HomeFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentUser = CommonUtil.getCurrentUser();
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
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        //Recycler part
        rv=(RecyclerView)view.findViewById(R.id.rev);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData=new ArrayList<>();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Registration");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        User l=npsnapshot.getValue(User.class);
                        List<FoodItem> items = null;
                        if (isInBound(l)){
                            items = l.getMyFoodItemsAsList();
                        }
                        System.out.println(l.getFirstName());
                        if (items!=null)
                            listData.addAll(items);
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

    private boolean isInBound(User l) {
        currentUser = CommonUtil.getCurrentUser();
        if (currentUser!=null && currentUser.getLocation()!=null && l!=null && l.getLocation()!=null)
         return currentUser.getLocation().distance(l.getLocation()) <= 2;
        return false;
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
       /*DatabaseReference dbl = FirebaseDatabase.getInstance().getReference("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Location");
        dbl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Loc loc=dataSnapshot.getValue(Loc.class);
                String a = "Location:"+loc.getAdd();
                tv.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

}