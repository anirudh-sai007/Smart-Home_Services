package com.example.dumee1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter   extends RecyclerView.Adapter<RecyclerAdapter.myviewholder>{

    ArrayList<ProductsModel> datalist;

    public RecyclerAdapter(ArrayList<ProductsModel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_view,parent,false);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.t1.setText(datalist.get(position).getName());
        holder.t2.setText(datalist.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView t1,t2;
        CardView c;
        String s1,s2;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);

            c=itemView.findViewById(R.id.cvl);

            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s1=t1.getText().toString();
                    s2=t2.getText().toString();
                    Intent ni=new Intent(itemView.getContext(),SP_details.class);
                    ni.putExtra("name",s1);
                    ni.putExtra("phno",s2);
                    itemView.getContext().startActivity(ni);


                }
            });
        }
    }
}

