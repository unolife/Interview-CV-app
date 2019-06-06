package com.cookandroid.interview_cv_app;

import android.provider.BaseColumns;

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

    public static class CVEntry implements BaseColumns {
        public static final String TABLE_NAME = "coverletter";
        public static final String COLUMN_NAME_QUESTION = "question";
        public static final String COLUMN_NAME_ANSWERS = "answers";
    }

}
