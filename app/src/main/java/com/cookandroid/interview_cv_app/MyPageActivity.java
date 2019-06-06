package com.cookandroid.interview_cv_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPageActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference userDB;
    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    TextView email, nickname, name, collagenum, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        userDB = mDatabase.child("users").child(userId(currentUser.getEmail())).child("userInfo");

        email = findViewById(R.id.TextView_email);
        nickname = findViewById(R.id.TextView_nickname);
        name = findViewById(R.id.TextView_name);
        collagenum = findViewById(R.id.TextView_collagenum);
        tel = findViewById(R.id.TextView_tel);

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);

                email.setText(currentUser.email);
                nickname.setText(currentUser.nickname);
                name.setText(currentUser.username);
                collagenum.setText(currentUser.collagenum);
                tel.setText(currentUser.tel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private String userId(String email){
        String ans ="";
        for(int i=0; i<email.length();i++) {
            char a =email.charAt(i);
            if(a!='.'&&a!='@') {
                ans +=a;
            }
        }
        return ans;
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
            case R.id.myPage:
                Intent intent3 = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent3);
                return true;
//            case R.id.CoverLetter:
//                Intent intent4 = new Intent(getApplicationContext(), CoverLetterActivity.class);
//                startActivity(intent4);
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
