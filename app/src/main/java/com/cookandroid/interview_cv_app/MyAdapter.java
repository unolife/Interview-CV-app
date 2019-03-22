package com.cookandroid.interview_cv_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView ivPicture;
        TextView tvCname;

        MyViewHolder(View view) {
            super(view);
            ivPicture = view.findViewById(R.id.iv_picture);
            tvCname = view.findViewById(R.id.tv_cname);
        }
    }

    private ArrayList<CVList> CVList;
    MyAdapter(ArrayList<CVList> CVList) {
        this.CVList = CVList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.ivPicture.setImageResource(CVList.get(position).drawableId);
        myViewHolder.tvCname.setText(CVList.get(position).cname);
    }

    @Override
    public int getItemCount() {
        return CVList.size();
    }
}
