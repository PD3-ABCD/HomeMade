package com.example.MaaKaKhana.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MaaKaKhana.R;
import com.example.MaaKaKhana.ui.home.ListData;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListData>listData;

    public MyAdapter(List<ListData>listData) {
        this.listData = listData;
    }

    @NonNull

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data,parent,false);
        return new ViewHolder(view);
    }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListData ld=listData.get(position);
//        holder.txtid.setText(ld.getId());
        holder.txtname.setText(ld.getFood_name());
        holder.txtdesc.setText(ld.getFood_desc());
        holder.txtprice.setText("₹ "+String.valueOf(ld.getFood_price()));
        }

@Override
public int getItemCount() {
        return listData.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView txtname,txtdesc,txtprice;
    public ViewHolder(View itemView) {
        super(itemView);
        //txtid=(TextView)itemView.findViewById(R.id.idtxt);
        txtname=(TextView)itemView.findViewById(R.id.name);
        txtdesc=(TextView)itemView.findViewById(R.id.description);
        txtprice=(TextView)itemView.findViewById(R.id.price);
    }
}
}

