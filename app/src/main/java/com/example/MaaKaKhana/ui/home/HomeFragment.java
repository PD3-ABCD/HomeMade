package com.example.MaaKaKhana.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.datainsert;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    ViewFlipper v_fliper;
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


        View view= inflater.inflate(R.layout.fragment_home,container,false);
        v_fliper = view.findViewById(R.id.v_fliper);

        int images[]= {R.drawable.imag1,R.drawable.imag2,R.drawable.imag3};

        v_fliper = view.findViewById(R.id.v_fliper);

        for(int image:images){
            fliperImages(image);
        }

        rv=(RecyclerView)view.findViewById(R.id.rev);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData=new ArrayList<>();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("datainsert");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        ListData l=npsnapshot.getValue(ListData.class);
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

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(getActivity(),ImageSliderBar.class);
//                startActivity(intent);
//            }
//        });



//        di=new datainsert();
//        mfoodlist = (ListView) view.findViewById(R.id.foodlistview);
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("datainsert");
//        mlist=new ArrayList<>();
//        adapter=new ArrayAdapter<String>(getActivity(),R.layout.food_info,R.id.foodInfo, mlist);
//
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot ds: dataSnapshot.getChildren())
//                {
//                    di= ds.getValue(datainsert.class);
//                    mlist.add(di.getFood_name().toString()+" "+di.getFood_desc().toString()+" "+ String.valueOf(di.getFood_price()));
//                }
//                mfoodlist.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

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
}
