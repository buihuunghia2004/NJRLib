package com.example.njrlib.fragments.Member;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.njrlib.R;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentIndividualBinding;
import com.example.njrlib.model.Member;
import com.google.firebase.auth.FirebaseAuth;

public class IndividualFragment extends Fragment {
    FragmentIndividualBinding binding;
    DataFireBase dataFireBase;
    DataFireBase.MembersData membersData;
    Context context;
    CurrentMember currentMember;
    Member member;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentIndividualBinding.inflate(inflater);
        context=getContext();
        currentMember=CurrentMember.getInstance(context);
        member=currentMember.getCurrentMember();
        //
        dataFireBase=DataFireBase.getInstance(getContext());
        membersData=dataFireBase.new MembersData();
        //set UI
        Glide.with(context).load(member.getLinkAvatar()).into(binding.imgAvatar);
        binding.tvName.setText(member.getName());
        binding.tvTotalMoney.setText(String.valueOf(member.getMoney()));

        //action
        //1.Button recharge click
        binding.btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money= Integer.parseInt(binding.edtMoney.getText().toString());
                //set money cho member
                member.setMoney(money+member.getMoney());
                //set lại money cho member toàn app
                currentMember.setCurrentMember(member);
                //set money cho firebase
                membersData.setUpdate(member.getId(),member);
                //set money cho các view
                binding.tvTotalMoney.setText(String.valueOf(member.getMoney()));
                binding.edtMoney.setText("");
            }
        });

        return binding.getRoot();
    }
}