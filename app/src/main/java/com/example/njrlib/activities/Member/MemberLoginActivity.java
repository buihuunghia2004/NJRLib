package com.example.njrlib.activities.Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.njrlib.activities.ConfigInfoMemberActivity;
import com.example.njrlib.databinding.ActivityMemberLoginBinding;
import com.example.njrlib.tool.ActivitySwitcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MemberLoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    ActivityMemberLoginBinding binding;
    ActivitySwitcher activitySwitcher;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMemberLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //fire base
        mAuth=FirebaseAuth.getInstance();
        uid=mAuth.getUid();
        //tool
        activitySwitcher=ActivitySwitcher.getInstance(this);
        //Login button click
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emai=binding.edtEmail.getText().toString();
                String password=binding.edtPassword.getText().toString();

                if (checkInfo(emai,password)){
                    mAuth.signInWithEmailAndPassword(emai,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MemberLoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                activitySwitcher.switchToActivity(MemberActivity.class);
                            }else{
                                Toast.makeText(MemberLoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        //Login with google button click
        //Forgot password textview click
        //Dont have an account textview click
        binding.tvDontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitySwitcher.switchToActivity(ConfigInfoMemberActivity.class);
            }
        });
    }
    private boolean checkInfo(String email,String password){
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length()>=6){
                return true;
            }else{
                Toast.makeText(this, "Email or password is invalid", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Input info please !", Toast.LENGTH_SHORT).show();

        }
        return false;
    }
}