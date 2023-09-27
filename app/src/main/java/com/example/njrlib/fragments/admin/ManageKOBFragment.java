package com.example.njrlib.fragments.admin;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.njrlib.R;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.adapters.admin.KOBListAdapter;
import com.example.njrlib.databinding.DialogAddKindofbookBinding;
import com.example.njrlib.databinding.FragmentManageKOBBinding;

import com.example.njrlib.interfaces.ClickItemRcv;
import com.example.njrlib.interfaces.ItemChooseListener;
import com.example.njrlib.model.KindOfBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ManageKOBFragment extends Fragment {
   //firebase
    DataFireBase dataFireBase;
    DataFireBase.KOBData kobData;
    //UI
    ArrayList<KindOfBook> arrayList;
    KOBListAdapter adapter;
    FragmentManageKOBBinding binding;
    //material
    final int DELETE =0;
    final int UPDATE =1;
    int deteleOrUpdate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentManageKOBBinding.inflate(inflater);

        dataFireBase=DataFireBase.getInstance(getContext());
        kobData=dataFireBase.new KOBData();

        arrayList=new ArrayList<>();

        kobData.myRefList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //lấy dữ liệu về
                arrayList=kobData.getList(dataSnapshot);
                //set adapter cho rcv
                adapter=new KOBListAdapter(arrayList);
                binding.rcvKindOfBook.setAdapter(adapter);
                binding.rcvKindOfBook.setLayoutManager(new LinearLayoutManager(getContext()));

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
                                kobData.setRemove(arrayList.get(i));
                            }
                        }
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return binding.getRoot();
    }
    private void addDialogShow(Context context,int postition){
        DialogAddKindofbookBinding dialogBinding= DialogAddKindofbookBinding.inflate(getLayoutInflater());
        Dialog dialog=new Dialog(context);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbox);

        //setup UI
        if (deteleOrUpdate==UPDATE){
            dialogBinding.tvTitle.setText("Chỉnh sửa loại sách");
            KindOfBook oldKOB=arrayList.get(postition);
            dialogBinding.edtIDKOB.setText(oldKOB.getId());
            dialogBinding.edtNameKOB.setText(oldKOB.getName());
        }else{
            dialogBinding.tvTitle.setText("Thêm loại sách");
        }

        //button save
        dialogBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deteleOrUpdate==UPDATE){
                    //cập nhật item
                    kobData.setUpdate(arrayList.get(postition),new KindOfBook(dialogBinding.edtIDKOB.getText().toString(),dialogBinding.edtNameKOB.getText().toString()));
                }else{
                    String id=dialogBinding.edtIDKOB.getText().toString();
                    String name=dialogBinding.edtNameKOB.getText().toString();

                    //thêm item
                    kobData.setAdd(new KindOfBook(id,name));
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