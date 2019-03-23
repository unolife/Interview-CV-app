package com.cookandroid.interview_cv_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ButtonListener listener;
    private ArrayList<CV> CV;

    MyAdapter(ArrayList<CV> CV) {
        this.CV = CV;
    }

    public interface ButtonListener {
        void startTargetActivity(int position);
    }

    public void setListener(ButtonListener listener) {
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPicture;
        Button btnCname;

        MyViewHolder(View view) {
            super(view);
            ivPicture = view.findViewById(R.id.iv_picture);
            btnCname = view.findViewById(R.id.btn_cname);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.ivPicture.setImageResource(CV.get(position).getDrawableId());
        myViewHolder.btnCname.setText(CV.get(position).getCname());

        myViewHolder.btnCname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.startTargetActivity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CV.size();
    }

}
