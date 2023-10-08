package com.example.njrlib.activities.Member;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
=======
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
>>>>>>> d2b3ea0 (Initial commit)
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.njrlib.activities.ConfigInfoMemberActivity;
import com.example.njrlib.databinding.ActivityMemberLoginBinding;
import com.example.njrlib.tool.ActivitySwitcher;
<<<<<<< HEAD
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MemberLoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    ActivityMemberLoginBinding binding;
    ActivitySwitcher activitySwitcher;
=======
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class MemberLoginActivity extends AppCompatActivity {
    GoogleSignInClient googleSignInClient;
    FirebaseAuth mAuth;
    ActivityMemberLoginBinding binding;
    ActivitySwitcher activitySwitcher;
    private ActivityResultLauncher<Intent> loginWithGGLauncher;
>>>>>>> d2b3ea0 (Initial commit)
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMemberLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //fire base
        mAuth=FirebaseAuth.getInstance();
        uid=mAuth.getUid();
<<<<<<< HEAD
=======
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken("438431947620-ecpi41uk3dhhf4mv8g8q993k3vs49ltm.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(MemberLoginActivity.this,gso);

        loginWithGGLauncher= registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            // When request code is equal to 100 initialize task
                            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
                            // check condition
                            if (signInAccountTask.isSuccessful()) {
                                // When google sign in successful initialize string
                                String s = "Google sign in successful";
                                // Display Toast
                                Log.d("LLLL", "successful: ");
                                Toast.makeText(MemberLoginActivity.this, s, Toast.LENGTH_SHORT).show();
                                // Initialize sign in account
                                try {
                                    Log.d("LLLL", "try");
                                    // Initialize sign in account
                                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                                    // Check condition
                                    if (googleSignInAccount != null) {
                                        Log.d("LLLL", "gsia != null");
                                        // When sign in account is not equal to null initialize auth credential
                                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                                        // Check credential
                                        mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d("LLLL", "task ok");
                                                    // When task is successful redirect to profile activity display Toast
                                                    startActivity(new Intent(MemberLoginActivity.this, ConfigInfoMemberActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                                    Toast.makeText(MemberLoginActivity.this, "Firebase authentication successful", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    // When task is unsuccessful display Toast
                                                    Toast.makeText(MemberLoginActivity.this, "Authentication Failed :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }else{
                                        Log.d("LLLL", "gsia == null");
                                    }
                                } catch (ApiException e) {
                                    Log.d("LLLL", "ex");
                                    throw new RuntimeException(e);
                                }
                            }else{
                                Log.d("LLLL", "fail: ");
                            }
//                        }else{
//                            Log.d("LLLL", "onActivityResult: fff");
//                        }
                    }
                }
        );
        // Khởi tạo GoogleSignInClient
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
>>>>>>> d2b3ea0 (Initial commit)
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
<<<<<<< HEAD
=======

        binding.btnLoginWithGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo yêu cầu đăng nhập bằng tài khoản Google
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,999);
//                loginWithGGLauncher.launch(signInIntent);

//                GoogleSignInClient signInClient = GoogleSignIn.getClient(MemberLoginActivity.this,
//                        GoogleSignInOptions.DEFAULT_SIGN_IN);
//                Intent intent = signInClient.getSignInIntent();
                //loginWithGGLauncher.launch(intent);
            }
        });
>>>>>>> d2b3ea0 (Initial commit)
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
<<<<<<< HEAD
=======
    // Xử lý sự kiện đăng nhập
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 999) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()){

                try {
                    // Lấy thông tin người dùng đã đăng nhập thành công
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    String idToken = account.getIdToken();

                    // Sử dụng idToken để xác thực người dùng trên máy chủ của bạn
                    // ...

                } catch (ApiException e) {
                    // Xử lý lỗi đăng nhập
                    // ...
                }
            }else{
                Log.d("LLLL", "onActivityResult: pheo");
            }


        }
    }
>>>>>>> d2b3ea0 (Initial commit)
}