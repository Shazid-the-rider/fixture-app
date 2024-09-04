package com.example.fixtureapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class fixtureadapter extends RecyclerView.Adapter<fixtureadapter.MyViewHolder> {
    Context context;
    ArrayList<matchinfo>list;
    public void setFilteredList(ArrayList<matchinfo>list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public fixtureadapter(Context context, ArrayList<matchinfo> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public fixtureadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.fixturemodel,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull fixtureadapter.MyViewHolder holder, int position) {
        matchinfo m = list.get(position);
        holder.txt6.setText(m.gett1());
        holder.txt5.setText(m.gett2());
        holder.txt2.setText(m.getdate());
        holder.txt3.setText(m.gettime());
        //holder.txt4.setText(m.getst());

        Glide.with(context)
                .load(m.getimg1())
                .into(holder.img1);

        Glide.with(context)
                .load(m.getimg2())
                .into(holder.img2);

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
            //txt1=itemView.findViewById(R.id.textView3);        //weekend day
            txt2=itemView.findViewById(R.id.textView7);     //date
            txt3=itemView.findViewById(R.id.textView12);    //time
            //txt4=itemView.findViewById(R.id.textView10);    //st imageView2
            img1=itemView.findViewById(R.id.imageView);    //t1 logo
            img2=itemView.findViewById(R.id.imageView2);    //t2 logo
            txt5= itemView.findViewById(R.id.textView2);     //t2 name
            txt6=itemView.findViewById(R.id.textView);      //t1 name


        }


    }
}
