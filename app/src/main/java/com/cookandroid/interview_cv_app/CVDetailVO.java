package com.cookandroid.interview_cv_app;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CVDetailVO {
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCvNum() {
        return cvNum;
    }

    public void setCvNum(Integer cvNum) {
        this.cvNum = cvNum;
    }

    public ArrayList<JSONObject> getQnA() {
        return QnA;
    }

    public void setQnA(ArrayList<JSONObject> qnA) {
        QnA = qnA;
    }

    private String company;
    private Integer year;
    private Integer cvNum;
    private ArrayList<JSONObject> QnA;

//    @Override
//    public String toString() {
//        return company + year + cvNum + QnA;
//    }
}
