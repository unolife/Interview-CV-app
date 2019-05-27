package com.cookandroid.interview_cv_app;

import java.io.Serializable;

public class CV implements Serializable {
    private int imagResource;
    private String cname;

    public int getImagResource() {
        return imagResource;
    }

    public String getCname() {
        return cname;
    }

    public CV(int imagResource, String cname) {
        this.imagResource = imagResource;
        this.cname = cname;
    }
}
