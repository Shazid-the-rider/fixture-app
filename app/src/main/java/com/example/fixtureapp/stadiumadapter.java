package com.example.fixtureapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class stadiumadapter extends RecyclerView.Adapter<stadiumadapter.MyViewHolder> {

    Context context;
    ArrayList<stadium_class> list;

    public void setFilteredList(ArrayList<stadium_class>list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public stadiumadapter(Context context, ArrayList<stadium_class> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public stadiumadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.stadium_sample,parent,false);
        return new stadiumadapter.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull stadiumadapter.MyViewHolder holder, int position) {
        stadium_class m = list.get(position);
        holder.txt1.setText(m.getn());
        holder.txt2.setText(m.gett());
        Glide.with(context)
                .load(m.getimg())
                .into(holder.img1);
        Glide.with(context)
                .load(m.getimg1())
                .into(holder.img2);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt1, txt2;
        ImageView img1,img2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.textView1);
            txt2 = itemView.findViewById(R.id.textView2);   //weekend day
            img1 = itemView.findViewById(R.id.imageView1);
            img2 = itemView.findViewById(R.id.imageView2);//t1 logo


        }

    }
}

