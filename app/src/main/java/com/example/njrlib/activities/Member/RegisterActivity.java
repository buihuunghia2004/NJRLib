package com.example.njrlib.activities.Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.njrlib.databinding.ActivityRegisterBinding;
import com.example.njrlib.model.Member;
import com.example.njrlib.tool.ActivitySwitcher;
import com.example.njrlib.tool.Tool;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //
    ActivityRegisterBinding binding;
    //tool
    ActivitySwitcher activitySwitcher;
    Tool tool;
    //
    String name,dob,phoneNumber;
    byte[] byteImage;
    Member mem=new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //firebase
        mAuth=FirebaseAuth.getInstance();

        //tool
        activitySwitcher=ActivitySwitcher.getInstance(this);
        tool=Tool.getInstance(this);

        //receiver data from intent
        Intent intent=getIntent();
        Bundle bundle;
        if (intent!=null){
            bundle=intent.getExtras();
            if (bundle!=null){
                name=bundle.getString("name");
                dob=bundle.getString("dob");
                phoneNumber=bundle.getString("phoneNumbers");
                byteImage=bundle.getByteArray("arrByte");

            }
        }

        //register button click
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.edtEmail.getText().toString();
                String password=binding.edtPassword.getText().toString();
                String confirmPassword=binding.edtConfirmPassword.getText().toString();
                if (checkInfo(email,password,confirmPassword)){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //up info
                                Intent resultItent =new Intent();
                                resultItent.putExtra("boolean",true);
                                setResult(Activity.RESULT_OK,resultItent);
                                finish();
                                //upInfoToFireStorage(mem);
                                Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Register fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        //textview have an account click -> goto the login activity
        binding.tvHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitySwitcher.switchToActivity(MemberLoginActivity.class);
            }
        });
    }
    private boolean checkInfo(String email,String password,String confirmPassword){
        boolean check=false;

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)){
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (password.equals(confirmPassword)){
                    return true;
                }else{
                    Toast.makeText(this, "Password incorrect", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Email invalidate", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Input info plesase !", Toast.LENGTH_SHORT).show();
        }
        return check;
    }
    private String upInfoToFireStorage(Member mem){
        final String[] imageUrl = new String[1];
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Tạo tên tập tin duy nhất cho hình ảnh
        String fileName = currentUser.getUid() + ".png";
        // Tạo tham chiếu đến vị trí lưu trữ trên Firebase Storage
        StorageReference imageRef = storageRef.child("images/avatar/" + fileName);
        // Tạo công việc tải lên và lắng nghe sự kiện hoàn thành
        UploadTask uploadTask = imageRef.putBytes(byteImage);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    // Lấy URL của hình ảnh đã tải lên
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            //thực hiện đưa thông tin lên firestore
                            mem.setId(mAuth.getUid());
                            mem.setName(name);
                            mem.setDob(tool.switchStringToDate(dob));
                            mem.setMoney(0);
                            mem.setPhoneNumber(phoneNumber);
                            // Create a new user with a first and last name
                            Map<String, Object> member = new HashMap<>();
                            member.put("id", mem.getId());
                            member.put("name", mem.getName());
                            member.put("dob",tool.switchDateToString(mem.getDob()));
                            member.put("phoneNumber", mem.getPhoneNumber());
                            member.put("money", mem.getMoney());
                            member.put("linkAvatar",uri.toString());

                            // Add a new document with a generated ID
                            db.collection("members")
                                    .add(member)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d("db", "DocumentSnapshot added with ID: " + documentReference.getId());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("db", "Error adding document", e);
                                        }
                                    });
                            Intent intent=new Intent(RegisterActivity.this,MemberLoginActivity.class);
                            intent.putExtra("email",binding.edtEmail.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    // Xử lý lỗi tải lên hình ảnh
                    Log.d("upavatar", "fail");
                }
            }
        });
        return imageUrl[0];
    }
}