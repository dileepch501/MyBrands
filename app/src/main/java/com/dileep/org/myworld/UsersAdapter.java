package com.dileep.org.myworld;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.net.URLEncoder;
import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {
    ArrayList<Users> list;
    Context context;

    public UsersAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new UsersHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {

        final Users listPojo=list.get(position);
       /* Glide.with(context).load(R.drawable.usericon)
                .into(holder.image);*/

        holder.name.setText(listPojo.getName());
        holder.phon.setText(listPojo.getPhone());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openWhatsApp(listPojo.getPhone());
            }
        });
    }

    private void openWhatsApp(String phonenumber) {
        String smsNumber = "91"+phonenumber; //without '+'
        String dlink="https://www.youtube.com/watch?v=z9_EsRclGL0";
        String message="Hii !";
        try {
            PackageManager packageManager =context. getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);
            String url = "https://api.whatsapp.com/send?phone="+ smsNumber +"&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                context.startActivity(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UsersHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,emai,phon;
    public UsersHolder(@NonNull View itemView) {
        super(itemView);

        image=(ImageView)itemView.findViewById(R.id.whatsapp);
        name=(TextView)itemView.findViewById(R.id.name);
        phon=(TextView)itemView.findViewById(R.id.phone);
    }
}
}
