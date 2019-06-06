package com.cookandroid.interview_cv_app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CoverLetterActivity extends AppCompatActivity {
    private long mCVId = -1;
    private EditText mQuestion, mAnswer;
    Button btnStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_letter);

        // 상단 아이콘
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon2);
        mQuestion = findViewById(R.id.question);
        mAnswer = findViewById(R.id.answer);
        btnStore = findViewById(R.id.store);

        Intent intent = getIntent();
        if (intent != null) {
            mCVId = intent.getLongExtra("id", -1);
            String question = intent.getStringExtra("question");
            String answer = intent.getStringExtra("answer");

            mAnswer.setText(answer);
            mQuestion.setText(question);
        }

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = mQuestion.getText().toString();
                String answer = mAnswer.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put(CV.CVEntry.COLUMN_NAME_QUESTION, question);
                contentValues.put(CV.CVEntry.COLUMN_NAME_ANSWERS, answer);

                SQLiteDatabase db = CVDbHelper.getInstance(getApplicationContext()).getWritableDatabase();
                if (mCVId == -1) {
                    long newRowId = db.insert(CV.CVEntry.TABLE_NAME, null, contentValues);

                    if (newRowId == -1) {
                        Toast.makeText(getApplicationContext(), "저장에 문제가 발생하였습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        startActivity(intent);
                    }
                } else {
                    int count = db.update(CV.CVEntry.TABLE_NAME, contentValues,
                            CV.CVEntry._ID + " = " + mCVId, null);
                    if (count == 0) {
                        Toast.makeText(getApplicationContext(), "수정에 문제가 발생하였습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Q&A가 수정되었습니다", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                    }
                }

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signIn:
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                return true;
            case R.id.signUp:
                Intent intent2 = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent2);
                return true;
            case R.id.myPage:
                Intent intent3 = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent3);
                return true;
            case R.id.CoverLetter:
                Intent intent4 = new Intent(getApplicationContext(), CoverLetterActivity.class);
                startActivity(intent4);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
