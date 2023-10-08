package com.example.njrlib.activities;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> d2b3ea0 (Initial commit)
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
<<<<<<< HEAD
                switcher.switchToActivity(AdminAndLibrarianLoginActivity.class);
=======
                Intent intent=new Intent(SelectLoginObjectActivity.this, AdminAndLibrarianLoginActivity.class);
                intent.putExtra("type","admin");
                startActivity(intent);
>>>>>>> d2b3ea0 (Initial commit)
            }
        });
        //librarian button click
        binding.btnLibrarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                switcher.switchToActivity(AdminAndLibrarianLoginActivity.class);
=======
                Intent intent=new Intent(SelectLoginObjectActivity.this, AdminAndLibrarianLoginActivity.class);
                intent.putExtra("type","librarian");
                startActivity(intent);
>>>>>>> d2b3ea0 (Initial commit)
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