package com.cookandroid.interview_cv_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CVitemActivity extends AppCompatActivity {

    ImageView ivPicture;
    TextView tvCname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvitem);
        CV cv = (CV) getIntent().getSerializableExtra("CV");

        ivPicture = findViewById(R.id.iv_picture);
        tvCname = findViewById(R.id.tv_cname);

        ivPicture.setImageResource(cv.getDrawableId());
        tvCname.setText(cv.getCname().toString());
    }
}
