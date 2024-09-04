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

public class teamadapter extends RecyclerView.Adapter<teamadapter.MyViewHolder> {
    Context context;
    ArrayList<team_class>list;

    public void setFilteredList(ArrayList<team_class>list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public teamadapter(Context context, ArrayList<team_class> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public teamadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.team_model,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull teamadapter.MyViewHolder holder, int position) {
        team_class m = list.get(position);
        holder.txt1.setText(m.getname());
        Glide.with(context)
                .load(m.getimg())
                .into(holder.img1);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, players.class);
            intent.putExtra("tname", m.getpin());
            context.startActivity(intent);
        });


    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt1,txt2,txt3,txt4,txt5,txt6;
        ImageView img1,img2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.textView1);        //weekend day
            img1=itemView.findViewById(R.id.imageView1);    //t1 logo


        }


    }
}
