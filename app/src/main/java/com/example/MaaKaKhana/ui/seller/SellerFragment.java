package com.example.MaaKaKhana.ui.seller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.datainsert;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SellerFragment extends Fragment {

    //private SellerViewModel favViewModel;
    EditText foodname,fooddesc,foodprice, foodquantity;
    Button savebtn,showbtn;
    DatabaseReference dbref;
    datainsert food_detail;
    long maxid=0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // favViewModel =
         //       ViewModelProviders.of(this).get(SellerViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_seller, container, false);
        //final TextView textView = root.findViewById(R.id.text_fav);
        //favViewModel.getText().observe(this, new Observer<String>() {
          //  @Override
            //public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            //}
        //});

        View view= inflater.inflate(R.layout.fragment_seller,container,false);
        foodname = view.findViewById(R.id.item_name);
        fooddesc = (EditText) view.findViewById(R.id.item_desc);
        foodprice = (EditText) view.findViewById(R.id.item_price);
        foodquantity = (EditText) view.findViewById(R.id.item_quantity);
        savebtn = (Button) view.findViewById(R.id.item_save);
        //showbtn=(Button)view.findViewById(R.id.showlist);
        food_detail = new datainsert();
        dbref = FirebaseDatabase.getInstance().getReference().child("FoodItems");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
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
                Double quantity = Double.parseDouble(foodquantity.getText().toString().trim());
                food_detail.setFood_name(name);
                food_detail.setFood_desc(desc);
                food_detail.setFood_price(price);
                food_detail.setFood_quantity(quantity);
                //dbref.push().setValue(food_detail);
                dbref.child(String.valueOf(maxid + 1)).setValue(food_detail);
                Toast.makeText(getActivity(), "Food detail inserted successfully!", Toast.LENGTH_LONG).show();
            }
        });

        /*showbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Intent myIntent = new Intent(SellerFragment.this, displaydatalist.class);
                //SellerFragment.this.startActivity(myIntent);

                SellerFragment saveButton = new SellerFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Save, saveButton);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });*/
        return view;
    }
}