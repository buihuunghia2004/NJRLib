package com.example.njrlib.fragments.admin;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.R;
import com.example.njrlib.adapters.admin.MemberManaAdapter;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentManageMemberBinding;
import com.example.njrlib.model.Member;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageMemberFragment extends Fragment {
    DataFireBase dataFireBase;
    DataFireBase.MembersData membersData;
    ValueEventListener valueEventListener;
    Context context;
    FragmentManageMemberBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding=FragmentManageMemberBinding.inflate(inflater);
        // Inflate the layout for this fragment
        context=getContext();
        dataFireBase=DataFireBase.getInstance(context);
        membersData=dataFireBase.new MembersData();

        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Member> list=membersData.getList(snapshot);
                MemberManaAdapter adapter=new MemberManaAdapter(context,list);
                binding.rcvManageMember.setAdapter(adapter);
                binding.rcvManageMember.setLayoutManager(new LinearLayoutManager(context));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        return binding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        membersData.myRefList.addValueEventListener(valueEventListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        membersData.myRefList.removeEventListener(valueEventListener);
    }
}