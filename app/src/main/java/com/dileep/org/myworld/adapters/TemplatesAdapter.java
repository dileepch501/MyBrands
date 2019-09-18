package com.dileep.org.myworld.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.dileep.org.myworld.R;
import com.dileep.org.myworld.SalesDetailesAdpater;
import com.dileep.org.myworld.pojos.TemplatesPojo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class TemplatesAdapter extends RecyclerView.Adapter<TemplatesAdapter.TemplateHolder> {

    ArrayList<TemplatesPojo> templatesPojoArrayList;
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;
    TemplatesPojo templatesPojo;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;
    public static Integer clickedPos;

    public TemplatesAdapter(ArrayList<TemplatesPojo> templatesPojoArrayList, Context context) {
        this.templatesPojoArrayList = templatesPojoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TemplateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.template_item,parent,false);
        return new TemplateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final TemplateHolder holder, int position) {
            TemplatesPojo templatesPojo=templatesPojoArrayList.get(position);
            holder.name.setText(templatesPojo.getName());
            holder.msg.setText(templatesPojo.getMsg());
        holder.radioButton.setChecked(templatesPojoArrayList.get(position).isSelected());
        holder.radioButton.setTag(new Integer(position));

        if(position == 0 && templatesPojoArrayList.get(0).isSelected() && holder.radioButton.isChecked())
        {
            lastChecked = holder.radioButton;
            lastCheckedPos = 0;
        }

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rb=(RadioButton)view;
                 clickedPos = ((Integer)rb.getTag()).intValue();
                if(rb.isChecked())
                {
                    if(lastChecked != null)
                    {
                        lastChecked.setChecked(false);
                        templatesPojoArrayList.get(lastCheckedPos).setSelected(false);
                    }

                    lastChecked = rb;
                    lastCheckedPos = clickedPos;
                }
                else
                    lastChecked = null;

                templatesPojoArrayList.get(clickedPos).setSelected(rb.isChecked());
            }

        });

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                    final View dialogView = LayoutInflater.from(context).inflate(R.layout.dailog, null);
                    dialogBuilder.setView(dialogView);

                    final EditText name=(EditText) dialogView.findViewById(R.id.name);
                    final EditText msg=(EditText)dialogView.findViewById(R.id.message);

                    name.setText(templatesPojoArrayList.get(holder.getAdapterPosition()).getName());
                    msg.setText(templatesPojoArrayList.get(holder.getAdapterPosition()).getMsg());


                   dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           update(templatesPojoArrayList.get(holder.getAdapterPosition()).getTemid(),name.getText().toString(),msg.getText().toString());


                       }
                   }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {

                       }
                   });


                    dialogBuilder.setTitle("Update");
                    final AlertDialog b = dialogBuilder.create();
                    b.show();
                }
            });

    }



    private void update(String temid, String name, String msg) {

        database=FirebaseDatabase.getInstance();
        templatesPojo = new TemplatesPojo();
        templatesPojo.setName(name);
        templatesPojo.setMsg(msg);
        templatesPojo.setTemid(temid);

        reference=database.getReference("templates");
        reference.child(temid).setValue(templatesPojo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }

    @Override
    public int getItemCount() {
        return templatesPojoArrayList.size();
    }

    public class TemplateHolder extends RecyclerView.ViewHolder{

        TextView name,msg;
        ImageView edit;
        RadioButton radioButton;
        public TemplateHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name);
            msg=(TextView)itemView.findViewById(R.id.msg);
            edit=(ImageView)itemView.findViewById(R.id.edit);
            radioButton=(RadioButton)itemView.findViewById(R.id.radiobutton);

        }
    }
}
