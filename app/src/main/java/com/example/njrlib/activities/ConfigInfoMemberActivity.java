package com.example.njrlib.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.njrlib.activities.Member.MemberActivity;
import com.example.njrlib.activities.Member.RegisterActivity;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.ActivityConfigInfoMemberBinding;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.Member;
import com.example.njrlib.tool.ActivitySwitcher;
import com.example.njrlib.tool.Tool;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class ConfigInfoMemberActivity extends AppCompatActivity {
    //fire base
    FirebaseAuth mAuth;
    DataFireBase dataFireBase;
    ActivityConfigInfoMemberBinding binding;
    private ActivityResultLauncher<Intent> imagePickerLauncher,upLoadInfoLaucher;

    //Tool
    ActivitySwitcher activitySwitcher;
    Tool tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityConfigInfoMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activitySwitcher=ActivitySwitcher.getInstance(this);
        tool=Tool.getInstance(this);

        mAuth=FirebaseAuth.getInstance();
        dataFireBase=DataFireBase.getInstance(this);
        //
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri imgUri=data.getData();
                            binding.imgAvatar.setImageURI(imgUri);
                        }
                    }
                }
        );
        upLoadInfoLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data=result.getData();
                            Boolean b=data.getBooleanExtra("boolean",true);
                            if (b){
                                String id=mAuth.getUid();

                                //up avatar lên storage
                                UploadTask uploadTask=dataFireBase.upLoadImg("images/avatar/"+id+".png",tool.switchImageToByteArrays(binding.imgAvatar.getDrawable()));
                                uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                        if (task.isSuccessful()){
                                            dataFireBase.imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String linkAvatar=uri.toString();
                                                    String name=binding.edtName.getText().toString();
                                                    String dob=binding.edtDOB.getText().toString();
                                                    String phoneNumbers=binding.edtPhoneNumbers.getText().toString();
                                                    //up info lên realtime
                                                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(dob) && Patterns.PHONE.matcher(phoneNumbers).matches()){
                                                        Member member=new Member(id,name,tool.switchStringToDate(dob),phoneNumbers,0,linkAvatar);
                                                        DataFireBase.MembersData membersData=dataFireBase.new MembersData();
                                                        membersData.setAdd(member);
                                                        //
                                                        activitySwitcher.switchToActivity(MemberActivity.class);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    }
                });


        //imageview avatar click
        binding.imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfigInfoMemberActivity.this, "ok", Toast.LENGTH_SHORT).show();
                openImagePicker();
            }
        });
        //edt DOB click
        binding.edtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDatePicker();
            }
        });
        //next button click
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConfigInfoMemberActivity.this, RegisterActivity.class);
                upLoadInfoLaucher.launch(intent);
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }
    private void showDialogDatePicker(){
        // Khởi tạo DatePickerDialog với ngày tháng mặc định
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Material_Dialog_NoActionBar,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Xử lý khi ngày được chọn
                        String date=dayOfMonth+"/"+monthOfYear+"/"+year;
                        binding.edtDOB.setText(date);
                    }
                }, mYear, mMonth, mDay);
        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }

}