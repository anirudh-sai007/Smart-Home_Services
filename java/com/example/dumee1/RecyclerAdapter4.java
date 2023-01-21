package com.example.dumee1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter4  extends RecyclerView.Adapter<RecyclerAdapter4.myviewholder>{

    ArrayList<ProductsModel3> datalist;

    public RecyclerAdapter4(ArrayList<ProductsModel3> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public RecyclerAdapter4.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reports,parent,false);
        return new RecyclerAdapter4.myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter4.myviewholder holder, int position) {
        holder.t1.setText(datalist.get(position).getName());
        holder.t2.setText(datalist.get(position).getPhone());
        holder.t3.setText(datalist.get(position).getREPORT());


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3;
        CardView c;
        String s1,s2;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);
            t3=itemView.findViewById(R.id.t3);
            c=itemView.findViewById(R.id.cvl);

            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
        }
    }
}

