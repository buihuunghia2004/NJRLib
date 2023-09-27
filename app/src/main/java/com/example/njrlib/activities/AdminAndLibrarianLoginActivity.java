package com.example.njrlib.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.njrlib.MainActivity;
import com.example.njrlib.R;
import com.example.njrlib.databinding.ActivityAdminAndLibrarianLoginBinding;
import com.example.njrlib.tool.ActivitySwitcher;

public class AdminAndLibrarianLoginActivity extends AppCompatActivity {
    ActivityAdminAndLibrarianLoginBinding binding;
    ActivitySwitcher activitySwitcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminAndLibrarianLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //tool
        activitySwitcher=ActivitySwitcher.getInstance(this);

        //login button click
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=binding.edtId.getText().toString();
                String password=binding.edtPassword.getText().toString();
                if (checkInfo(id,password)){
                    if (id.equals("admin") && password.equals("123456")){
                        activitySwitcher.switchToActivity(MainActivity.class);
                    }else if (id.equals("librarian") && password.equals("123456")){
                        activitySwitcher.switchToActivity(MainActivity.class);
                    }else{
                        Toast.makeText(AdminAndLibrarianLoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean checkInfo(String id,String password){
        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(password)){
            if (password.length()>=6){
                return true;
            }else{
                Toast.makeText(this, "Id or password is invalid", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Input info please !", Toast.LENGTH_SHORT).show();

        }
        return false;
    }
}