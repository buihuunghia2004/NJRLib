package com.example.njrlib.fragments.admin;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.njrlib.R;
import com.example.njrlib.adapters.admin.KOBListAdapter;
import com.example.njrlib.adapters.admin.LibrarianManaAdapter;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.DialogAddKindofbookBinding;
import com.example.njrlib.databinding.DialogLibrarianManaBinding;
import com.example.njrlib.databinding.FragmentManageLibrarianBinding;
import com.example.njrlib.databinding.FragmentManageMemberBinding;
import com.example.njrlib.interfaces.ClickItemRcv;
import com.example.njrlib.interfaces.ItemChooseListener;
import com.example.njrlib.model.KindOfBook;
import com.example.njrlib.model.Librarian;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageLibrarianFragment extends Fragment {
    //FireBase
    DataFireBase dataFireBase;
    DataFireBase.LibrarianData librarianData;
    //UI
    ArrayList<Librarian> arrayList;
    LibrarianManaAdapter adapter;
    FragmentManageLibrarianBinding binding;
    Context context;
    //Material
    final int DELETE =0;
    final int UPDATE =1;
    int deteleOrUpdate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentManageLibrarianBinding.inflate(inflater);
        context=getContext();
        dataFireBase=DataFireBase.getInstance(context);
        librarianData=dataFireBase.new LibrarianData();

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //lấy dữ liệu về
                arrayList=librarianData.getList(snapshot);
                //set adapter cho rcv
                adapter=new LibrarianManaAdapter(context,arrayList);
                binding.rcv.setAdapter(adapter);
                binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));

                librarianData.myRefList.get();

                //thay đổi icon button (thêm hoặc xóa)
                adapter.sizeArrItemListener(new ItemChooseListener() {
                    @Override
                    public void itemChooseListener(int size) {
                        if (size==0){
                            binding.btnAdd.setImageResource(R.drawable.baseline_add_24);
                        }else{
                            binding.btnAdd.setImageResource(R.drawable.baseline_delete_24);
                        }
                    }
                });

                //click item kob
                adapter.setClickItemKOB(new ClickItemRcv() {
                    @Override
                    public void clickItemRcv(int position) {
                        deteleOrUpdate=UPDATE;
                        addDialogShow(getContext(),position);
                    }
                });

                //Button add click
                binding.btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deteleOrUpdate=DELETE;
                        ArrayList<Integer> arrPosition=adapter.getArrItemChoose();
                        //if no item is selected
                        if (arrPosition.size()==0){
                            addDialogShow(getContext(),0);
                        }else{
                            Toast.makeText(getContext(), "delete", Toast.LENGTH_SHORT).show();
                            for (int i:arrPosition){
                                librarianData.setRemove(arrayList.get(i));
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        librarianData.myRefList.addValueEventListener(valueEventListener);
        return binding.getRoot();
    }
    private void addDialogShow(Context context,int postition){
        DialogLibrarianManaBinding dialogBinding= DialogLibrarianManaBinding.inflate(getLayoutInflater());
        Dialog dialog=new Dialog(context);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbox);

        //setup UI
        if (deteleOrUpdate==UPDATE){
            dialogBinding.tvTitle.setText("Chỉnh sửa");
            Librarian librarian=arrayList.get(postition);
            dialogBinding.edtId.setText(librarian.getId());
            dialogBinding.edtName.setText(librarian.getName());
            dialogBinding.edtPassword.setText(librarian.getPassword());
        }else{
            dialogBinding.tvTitle.setText("Thêm thủ thư");
        }

        //button save
        dialogBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=dialogBinding.edtId.getText().toString();
                String password=dialogBinding.edtPassword.getText().toString();
                String name=dialogBinding.edtName.getText().toString();
                Librarian newLib=new Librarian(id,password,name);
                if (deteleOrUpdate==UPDATE){
                    //cập nhật item

                    librarianData.setUpdate(arrayList.get(postition),newLib);
                }else{
                    //thêm item
                    librarianData.setAdd(newLib);
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
}