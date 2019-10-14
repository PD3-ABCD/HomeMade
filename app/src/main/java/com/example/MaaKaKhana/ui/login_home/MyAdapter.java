package com.example.MaaKaKhana.ui.login_home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MaaKaKhana.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private FoodItem cart_data;
    private DatabaseReference databaseReference;

    private FoodItem ld;
    int max;
    private List<FoodItem>listData;

    public MyAdapter(List<FoodItem>listData) {
        this.listData = listData;
    }

    @NonNull

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_items,parent,false);
        return new ViewHolder(view);
    }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ld=listData.get(position);
        holder.txtid.setText(ld.getId());
        holder.txtname.setText(ld.getFood_name());
        holder.txtdesc.setText(ld.getFood_desc());
        holder.txtprice.setText(ld.getFood_price().toString());

        cart_data=new FoodItem();
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = holder.txtname.getText().toString().trim();
                String desc = holder.txtdesc.getText().toString().trim();
                Double price = Double.parseDouble(holder.txtprice.getText().toString().trim());
                String id3=holder.txtid.getText().toString().trim();
               // int id3=Integer.parseInt(holder.txtid.getText().toString().trim());
                //Double quantity = Double.parseDouble(foodquantity.getText().toString().trim());
                cart_data.setFood_name(name);
                cart_data.setFood_desc(desc);
                cart_data.setFood_price(price);
                cart_data.setFood_quantity(1);
                databaseReference= FirebaseDatabase.getInstance().getReference("Registration");
                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyCart").child(id3).setValue(cart_data);

                Toast.makeText(view.getContext(), "Item Added to Cart", Toast.LENGTH_SHORT).show();

            }
        });
        }

@Override
public int getItemCount() {
        return listData.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView txtid,txtname,txtdesc,txtprice;
    private Button addToCart;
    public ViewHolder(View itemView) {
        super(itemView);
       txtid=(TextView)itemView.findViewById(R.id.id);
        txtname=(TextView)itemView.findViewById(R.id.name);
        txtdesc=(TextView)itemView.findViewById(R.id.description);
        txtprice=(TextView)itemView.findViewById(R.id.price);
        addToCart=(Button)itemView.findViewById(R.id.btn);
    }
}
}

