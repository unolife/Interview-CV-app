package com.cookandroid.interview_cv_app;

import android.provider.BaseColumns;

public class CV  {
    public static class CVEntry implements BaseColumns {
        public static final String TABLE_NAME = "coverletter";
        public static final String COLUMN_NAME_QUESTION = "question";
        public static final String COLUMN_NAME_ANSWERS = "answers";
    }
}
