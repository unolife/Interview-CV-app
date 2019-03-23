package com.cookandroid.interview_cv_app;

import java.io.Serializable;

public class CV implements Serializable {
    private int drawableId;
    private String cname;

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public CV(int drawableId, String cname) {

        this.drawableId = drawableId;
        this.cname = cname;
    }
}
