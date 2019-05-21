package com.cookandroid.interview_cv_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_main;
    Button btn_cv;
    Button btn_iv;
    Button btn_tn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        bindView();

        // 자소서 버튼
        btn_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CVActivity.class);
                intent.putExtra("cv", new CV(1, ""));
                startActivity(intent);
            }
        });

        // 면접 질문 버튼
        btn_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(MainActivity.this, IVActivity.class);
                startActivity(Intent);
            }
        });

        // 용어 버튼
        btn_tn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(MainActivity.this, CalenderActivity.class);
                startActivity(Intent);
            }
        });
    }

    private void bindView() {
        // 상단 아이콘
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon2);

        btn_cv = (Button) findViewById(R.id.button_CV);
        btn_iv = (Button) findViewById(R.id.button_IV);
        btn_tn = (Button) findViewById(R.id.button_TN);
    }
}
