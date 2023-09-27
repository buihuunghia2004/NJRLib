package com.example.njrlib.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.R;
import com.example.njrlib.adapters.MemberManageAdapter;
import com.example.njrlib.databinding.FragmentMemberManageBinding;
import com.example.njrlib.model.Member;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MemberManageFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FragmentMemberManageBinding binding;
    //material
    ArrayList<Member> list;
    MemberManageAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentMemberManageBinding.inflate(inflater);
        list=new ArrayList<>();
        list=getListMember();

        adapter=new MemberManageAdapter(list,getContext());
        binding.rcvMember.setAdapter(adapter);
        binding.rcvMember.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    private ArrayList<Member> getListMember(){
        ArrayList<Member> listMem=new ArrayList<>();
        db.collection("Members")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            //Member member = documentSnapshot.toObject(Member.class);
                            Log.d("UUUU",documentSnapshot.getId());
                            //listMem.add(member);
                        }
                        Log.d("UUUL", "onSuccess: "+listMem.size());
                        // sử dụng danh sách userList ở đây
                    }
                });
        return listMem;
    }
}