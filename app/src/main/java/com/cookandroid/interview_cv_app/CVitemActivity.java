package com.cookandroid.interview_cv_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class CVitemActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_cvitem;

    ImageView ivPicture;
    TextView tvCname;
    TextView hi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        bindView();
        String you = getJsonText();
        hello2(you);
    }

    private void bindView() {
        ivPicture = findViewById(R.id.iv_picture);
        tvCname = findViewById(R.id.tv_cname);
        hi = findViewById(R.id.hi);
    }

    public String getJsonText() {
        // 참고: https://gangzzang.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-JSON-JavaScript-Object-Notation
        // 내부적으로 문자열 편집이 가능한 StringBufffer 생성자
        StringBuffer sb = new StringBuffer();

        try {
            // JSON 객체 생성
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // "CV"이 배열로 구성되어 있으므로 JSON 객체 생성
            JSONArray arr = new JSONArray(obj.getString("CV"));

            for (int i = 0; i < arr.length(); i++) {
                // people 배열 안에 내부 JSON이므로 JSON 내부 객체 생성
                JSONObject insideObject = arr.getJSONObject(i);
                sb.append("아이디: ").append(insideObject.getString("com_id")).append("\n");
                sb.append("회사명: ").append(insideObject.getString("com_name")).append("\n");
//                sb.append("자소서 목록: ").append(insideObject.getString("CVList")).append("\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return sb.toString();
    } // getJsonText

    public void hello2(String a) {
        CV cv = (CV) getIntent().getSerializableExtra("CV");
        ivPicture.setImageResource(cv.getDrawableId());
        tvCname.setText(a);
        hi.setText("1");
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
