package com.example.njrlib.fragments.admin;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.njrlib.R;
import com.example.njrlib.adapters.admin.BookManaAdapter;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.adapters.admin.KOBListAdapter;
import com.example.njrlib.databinding.DialogAddBookAdminBinding;
import com.example.njrlib.databinding.FragmentManageBookBinding;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.KindOfBook;
import com.example.njrlib.tool.Tool;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ManageBookFragment extends Fragment {
    //firebase
    DataFireBase dataFireBase;
    DataFireBase.BooksData booksData;
    DataFireBase.KOBData KOBData;
    private ValueEventListener valueEventListenerKOB;

    //tool
    Tool tool;
    //UI
    ArrayList<Book> listBook;
    BookManaAdapter adapter;
    //material
    private ActivityResultLauncher<Intent> launcher;
    byte[] arrByteImg;
    final int DELETE =0;
    final int UPDATE =1;
    int deteleOrUpdate;
    ArrayList<KindOfBook> listKOB;
    FragmentManageBookBinding binding;
    DialogAddBookAdminBinding dialogBinding;
    //DialogAddBookAdminBinding dialogBinding= DialogAddBookAdminBinding.inflate(getLayoutInflater());
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listKOB=new ArrayList<>();
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri imgUri=data.getData();
                            //dialogBinding.imgCover.setImageURI(imgUri);
                            setImageAvatar(imgUri);
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentManageBookBinding.inflate(inflater);
        tool=Tool.getInstance(getContext());
        dataFireBase=DataFireBase.getInstance(getContext());
        booksData=dataFireBase.new BooksData();
        KOBData=dataFireBase.new KOBData();

        //recycler view
        listBook=new ArrayList<>();
        booksData.myRefList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listBook.clear();
                listBook=booksData.getList(snapshot);
                adapter=new BookManaAdapter(getContext(),listBook);
                binding.rcvBooks.setAdapter(adapter);
                binding.rcvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //button add click
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogShow(getContext(),1);
            }
        });


        return binding.getRoot();
    }
    private void addDialogShow(Context context,int postition){
        dialogBinding= DialogAddBookAdminBinding.inflate(getLayoutInflater());
        Dialog dialog=new Dialog(context);
        //Book newBook=listBook.get(postition);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbox);

        //setup UI
        if (deteleOrUpdate==UPDATE){
            dialogBinding.tvTitle.setText("Chỉnh sửa sách");

            //set info...handle....
        }else{
            dialogBinding.tvTitle.setText("Thêm sách mới");
        }

        //chọn hình ảnh
        dialogBinding.imgCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        //chọn loại sách
        valueEventListenerKOB = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Xử lý dữ liệu
                if (snapshot!=null){
                    listKOB=KOBData.getList(snapshot);
                    if (listKOB!=null){
                        String[] items=new String[listKOB.size()];
                        for (int i=0;i<listKOB.size();i++){
                            items[i]=listKOB.get(i).getId();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,items);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dialogBinding.spinnerKOB.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        };
        KOBData.myRefList.addValueEventListener(valueEventListenerKOB);

        //button save
        dialogBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deteleOrUpdate==UPDATE){
                    //cập nhật item
                    //booksData.setUpdate(newBook,new Book(dialogBinding.edtIDKOB.getText().toString(),dialogBinding.edtNameKOB.getText().toString()));
                }else{
                    String id=dialogBinding.edtBookId.getText().toString();

                    //up load ảnh sách
                    UploadTask uploadTask=dataFireBase.upLoadImg("images/covers/"+id+".png",tool.switchImageToByteArrays(dialogBinding.imgCover.getDrawable()));
                            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()){
                                    dataFireBase.imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String name=dialogBinding.edtBookName.getText().toString();
                                            String idKOB=dialogBinding.spinnerKOB.getSelectedItem().toString();
                                            int rentCost= Integer.parseInt(dialogBinding.edtRentCost.getText().toString());
                                            String linkImg=uri.toString();
                                            Book book=new Book(id,name,rentCost,idKOB,linkImg);
                                            //thêm sách lên firebase
                                            booksData.setAdd(book);
                                        }
                                    });
                                }else{

                                }
                            }
                        });
                }
                dialog.dismiss();
            }
        });
        //cancel
        dialogBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        launcher.launch(intent);
    }
    private void setImageAvatar(Uri uri){
        dialogBinding.imgCover.setImageURI(uri);
    }
<<<<<<< HEAD

    @Override
    public void onStop() {
        super.onStop();
        KOBData.myRefList.removeEventListener(valueEventListenerKOB);
    }
=======
>>>>>>> d2b3ea0 (Initial commit)
}

