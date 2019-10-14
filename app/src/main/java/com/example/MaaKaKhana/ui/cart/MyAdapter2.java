package com.example.MaaKaKhana.ui.cart;


import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.ui.login_home.FoodItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.HashMap;
import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private static final String TAG = "MyAdapter2";
    private List<FoodItem>listData2;
    private FoodItem ld2;
    ElegantNumberButton elegantNumberButton;


    public MyAdapter2(List<FoodItem> listData2) {
        this.listData2 = listData2;
    }

    @NonNull

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_data,parent,false);
        return new ViewHolder(view);

    }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
    final String id2 = holder.txtid.getText().toString().trim();
    ld2 = listData2.get(position);
    holder.txtid.setText(ld2.getId());
    holder.txtname.setText(ld2.getFood_name());
    // holder.txtdesc.setText(ld2.getFood_desc());
    holder.txtprice.setText("₹ " + String.valueOf(ld2.getFood_price()));
    holder.elegantNumberButton.setNumber(ld2.getFood_quantity().toString());
    holder.elegantNumberButton.setRange(1,10);



    holder.b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FoodItem ld3 = listData2.get(position);
            //final DatabaseReference databaseReference;
            final DatabaseReference databaseReference;
            databaseReference = FirebaseDatabase.getInstance().getReference("Registration");
            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyCart").child(id2).removeValue();
            //int curposition=listData2.indexOf(ld2);
            listData2.remove(ld3);
            notifyDataSetChanged();
            notifyItemRemoved(position);
            new ViewHolder(view);
            Toast.makeText(view.getContext(), "Item Deleted!", Toast.LENGTH_SHORT).show();
        }
    });
        holder.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodItem ld3=listData2.get(position);
                //final DatabaseReference databaseReference;
                final DatabaseReference databaseReference;
                databaseReference= FirebaseDatabase.getInstance().getReference("Registration");
                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyCart").child(ld3.getId()).removeValue();
                //int curposition=listData2.indexOf(ld2);
                listData2.remove(ld3);
                notifyDataSetChanged();
                notifyItemRemoved(position);
                new ViewHolder(view);
                Toast.makeText(view.getContext(), "Item Deleted!" , Toast.LENGTH_SHORT).show();
            }
        });


    holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
        @Override
        public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
            //write logic here
            FoodItem item = listData2.get(position);
            final String id3 = holder.txtid.getText().toString().trim();
            //HashMap<String, Object> result = new HashMap<>();
            //result.put("food_quantity", newValue);


            holder.txtprice.setText("₹ " + Double.toString(item.getFood_price() * newValue));
            final DatabaseReference databaseReference1;
            databaseReference1 = FirebaseDatabase.getInstance().getReference("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyCart").child(id3);
            databaseReference1.child("food_quantity").setValue(newValue);
            notifyDataSetChanged();
            notifyItemChanged(position);
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment myFragment = new CartFragment();



            //activity.getSupportFragmentManager().beginTransaction().remove(myFragment);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();
                holder.txtprice.setText( "₹ "+Double.toString(item.getFood_price() * newValue));
                final DatabaseReference databaseReference2;
                databaseReference2= FirebaseDatabase.getInstance().getReference("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyCart").child(id3);
                databaseReference2.child("food_quantity").setValue(newValue);
                notifyDataSetChanged();
                notifyItemChanged(position);

            //Log.d(TAG, "onValueChange() called with: view = [" + view + "], oldValue = [" + oldValue + "], newValue = [" + newValue + "]");
        }
    });
}

@Override
public int getItemCount() {
        return listData2.size();
        }



public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView txtid, txtname,txtdesc,txtprice;
    private Button b1, b2;
    private ElegantNumberButton elegantNumberButton;


    public ViewHolder(View itemView) {
        super(itemView);
        txtid=(TextView)itemView.findViewById(R.id.idtxt1);
        txtname=(TextView)itemView.findViewById(R.id.itemname);
     //   txtdesc=(TextView)itemView.findViewById(R.id.description1);
        txtprice=(TextView)itemView.findViewById(R.id.itemprice);
        b1=(Button)itemView.findViewById(R.id.btn1);
        b2=(Button)itemView.findViewById(R.id.btnRemove);
        elegantNumberButton=itemView.findViewById(R.id.myButton);

        //button = itemView.findViewById(R.id.myButton);
    }

}



}

