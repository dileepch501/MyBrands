package com.dileep.org.myworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dileep.org.myworld.Accepojodil;
import com.dileep.org.myworld.R;

import java.util.ArrayList;


public class Accessadapter extends RecyclerView.Adapter<Accessadapter.AccHolder> {

    ArrayList<Accepojodil> accepojodilArrayList;
    Context context;

    public Accessadapter(ArrayList<Accepojodil> accepojodilArrayList, Context context) {
        this.accepojodilArrayList = accepojodilArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AccHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.salesinfo_list_item,parent,false);
        return new AccHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccHolder holder, int position) {
                Accepojodil accepojodil=accepojodilArrayList.get(position);
                holder.nameid.setText(accepojodil.getModelnumber());
                holder.cost.setText(accepojodil.getAmount());
    }

    @Override
    public int getItemCount() {
        return accepojodilArrayList.size();
    }

    public class AccHolder extends RecyclerView.ViewHolder{
        TextView nameid, cost;

        public AccHolder(@NonNull View itemView) {
            super(itemView);
            nameid = (TextView) itemView.findViewById(R.id.nameid);
            cost = (TextView) itemView.findViewById(R.id.cost);
        }
    }
}
