package com.example.njrlib.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.example.njrlib.R;
import com.example.njrlib.activities.Member.MemberActivity;
import com.example.njrlib.tool.ActivitySwitcher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    ActivitySwitcher activitySwitcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //firebase
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        //tool
        activitySwitcher=ActivitySwitcher.getInstance(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user!=null){
                    activitySwitcher.switchToActivity(MemberActivity.class);
                }else{
                    activitySwitcher.switchToActivity(SelectLoginObjectActivity.class);
                }
            }
        },3000);
        finish();
    }
}