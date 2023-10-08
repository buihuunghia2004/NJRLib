package com.example.njrlib.fragments.Member;

import android.content.Context;
<<<<<<< HEAD
import android.os.Bundle;

=======
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
>>>>>>> d2b3ea0 (Initial commit)
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.njrlib.R;
<<<<<<< HEAD
=======
import com.example.njrlib.activities.Member.MyOrderActivity;
>>>>>>> d2b3ea0 (Initial commit)
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentIndividualBinding;
import com.example.njrlib.model.Member;
import com.google.firebase.auth.FirebaseAuth;
<<<<<<< HEAD

public class IndividualFragment extends Fragment {
    FragmentIndividualBinding binding;
    DataFireBase dataFireBase;
    DataFireBase.MembersData membersData;
    Context context;
    CurrentMember currentMember;
    Member member;
=======
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class IndividualFragment extends Fragment {
    FirebaseAuth mAuth;
    FragmentIndividualBinding binding;
    DataFireBase dataFireBase;
    DataFireBase.MembersData membersData;
    DataFireBase.LoanSlipData loanSlipData;
    Context context;
    ValueEventListener valueEventListener;
>>>>>>> d2b3ea0 (Initial commit)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentIndividualBinding.inflate(inflater);
        context=getContext();
<<<<<<< HEAD
        currentMember=CurrentMember.getInstance(context);
        member=currentMember.getCurrentMember();
        //
        dataFireBase=DataFireBase.getInstance(getContext());
        membersData=dataFireBase.new MembersData();
        //set UI
        Glide.with(context).load(member.getLinkAvatar()).into(binding.imgAvatar);
        binding.tvName.setText(member.getName());
        binding.tvTotalMoney.setText(String.valueOf(member.getMoney()));
=======
        //firebase
        dataFireBase=DataFireBase.getInstance(getContext());
        membersData=dataFireBase.new MembersData();
        loanSlipData=dataFireBase.new LoanSlipData();
        mAuth=FirebaseAuth.getInstance();

        //sss
        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member member=membersData.getMember(snapshot);
                //set UI
                Glide.with(context).load(member.getLinkAvatar()).into(binding.imgAvatar);
                binding.tvName.setText(member.getName());
                binding.tvTotalMoney.setText(String.valueOf(member.getMoney()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        membersData.myRefList.child(mAuth.getUid()).addValueEventListener(valueEventListener);
>>>>>>> d2b3ea0 (Initial commit)

        //action
        //1.Button recharge click
        binding.btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                int money= Integer.parseInt(binding.edtMoney.getText().toString());
                //set money cho member
                member.setMoney(money+member.getMoney());
                //set lại money cho member toàn app
                currentMember.setCurrentMember(member);
                //set money cho firebase
                membersData.setUpdate(member.getId(),member);
                //set money cho các view
                binding.tvTotalMoney.setText(String.valueOf(member.getMoney()));
=======
                //tiền hiện tại
                int moneyCurrent=Integer.parseInt(binding.tvTotalMoney.getText().toString());
                //tiền muốn nạp
                int money= Integer.parseInt(binding.edtMoney.getText().toString());
                //thực hiện thêm tiền lên database
                membersData.myRefList.child(mAuth.getUid()).child("money").setValue(money+moneyCurrent);
                //fixUI
                binding.tvTotalMoney.setText(String.valueOf(moneyCurrent+money));
>>>>>>> d2b3ea0 (Initial commit)
                binding.edtMoney.setText("");
            }
        });

<<<<<<< HEAD
        return binding.getRoot();
    }
=======
        binding.iconConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOrder(0);
            }
        });
        binding.iconWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOrder(1);
            }
        });
        binding.iconReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOrder(2);
            }
        });
        binding.iconReturned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOrder(3);
            }
        });
        return binding.getRoot();
    }

    private void myOrder(int i){
        Intent intent=new Intent(context, MyOrderActivity.class);
        intent.putExtra("type",i);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        membersData.myRefList.child(mAuth.getUid()).removeEventListener(valueEventListener);
        Log.d("FFFF", "onStop: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (valueEventListener!=null){
            membersData.myRefList.child(mAuth.getUid()).addValueEventListener(valueEventListener);
        }
        Log.d("FFFF", "onResume: ");
    }
>>>>>>> d2b3ea0 (Initial commit)
}