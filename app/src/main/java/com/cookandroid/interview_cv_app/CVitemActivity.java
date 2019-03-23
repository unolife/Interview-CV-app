package com.cookandroid.interview_cv_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CVitemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvitem);
        CV cv = (CV) getIntent().getSerializableExtra("CV");

    }
}
