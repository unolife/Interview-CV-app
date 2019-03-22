package com.cookandroid.interview_cv_app;

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

        RecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(LayoutManager);

        ArrayList<CVList> CVList = new ArrayList<>();

        CVList.add(new CVList(R.drawable.samsungelectronics, "삼성전자"));
        CVList.add(new CVList(R.drawable.skcnc, "SK C&C"));
        CVList.add(new CVList(R.drawable.lgcns, "LG CNS"));
        CVList.add(new CVList(R.drawable.kt, "KT"));
        CVList.add(new CVList(R.drawable.hyundaiautoever, "현대 오토에버"));
        MyAdapter myAdapter = new MyAdapter(CVList);

        RecyclerView.setAdapter(myAdapter);
    }
}
