package com.dileep.org.myworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dileep.org.myworld.R;
import com.dileep.org.myworld.Users;

import java.util.ArrayList;
import java.util.List;

public class SendMessageAdapter extends RecyclerView.Adapter<SendMessageAdapter.SendMessageHolder> {

    ArrayList<Users> list;
    Context context;

    public SendMessageAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SendMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sendmessage_listitem,parent,false);
        return new SendMessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SendMessageHolder holder, int position) {
        holder.bind(position);

       // Users users=list.get(position);

       // holder.name.setText(users.getName());
        //holder.phon.setText(users.getPhone());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();

                if (list.get(adapterPosition).isChecked()) {
                    holder.checkBox.setChecked(false);
                    list.get(adapterPosition).setChecked(false);
                }
                else {
                    holder.checkBox.setChecked(true);
                    list.get(adapterPosition).setChecked(true);
                }
            }
        });

    }

    @Override
    public void onBindViewHolder(@NonNull SendMessageHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
public void Allselection(){
    for (int i=0;i<list.size();i++){
        list.get(i).setChecked(false);
    }
        for (int i=0;i<list.size();i++){
            list.get(i).setChecked(true);
        }
notifyDataSetChanged();
}
public  void Firstten(){
    for (int i=0;i<list.size();i++){
        list.get(i).setChecked(false);
    }
        if (list.size()>=10){
            for (int i=0;i<10;i++){
                list.get(i).setChecked(true);
            }
        }else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(true);
            }
        }
    notifyDataSetChanged();
}
    public  void Firstfifty(){
        for (int i=0;i<list.size();i++){
            list.get(i).setChecked(false);
        }
        if (list.size()>=50){
            for (int i=0;i<50;i++){
                list.get(i).setChecked(true);
            }
        }else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(true);
            }
        }
        notifyDataSetChanged();
    }
    public  void Firsthun(){
        for (int i=0;i<list.size();i++){
            list.get(i).setChecked(false);
        }
        if (list.size()>=100){
            for (int i=0;i<100;i++){
                list.get(i).setChecked(true);
            }
        }else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(true);
            }
        }
        notifyDataSetChanged();
    }
    public  void Firstonefifty(){
        for (int i=0;i<list.size();i++){
            list.get(i).setChecked(false);
        }
        if (list.size()>=150){
            for (int i=0;i<150;i++){
                list.get(i).setChecked(true);
            }
        }else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(true);
            }
        }
        notifyDataSetChanged();
    }
    public  void Firsttwohun(){
        for (int i=0;i<list.size();i++){
            list.get(i).setChecked(false);
        }
        if (list.size()>=200){
            for (int i=0;i<200;i++){
                list.get(i).setChecked(true);
            }
        }else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(true);
            }
        }
        notifyDataSetChanged();
    }
    public  void Firstfivehun(){
        for (int i=0;i<list.size();i++){
            list.get(i).setChecked(false);
        }
        if (list.size()>=500){
            for (int i=0;i<500;i++){
                list.get(i).setChecked(true);
            }
        }else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(true);
            }
        }
        notifyDataSetChanged();
    }
    public class SendMessageHolder extends RecyclerView.ViewHolder{
        TextView name,emai,phon;
        CheckBox checkBox;

        public SendMessageHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            phon=(TextView)itemView.findViewById(R.id.phone);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkbox);
        }
        void bind(int position) {
            name.setText(list.get(position).getName());
            phon.setText(list.get(position).getPhone());
            if (list.get(position).isChecked()) {
                checkBox.setChecked(true);
            }
            else {
                checkBox.setChecked(false);
            }
        }
    }
}
