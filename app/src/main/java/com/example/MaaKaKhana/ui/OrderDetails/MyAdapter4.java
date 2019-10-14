package com.example.MaaKaKhana.ui.OrderDetails;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.MaaKaKhana.Home_Bottom;
import com.example.MaaKaKhana.R;

import java.util.List;

public class MyAdapter4 extends RecyclerView.Adapter<MyAdapter4.ViewHolder> {

    private static final String TAG = "MyAdapter2";
    private List<ListData4>listData3;
    private ListData4 ld3;
    private Context context;
    ElegantNumberButton elegantNumberButton;


    public MyAdapter4(List<ListData4> listData2) {
        this.listData3 = listData2;

    }

    @NonNull

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details,parent,false);
        return new ViewHolder(view);

    }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

    ld3 = listData3.get(position);
    holder.txtid.setText(ld3.getId());
    holder.txtname.setText(ld3.getFood_name());
    holder.txtprice.setText("Rs. "+String.valueOf(ld3.getFood_price()));
    holder.txtQuantity.setText("Quantity: "+String.valueOf(ld3.getFood_quantity()));

}

@Override
public int getItemCount() {
        return listData3.size();
        }



public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView txtid, txtname, txtprice, txtQuantity;




    public ViewHolder(View itemView) {
        super(itemView);
        txtid=(TextView)itemView.findViewById(R.id.idtxt1);
        txtname=(TextView)itemView.findViewById(R.id.itemname);
        txtprice=(TextView)itemView.findViewById(R.id.itemprice);
        txtQuantity=(TextView)itemView.findViewById(R.id.itemquantity);

    }

}

}

