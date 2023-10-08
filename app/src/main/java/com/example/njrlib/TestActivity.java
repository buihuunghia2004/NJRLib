package com.example.njrlib;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TestActivity extends AppCompatActivity {
=======
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.njrlib.activities.ConfigInfoMemberActivity;
import com.example.njrlib.databinding.ActivityTestBinding;
import com.example.njrlib.model.KindOfBook;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    ActivityTestBinding binding;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;
>>>>>>> d2b3ea0 (Initial commit)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_test);

=======
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(TestActivity.this);
        progressDialog.setTitle("Đụ má....");
        progressDialog.setMessage("Haizzzz....");

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken()
                .requestEmail()
                .build();

        mGoogleSignInClient=GoogleSignIn.getClient(this,gso);

        binding.btnLoginWithGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    int RC_SIGN=40;
    private void signIn(){
        Intent intent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()){

            }else{
                Log.d("LLLL", "onActivityResult: chán1");

            }
            try {
                Log.d("LLLL", "onActivityResult: chán2");
                GoogleSignInAccount account=task.getResult(ApiException.class);
                //firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void firebaseAuth(String idToken) {
        AuthCredential credential=GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user=mAuth.getCurrentUser();
                        }
                    }
                });
>>>>>>> d2b3ea0 (Initial commit)
    }
}