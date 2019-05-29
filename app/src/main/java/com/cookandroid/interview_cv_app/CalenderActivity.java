package com.cookandroid.interview_cv_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CalenderActivity extends AppCompatActivity {

    // chrome://inspect/#devices  -> stetho debugging address

    private RecyclerView recyclerView;
    TextView textView1, textView2, textView3;
    MyService service;
    JSONObject jsonObject, result;
    Object first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        recyclerView = findViewById(R.id.recycler_view2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        List<Calender> calenderList = new ArrayList<>();
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));
        calenderList.add(new Calender("회사명", "직무이름","업무","마감일"));

        CalenderAdapter adapter = new CalenderAdapter(calenderList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // Stetho and Retrofit
        Stetho.initializeWithDefaults(this);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://oofx7lo6we.execute-api.ap-northeast-2.amazonaws.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MyService.class);

        textView1 = findViewById(R.id.text1);
//        textView2 = findViewById(R.id.text2);
//        textView3 = findViewById(R.id.text3);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });

//        textView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    result = (JSONObject) jsonObject.get("body");
//                    first = result.get("0");
//
//                    Log.v("Test", String.valueOf(result));
//                    textView3.setText(String.valueOf(first));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        Log.v("Test", String.valueOf(jsonObject));
    }

    public void requestData(){
        Call<ResponseBody> getCalenderData = service.getData("testing");
        // enqueue는 비동기 방식임
        getCalenderData.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    jsonObject = new JSONObject(response.body().string());
                    Log.v("Test", String.valueOf(jsonObject));
//                    textView2.setText(String.valueOf(jsonObject));

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}

