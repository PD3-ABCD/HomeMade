package com.example.MaaKaKhana.ui.cart;


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

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private List<ListData2>listData2;
    private ListData2 ld;


    public MyAdapter2(List<ListData2> listData2) {
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
        ld=listData2.get(position);
        holder.txtid.setText(ld.getId());
        holder.txtname.setText(ld.getFood_name());
       // holder.txtdesc.setText(ld.getFood_desc());
        holder.txtprice.setText("₹ "+String.valueOf(ld.getFood_price()));

        holder.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id2=holder.txtid.getText().toString().trim();

                final DatabaseReference databaseReference;
                databaseReference= FirebaseDatabase.getInstance().getReference("Registration");
                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("My Cart").child(id2).removeValue();
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


    public ViewHolder(View itemView) {
        super(itemView);
        txtid=(TextView)itemView.findViewById(R.id.idtxt1);
        txtname=(TextView)itemView.findViewById(R.id.itemname);
       // txtdesc=(TextView)itemView.findViewById(R.id.description1);
        txtprice=(TextView)itemView.findViewById(R.id.itemprice);
        b1=(Button)itemView.findViewById(R.id.btn1);
        b2=(Button)itemView.findViewById(R.id.btnRemove);
    }

}

}

