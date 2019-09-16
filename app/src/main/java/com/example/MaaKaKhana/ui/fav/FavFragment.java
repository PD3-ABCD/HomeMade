package com.example.MaaKaKhana.ui.fav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.datainsert;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FavFragment extends Fragment {

    //private FavViewModel favViewModel;
    EditText foodname,fooddesc,foodprice;
    Button savebtn,showbtn;
    DatabaseReference dbref;

    datainsert food_detail;
    long maxid=0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // favViewModel =
         //       ViewModelProviders.of(this).get(FavViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_fav, container, false);
        //final TextView textView = root.findViewById(R.id.text_fav);
        //favViewModel.getText().observe(this, new Observer<String>() {
          //  @Override
            //public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            //}
        //});

        View view= inflater.inflate(R.layout.fragment_fav,container,false);
        foodname = view.findViewById(R.id.item_name);
        fooddesc = (EditText) view.findViewById(R.id.item_desc);
        foodprice = (EditText) view.findViewById(R.id.item_price);
        savebtn = (Button) view.findViewById(R.id.item_save);
        showbtn=(Button)view.findViewById(R.id.showlist);
        food_detail = new datainsert();
        dbref = FirebaseDatabase.getInstance().getReference().child("datainsert").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // if (dataSnapshot.exists())
                 //   maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = foodname.getText().toString().trim();
                String desc = fooddesc.getText().toString().trim();
                Double price = Double.parseDouble(foodprice.getText().toString().trim());
                food_detail.setFood_name(name);
                food_detail.setFood_desc(desc);
                food_detail.setFood_price(price);
                //dbref.push().setValue(food_detail);
                dbref.setValue(food_detail);
                Toast.makeText(getActivity(), "Food detail inserted successfully!", Toast.LENGTH_LONG);
            }
        });

        showbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Intent myIntent = new Intent(FavFragment.this, displaydatalist.class);
                //FavFragment.this.startActivity(myIntent);

                FavFragment saveButton = new FavFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Save, saveButton);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}