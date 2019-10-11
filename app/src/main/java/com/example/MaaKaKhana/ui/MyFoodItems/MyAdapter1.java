package com.example.MaaKaKhana.ui.MyFoodItems;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.ui.MyFoodItems.ListData1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder> {

    private List<ListData1>listData1;
    private ListData1 ld;


    public MyAdapter1(List<ListData1> listData1) {
        this.listData1 = listData1;
    }

    @NonNull

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_items_seller,parent,false);
        return new ViewHolder(view);


    }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ld=listData1.get(position);
       // holder.txtid.setText(ld.getId());
        holder.txtname.setText(ld.getFood_name());
        holder.txtdesc.setText(ld.getFood_desc());
        holder.txtprice.setText("₹ "+String.valueOf(ld.getFood_price()));
        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    final DatabaseReference databaseReference, databaseReference1;
                    databaseReference= FirebaseDatabase.getInstance().getReference("Registration");
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("My Food Items").child(ld.getId()).removeValue();
                    databaseReference1=FirebaseDatabase.getInstance().getReference("FoodItems");
                    databaseReference1.child(ld.getId()).removeValue();

                    notifyDataSetChanged();
                    notifyItemRemoved(position);
                    Toast.makeText(view.getContext(), "Item Deleted! Come Back to check" , Toast.LENGTH_SHORT).show();
                    
            }
        });
        }

@Override
public int getItemCount() {
        return listData1.size();
        }



public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView txtid, txtname,txtdesc,txtprice;
    private Button b1;


    public ViewHolder(View itemView) {
        super(itemView);
       // txtid=(TextView)itemView.findViewById(R.id.idtxt1);
        txtname=(TextView)itemView.findViewById(R.id.name1);
        txtdesc=(TextView)itemView.findViewById(R.id.description1);
        txtprice=(TextView)itemView.findViewById(R.id.price1);
        b1=(Button)itemView.findViewById(R.id.btn1);
    }









}



}

