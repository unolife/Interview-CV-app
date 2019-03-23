package com.cookandroid.interview_cv_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_cv, btn_iv, btn_tn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 상단 아이콘
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon2);

        btn_cv = (Button) findViewById(R.id.button_CV);
        btn_iv = (Button) findViewById(R.id.button_IV);
        btn_tn = (Button) findViewById(R.id.button_TN);

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
                Intent Intent = new Intent(MainActivity.this, TermActivity.class);
                startActivity(Intent);
            }
        });
    }
}
