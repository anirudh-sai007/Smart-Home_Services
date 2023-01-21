package com.example.dumee1;

import android.content.Context;
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

public class RecyclerAdapter2  extends RecyclerView.Adapter<RecyclerAdapter2.myviewholder>{

    ArrayList<ProductsModel2> datalist;
Context context;
    public RecyclerAdapter2(Context context,ArrayList<ProductsModel2> datalist) {
        this.datalist = datalist;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapter2.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_view2,parent,false);
        return new RecyclerAdapter2.myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter2.myviewholder holder, int position) {
      //  Toast.makeText(context, ""+datalist.get(position).getName(, Toast.LENGTH_SHORT).show();
        holder.t1.setText(datalist.get(position).getName());
        holder.t2.setText(datalist.get(position).toString());

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

      /*      c=itemView.findViewById(R.id.cvl);

            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s1=t1.getText().toString();
                    s2=t2.getText().toString();
                    Intent ni=new Intent(itemView.getContext(),SP_details2.class);
                    ni.putExtra("name",s1);
                    ni.putExtra("phno",s2);
                    itemView.getContext().startActivity(ni);
                }
            });*/
        }
    }
}

