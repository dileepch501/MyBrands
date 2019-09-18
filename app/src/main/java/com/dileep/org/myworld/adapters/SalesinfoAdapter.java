package com.dileep.org.myworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dileep.org.myworld.Mobilepojo;
import com.dileep.org.myworld.R;

import java.util.ArrayList;

public class SalesinfoAdapter extends RecyclerView.Adapter<SalesinfoAdapter.SalesHolder> {

    ArrayList<Mobilepojo> mobilepojoArrayList;
    Context context;

    public SalesinfoAdapter(ArrayList<Mobilepojo> mobilepojoArrayList, Context context) {
        this.mobilepojoArrayList = mobilepojoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SalesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salesinfo_list_item, parent, false);
        return new SalesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesHolder holder, int position) {

        Mobilepojo mobilepojo=mobilepojoArrayList.get(position);
        holder.nameid.setText(mobilepojo.getImei());
        holder.cost.setText(mobilepojo.getAmount());
    }

    @Override
    public int getItemCount() {
        return mobilepojoArrayList.size();
    }

    public class SalesHolder extends RecyclerView.ViewHolder {
        TextView nameid, cost;

        public SalesHolder(@NonNull View itemView) {
            super(itemView);
            nameid = (TextView) itemView.findViewById(R.id.nameid);
            cost = (TextView) itemView.findViewById(R.id.cost);
        }
    }
}
