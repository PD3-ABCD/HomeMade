package com.example.MaaKaKhana.ui.MyFoodItems;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MaaKaKhana.Home_Bottom;
import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.ui.MyFoodItems.ListData1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder> {

    private List<ListData1>listData1;
    private ListData1 ld;
    private String id2;
    MyAdapter1 myAdapter1;


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
public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ld=listData1.get(position);
        holder.txtid.setText(ld.getId());
        holder.txtname.setText(ld.getFood_name());
        holder.txtdesc.setText(ld.getFood_desc());
        holder.txtprice.setText("₹ "+String.valueOf(ld.getFood_price()));
        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    id2=holder.txtid.getText().toString().trim();

                    final DatabaseReference databaseReference, databaseReference1, databaseReference2;
                    databaseReference= FirebaseDatabase.getInstance().getReference("Registration");
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("My Food Items").child(id2).removeValue();
                    databaseReference1=FirebaseDatabase.getInstance().getReference("FoodItems");
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("My Cart").child(id2).removeValue();
                    databaseReference1.child(id2).removeValue();
                    //notifyDataSetChanged();
                    //notifyItemRemoved(position);
                    //myAdapter1.refresh(listData1);
                new ViewHolder(view);

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
        txtid=(TextView)itemView.findViewById(R.id.idtxt);
        txtname=(TextView)itemView.findViewById(R.id.name1);
        txtdesc=(TextView)itemView.findViewById(R.id.description1);
        txtprice=(TextView)itemView.findViewById(R.id.price1);
        b1=(Button)itemView.findViewById(R.id.btn1);
    }
    }

    public void refresh(List<ListData1> listData1)
    {
        this.listData1.clear();
        this.listData1.addAll(listData1);
        notifyDataSetChanged();
    }




}

