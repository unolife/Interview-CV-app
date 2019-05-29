package com.cookandroid.interview_cv_app;

public class Calender {
    public String getComname() {
        return comname;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public String getTask() {
        return task;
    }

    public String getDeadline() {
        return deadline;
    }

    private String comname;
    private String jobtitle;
    private String task;
    private String deadline;


    public Calender(String comname, String jobtitle, String task, String deadline) {
        this.comname = comname;
        this.jobtitle = jobtitle;
        this.task = task;
        this.deadline = deadline;
    }
}
