package com.cookandroid.interview_cv_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class CVitemActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_cvitem;
    ImageView ivPicture;
    TextView tvCname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        bindView();
        hello();

    }

    private void bindView() {
        ivPicture = findViewById(R.id.iv_picture);
        tvCname = findViewById(R.id.tv_cname);
    }

    public void hello() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            CV cv = (CV) getIntent().getSerializableExtra("CV");
            ivPicture.setImageResource(cv.getDrawableId());
            tvCname.setText(obj.getString("name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public String loadJSONFromAsset() {
        // 출처: https://hashcode.co.kr/questions/2184/android-studio%EB%A1%9C-json%ED%8C%8C%EC%9D%BC-%EB%B6%88%EB%9F%AC%EC%98%A4%EA%B8%B0
        // 출처: https://liveonthekeyboard.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-JSON-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%A3%BC%EA%B3%A0-%EB%B0%9B%EA%B8%B0
        String json = null;
        try {

            InputStream is = getAssets().open("hi.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }


}
