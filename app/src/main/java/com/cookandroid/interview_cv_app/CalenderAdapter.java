package com.cookandroid.interview_cv_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CalenderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Calender> calenderList;

    public CalenderAdapter(List<Calender> calenderList) { this.calenderList = calenderList;}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.calender_item, viewGroup, false);
        return new CalenderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CalenderViewHolder cholder = (CalenderViewHolder) viewHolder;
        String cname = calenderList.get(position).getComname();
        String title = calenderList.get(position).getJobtitle();
        String date = calenderList.get(position).getDeadline();
        String content = calenderList.get(position).getTask();
        cholder.setData(cname, title, date, content);
    }

    @Override
    public int getItemCount() {
        return calenderList.size();
    }

    class CalenderViewHolder extends RecyclerView.ViewHolder{
        private TextView comname;
        private TextView jobtitle;
        private TextView deadline;
        private TextView task;

        public CalenderViewHolder(@NonNull View itemView) {
            super(itemView);
            comname = itemView.findViewById(R.id.com_name);
            jobtitle = itemView.findViewById(R.id.job_title);
            task = itemView.findViewById(R.id.task);
            deadline = itemView.findViewById(R.id.deadline);
        }

        private void setData(String cname, String title, String date, String content){
            comname.setText(cname);
            jobtitle.setText(title);
            deadline.setText(date);
            task.setText(content);
        }
    }
}
