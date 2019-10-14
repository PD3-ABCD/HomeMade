package com.example.MaaKaKhana.ui.OrderHistory;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.MaaKaKhana.Home_Bottom;
import com.example.MaaKaKhana.Order_details;
import com.example.MaaKaKhana.R;

import java.util.List;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.ViewHolder> {

    private static final String TAG = "MyAdapter2";
    private List<ListData3>listData3;
    private ListData3 ld3;
    private Context context;
    ElegantNumberButton elegantNumberButton;


    public MyAdapter3(List<ListData3> listData2) {
        this.listData3 = listData2;

    }

    @NonNull

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_data,parent,false);
        return new ViewHolder(view);

    }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


    ld3 = listData3.get(position);
    holder.txtid.setText(ld3.getId());
    holder.txtname.setText(ld3.getId());
    final String id2 = holder.txtid.getText().toString().trim();
    holder.b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), Order_details.class);
            intent.putExtra("message", id2);
            view.getContext().startActivity(intent);
        }
    });

}

@Override
public int getItemCount() {
        return listData3.size();
        }



public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView txtid, txtname;
    private Button b1;



    public ViewHolder(View itemView) {
        super(itemView);
        txtid=(TextView)itemView.findViewById(R.id.idtxt1);
        txtname=(TextView)itemView.findViewById(R.id.itemname);
        b1=(Button)itemView.findViewById(R.id.btnorder);



        //button = itemView.findViewById(R.id.myButton);
    }

}

}

