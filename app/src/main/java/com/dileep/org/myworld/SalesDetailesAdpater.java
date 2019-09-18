package com.dileep.org.myworld;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SalesDetailesAdpater extends RecyclerView.Adapter<SalesDetailesAdpater.SalesHoder>{

    ArrayList<Salesdetailespojo>salesdetailespojoArrayList;
    Context context;
    ArrayList<Users> salesmanArralist;
    FirebaseDatabase database, detailesDb,databaseac,salesmandb;
    DatabaseReference reference, detailesRef,referenceac,salesmanref;
    ArrayList<Mobilepojo> mobilepojoArrayList=new ArrayList<>();
    Mobilepojo mobilepojo;


    public SalesDetailesAdpater(ArrayList<Salesdetailespojo> salesdetailespojoArrayList, ArrayList<Users> salesmanArralist, Context context) {
        this.salesdetailespojoArrayList = salesdetailespojoArrayList;
        this.context = context;
        this.salesmanArralist=salesmanArralist;



    }

    @NonNull
    @Override
    public SalesHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_detailes_listitem,parent,false);
       return new SalesHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SalesHoder holder, int position) {

        Users userspojo=salesmanArralist.get(position);

        holder.salesname.setText(userspojo.getName());

        for (int i=0;i<salesdetailespojoArrayList.size();i++){
            if (salesmanArralist.get(holder.getAdapterPosition()).getPhone().equals(salesdetailespojoArrayList.get(i).getSalesmanid())){
                holder.mcount.setText(String.valueOf(salesdetailespojoArrayList.get(i).getMobilescount()));
                holder.acount.setText(String.valueOf(salesdetailespojoArrayList.get(i).getAccesscount()));
            }
        }



       // holder.mcount.setText(String.valueOf(getmobilecount(userspojo.getPhone())));


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //salesdetailespojoArrayList.clear();
                Intent salesmaninfo=new Intent(context,SalesmanInfo.class);
                salesmaninfo.putExtra("id",salesmanArralist.get(holder.getAdapterPosition()).getPhone());
                salesmaninfo.putExtra("name",salesmanArralist.get(holder.getAdapterPosition()).getName());
                context.startActivity(salesmaninfo);
            }
        });
        //holder.mcount.setText(String.valueOf( pojo.getMobilescount()));
        //holder.acount.setText(String.valueOf(pojo.getAccesscount()));
    }

    @Override
    public int getItemCount() {
        return salesmanArralist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SalesHoder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class SalesHoder extends RecyclerView.ViewHolder{

        TextView salesname,mcount,acount;
        LinearLayout layout;


        public SalesHoder(@NonNull View itemView) {
            super(itemView);

            salesname=(TextView)itemView.findViewById(R.id.salemanid);
            mcount=(TextView)itemView.findViewById(R.id.mcount);
            acount=(TextView)itemView.findViewById(R.id.acount);
            layout=(LinearLayout)itemView.findViewById(R.id.layout);

        }
    }
}
