package com.cookandroid.interview_cv_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_main;
    Button btn_cv;
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
        btn_tn = (Button) findViewById(R.id.button_TN);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.signIn:
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                return true;
            case R.id.signUp:
                Intent intent2 = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent2);
                return true;
//            case R.id.myPage:
//                Intent intent3 = new Intent(getApplicationContext(), MyPageActivity.class);
//                startActivity(intent3);
//                return true;
//            case R.id.CoverLetter:
//                Intent intent4 = new Intent(getApplicationContext(), CoverLetterActivity.class);
//                startActivity(intent4);
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
