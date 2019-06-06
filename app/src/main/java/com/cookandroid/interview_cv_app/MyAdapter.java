package com.cookandroid.interview_cv_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {
    private List<NewsData> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView TextViewTitle;
        public TextView TextViewContent;
        public ImageView ImageViewTitle;

        public MyViewHolder(View v) {
            super(v);
            TextViewTitle = v.findViewById(R.id.TextView_title);
            TextViewContent = v.findViewById(R.id.TextView_content);
            ImageViewTitle = v.findViewById(R.id.ImageView_title);
        }
    }

    public MyAdapter(List<NewsData> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout  v = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_news, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.TextViewTitle.setText(mDataset[position]);
    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
