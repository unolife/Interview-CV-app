package com.cookandroid.interview_cv_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CVActivity extends AppCompatActivity {

    RecyclerView RecyclerView;
    RecyclerView.LayoutManager LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);

        // Recyclerview 생성
        RecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(LayoutManager);

        final ArrayList<CV> CVlist = new ArrayList<>();

        CVlist.add(new CV(R.drawable.samsungelectronics, "삼성전자"));
        CVlist.add(new CV(R.drawable.skcnc, "SK C&C"));
        CVlist.add(new CV(R.drawable.lgcns, "LG CNS"));
        CVlist.add(new CV(R.drawable.kt, "KT"));
        CVlist.add(new CV(R.drawable.hyundaiautoever, "현대 오토에버"));

        // MyAdapter 생성
        MyAdapter myAdapter = new MyAdapter(CVlist);

        // MyAdapter에 리스너 등록
        myAdapter.setListener(new MyAdapter.ButtonListener() {
            @Override
            public void startTargetActivity(int position) {
                Intent intent = new Intent(CVActivity.this, CVitemActivity.class);
                intent.putExtra("CV", CVlist.get(position));
                startActivity(intent);
            }
        });

        RecyclerView.setAdapter(myAdapter);
    }
}
