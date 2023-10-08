package com.example.njrlib.fragments.admin.LoanShip;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.adapters.admin.Order.LoanSlipManaAdapter;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentConfirmOrderManaBinding;
import com.example.njrlib.interfaces.ConditionListener;
import com.example.njrlib.model.LoanSlip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConfirmLoanSlipManaFragment extends Fragment {
    FragmentConfirmOrderManaBinding binding;
    DataFireBase dataFireBase;
    DataFireBase.LoanSlipData loanSlipData;
    Context context;
    ValueEventListener valueEventListener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        //firebase
        dataFireBase=DataFireBase.getInstance(context);
        loanSlipData=dataFireBase.new LoanSlipData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentConfirmOrderManaBinding.inflate(inflater);
        // Inflate the layout for this fragment
        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<LoanSlip> list=loanSlipData.getList(snapshot);
                ArrayList<LoanSlip> listLoanSlip=new ArrayList<>();
                for (LoanSlip loanSlip:list){
                    if (loanSlip.getCondition()==0){
                        listLoanSlip.add(loanSlip);
                    }
                }
                //set Recyclerview
                LoanSlipManaAdapter adapter=new LoanSlipManaAdapter(listLoanSlip);
                binding.rcv.setLayoutManager(new LinearLayoutManager(context));
                binding.rcv.setAdapter(adapter);

                adapter.setConditionListener(new ConditionListener() {
                    @Override
                    public void setConditionListener(int condition, int position) {
                        loanSlipData.myRefList.child(listLoanSlip.get(position).getId()).child("condition").setValue(condition);
                        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
                        String libId=sharedPreferences.getString("libId","null");
                        Log.d("LLLL", "setConditionListener: "+libId);
                        loanSlipData.myRefList.child(listLoanSlip.get(position).getId()).child("libId").setValue(libId);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        loanSlipData.myRefList.addValueEventListener(valueEventListener);
        return binding.getRoot();
    }
}