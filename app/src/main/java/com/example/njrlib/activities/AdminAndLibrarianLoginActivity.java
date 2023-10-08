package com.example.njrlib.activities;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
=======
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
>>>>>>> d2b3ea0 (Initial commit)
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.njrlib.MainActivity;
import com.example.njrlib.R;
<<<<<<< HEAD
import com.example.njrlib.databinding.ActivityAdminAndLibrarianLoginBinding;
import com.example.njrlib.tool.ActivitySwitcher;
=======
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.ActivityAdminAndLibrarianLoginBinding;
import com.example.njrlib.model.Librarian;
import com.example.njrlib.tool.ActivitySwitcher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
>>>>>>> d2b3ea0 (Initial commit)

public class AdminAndLibrarianLoginActivity extends AppCompatActivity {
    ActivityAdminAndLibrarianLoginBinding binding;
    ActivitySwitcher activitySwitcher;
<<<<<<< HEAD
=======
    String type;
>>>>>>> d2b3ea0 (Initial commit)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminAndLibrarianLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //tool
        activitySwitcher=ActivitySwitcher.getInstance(this);

<<<<<<< HEAD
=======
        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        Toast.makeText(AdminAndLibrarianLoginActivity.this, type, Toast.LENGTH_SHORT).show();
>>>>>>> d2b3ea0 (Initial commit)
        //login button click
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=binding.edtId.getText().toString();
                String password=binding.edtPassword.getText().toString();
<<<<<<< HEAD
                if (checkInfo(id,password)){
                    if (id.equals("admin") && password.equals("123456")){
                        activitySwitcher.switchToActivity(MainActivity.class);
                    }else if (id.equals("librarian") && password.equals("123456")){
                        activitySwitcher.switchToActivity(MainActivity.class);
                    }else{
                        Toast.makeText(AdminAndLibrarianLoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                    }
=======
                if (type.equals("admin")){
                    if (checkInfo(id,password)){
                        if (id.equals("admin") && password.equals("123456")){
                            SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("type","admin");
                            editor.apply();
                            activitySwitcher.switchToActivity(MainActivity.class);
                        }else{
                            Toast.makeText(AdminAndLibrarianLoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    librarianLogin(id,password);
>>>>>>> d2b3ea0 (Initial commit)
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
<<<<<<< HEAD
=======
    private void librarianLogin(String id,String pass){
        DataFireBase dataFireBase=DataFireBase.getInstance(this);
        DataFireBase.LibrarianData librarianData=dataFireBase.new LibrarianData();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean b=false;
                for (Librarian librarian:librarianData.getList(snapshot)){
                    if (librarian.getId().equals(id) && librarian.getPassword().equals(pass)){
                        b=true;
                        break;
                    }
                }
                if (b){
                    SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("type","lib");

                    Intent intent=new Intent(AdminAndLibrarianLoginActivity.this, MainActivity.class);
                    String libId= binding.edtId.getText().toString();
                    intent.putExtra("lib",libId);
                    editor.putString("libId",libId);
                    Log.d("LLLL", "setConditionListener: "+libId);
                    editor.apply();
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(AdminAndLibrarianLoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                }
                librarianData.myRefList.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        librarianData.myRefList.addValueEventListener(valueEventListener);
    }
>>>>>>> d2b3ea0 (Initial commit)
}