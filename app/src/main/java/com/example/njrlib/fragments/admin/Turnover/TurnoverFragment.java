package com.example.njrlib.fragments.admin.Turnover;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.R;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentTurnoverBinding;
import com.example.njrlib.model.LoanSlip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TurnoverFragment extends Fragment {
    DataFireBase.LoanSlipData loanSlipData;
    Context context;
    FragmentTurnoverBinding binding;
    ArrayList<LoanSlip> list;
    int money;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        loanSlipData=DataFireBase.getInstance(context).new LoanSlipData();
        list=new ArrayList<>();

        setList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentTurnoverBinding.inflate(inflater);
        // Inflate the layout for this fragment

        return binding.getRoot();
    }
    private void setList(){
        loanSlipData.myRefList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot childSnapshot:snapshot.getChildren()){
                    LoanSlip loanSlip=childSnapshot.getValue(LoanSlip.class);
                    if (loanSlip.getCondition()==3){
                        list.add(loanSlip);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}