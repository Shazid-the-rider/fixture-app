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

public class playeradapter extends RecyclerView.Adapter<playeradapter.MyViewHolder> {

    Context context;
    ArrayList<player_class> list;

    public void setFilteredList(ArrayList<player_class>list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public playeradapter(Context context, ArrayList<player_class> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public playeradapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.playerlist_model,parent,false);
        return new playeradapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull playeradapter.MyViewHolder holder, int position) {
       player_class m = list.get(position);
        holder.txt1.setText(m.getname());
        holder.txt2.setText(m.getpos());
        Glide.with(context)
                .load(m.getimg())
                .into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt1, txt2;
        ImageView img1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.textView1);
            txt2 = itemView.findViewById(R.id.textView2);   //weekend day
            img1 = itemView.findViewById(R.id.imageView1);    //t1 logo


        }

    }
}

