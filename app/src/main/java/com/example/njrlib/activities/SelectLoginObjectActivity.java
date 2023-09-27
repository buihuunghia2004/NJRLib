package com.example.njrlib.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.njrlib.activities.Member.MemberLoginActivity;
import com.example.njrlib.databinding.ActivitySelectLoginObjectBinding;
import com.example.njrlib.tool.ActivitySwitcher;

public class SelectLoginObjectActivity extends AppCompatActivity {
    ActivitySelectLoginObjectBinding binding;
    ActivitySwitcher switcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySelectLoginObjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //tool
        switcher=ActivitySwitcher.getInstance(this);
        //admin button click
        binding.btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.switchToActivity(AdminAndLibrarianLoginActivity.class);
            }
        });
        //librarian button click
        binding.btnLibrarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.switchToActivity(AdminAndLibrarianLoginActivity.class);
            }
        });
        //member button click
        binding.btnMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.switchToActivity(MemberLoginActivity.class);
            }
        });
    }
}