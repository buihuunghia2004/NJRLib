package com.example.njrlib.activities;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.os.Bundle;
import android.os.Handler;

=======
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.njrlib.MainActivity;
>>>>>>> d2b3ea0 (Initial commit)
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
<<<<<<< HEAD
=======
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

>>>>>>> d2b3ea0 (Initial commit)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user!=null){
                    activitySwitcher.switchToActivity(MemberActivity.class);
                }else{
<<<<<<< HEAD
                    activitySwitcher.switchToActivity(SelectLoginObjectActivity.class);
                }
            }
        },3000);
=======
                    String type=sharedPreferences.getString("type","null");
                    if (type.equals("admin")){

                    }else if (type.equals("lib")){
                        Intent intent=new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        activitySwitcher.switchToActivity(SelectLoginObjectActivity.class);
                    }
                }
            }
        },1000);
>>>>>>> d2b3ea0 (Initial commit)
        finish();
    }
}