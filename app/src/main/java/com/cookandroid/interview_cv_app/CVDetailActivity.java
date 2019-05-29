package com.cookandroid.interview_cv_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class CVDetailActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_cvdetail;

    ImageView ivPicture;
    TextView tvCname;
    TextView year;
    String cname;
    Integer resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        CV cv = (CV) getIntent().getSerializableExtra("CV");
        resource = cv.getImagResource();
        cname = cv.getCname();
        bindView();
        ArrayList<Integer> cvYear = getCVList(cname);
        setInformation(cvYear, resource, cname);
    }

    private void bindView() {
        ivPicture = findViewById(R.id.iv_picture);
        tvCname = findViewById(R.id.tv_cname);
        year = findViewById(R.id.year_content);
    }


    public void setInformation(ArrayList<Integer> yearList, Integer resource, String cname) {
        CV cv = (CV) getIntent().getSerializableExtra("CV");
        ivPicture.setImageResource(resource);
        tvCname.setText(cname);
        Log.i("year", String.valueOf(yearList.get(0)));
        year.setText(String.valueOf(yearList.get(0)));
    }

    private ArrayList<Integer> getCVList(String cname){
        ArrayList<CVDetailVO> list_CVdetail = new ArrayList<>();
        ArrayList<Integer> yearList = new ArrayList<>();
        Gson gson = new Gson();

        try{
            InputStream is = getAssets().open("hi.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("CV");
            int index = 1;
            CVDetailVO cvDetailVO = gson.fromJson(jsonArray.get(index).toString(), CVDetailVO.class);
            if( 2017 == cvDetailVO.getYear()){
                yearList.add(cvDetailVO.getYear());
            } else {
                yearList.add(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Log.i("list", String.valueOf(yearList));
        return yearList;
    }
}
