package com.cookandroid.interview_cv_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CVActivity extends AppCompatActivity {
    ArrayList<CV> CVList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);

        bindView();

        CVList.add(new CV(R.drawable.samsungelectronics, "삼성전자"));
        CVList.add(new CV(R.drawable.skcnc, "SK C&C"));
        CVList.add(new CV(R.drawable.lgcns, "LG CNS"));
        CVList.add(new CV(R.drawable.kt, "KT"));
        CVList.add(new CV(R.drawable.hyundaiautoever, "현대 오토에버"));
    }

    private void bindView() {
        // Recyclerview 생성
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // MyAdapter 생성
        MyAdapter myAdapter = new MyAdapter(CVList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

        // MyAdapter에 리스너 등록
        myAdapter.setListener(new MyAdapter.ButtonListener() {
            @Override
            public void startTargetActivity(int position) {
                Intent intent = new Intent(CVActivity.this, CVDetailActivity.class);
                intent.putExtra("CV", CVList.get(position));
                startActivity(intent);
            }
        });

    }
}
