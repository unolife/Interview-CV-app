package com.cookandroid.interview_cv_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_main;
    private static final int REQUEST_CODE_INSERT = 1000;
    private CVAdapter cvAdapter;
    FloatingActionButton fab;
    ImageView news;
//    Button btn_tn;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        bindView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoverLetterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });
        Cursor cursor = getCVCursor();
        cvAdapter = new MainActivity.CVAdapter(this, cursor);
        listView.setAdapter(cvAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CoverLetterActivity.class);

                Cursor cursor = (Cursor) cvAdapter.getItem(position);
                String question = cursor.getString(cursor.getColumnIndexOrThrow(CV.CVEntry.COLUMN_NAME_QUESTION));
                String answer = cursor.getString(cursor.getColumnIndexOrThrow(CV.CVEntry.COLUMN_NAME_ANSWERS));

                intent.putExtra("id", id);
                intent.putExtra("question", question);
                intent.putExtra("answer", answer);

                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });

        // 오래 누르면 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final long deleteId = id;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Q&A 삭제");
                builder.setMessage("메모를 삭제하시겠습니까?");
                builder.setNegativeButton("취소", null);
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = CVDbHelper.getInstance(MainActivity.this).getWritableDatabase();
                        int deleteCount = db.delete(CV.CVEntry.TABLE_NAME,
                                CV.CVEntry._ID + "=" + deleteId, null);
                        if (deleteCount == 0) {
                            Toast.makeText(MainActivity.this, "삭제에 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            cvAdapter.swapCursor(getCVCursor());
                            Toast.makeText(MainActivity.this, "Q&A가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
                return true;
            }
        });

//        // 용어 버튼
//        btn_tn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent Intent = new Intent(MainActivity.this, CalenderActivity.class);
//                startActivity(Intent);
//            }
//        });
    }

    private Cursor getCVCursor() {
        CVDbHelper dbHelper = CVDbHelper.getInstance(this);
        return dbHelper.getReadableDatabase()
                .query(CV.CVEntry.TABLE_NAME,
                        null, null, null, null, null, null);
    }

    private static class CVAdapter extends CursorAdapter {

        public CVAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView questionText = view.findViewById(android.R.id.text1);
            questionText.setText(cursor.getString(cursor.getColumnIndexOrThrow(CV.CVEntry.COLUMN_NAME_QUESTION)));
        }
    }

    private void bindView() {
        // 상단 아이콘
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon2);
        listView = findViewById(R.id.cv_list);
//        btn_tn = (Button) findViewById(R.id.button_TN);
        fab = findViewById(R.id.floatingActionButton);
        news = findViewById(R.id.newspaper);
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
                Intent intent1 = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent1);
                return true;
            case R.id.signUp:
                Intent intent2 = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent2);
                return true;
            case R.id.myPage:
                Intent intent3 = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_INSERT && resultCode == RESULT_OK) {
            cvAdapter.swapCursor(getCVCursor());
        }
    }
}
