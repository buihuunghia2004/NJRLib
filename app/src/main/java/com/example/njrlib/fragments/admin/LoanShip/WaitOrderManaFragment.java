package com.example.njrlib.fragments.admin.LoanShip;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.adapters.admin.Order.WaitOrderManaAdapter;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentWaitOrderManaBinding;
import com.example.njrlib.interfaces.ConditionListener;
import com.example.njrlib.model.LoanSlip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WaitOrderManaFragment extends Fragment {
    DataFireBase dataFireBase;
    DataFireBase.LoanSlipData loanSlipData;
    ArrayList<LoanSlip> list;
    WaitOrderManaAdapter adapter;
    FragmentWaitOrderManaBinding binding;
    ValueEventListener valueEventListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataFireBase=DataFireBase.getInstance(getContext());
        loanSlipData=dataFireBase.new LoanSlipData();
        list=new ArrayList<>();
        getList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentWaitOrderManaBinding.inflate(inflater);
        // Inflate the layout for this fragment
        initUI();
        adapter.setConditionListener(new ConditionListener() {
            @Override
            public void setConditionListener(int condition, int position) {
                loanSlipData.myRefList.child(list.get(position).getId()).child("condition").setValue(condition);
            }
        });
        return binding.getRoot();
    }
    private void initUI(){
        adapter=new WaitOrderManaAdapter(list);
        binding.rcv.setAdapter(adapter);
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void getList(){
        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot childSnapshot:snapshot.getChildren()){
                    LoanSlip loanSlip=childSnapshot.getValue(LoanSlip.class);
                    if (loanSlip.getCondition()==1){
                        list.add(loanSlip);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        loanSlipData.myRefList.addValueEventListener(valueEventListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (valueEventListener!=null){
            loanSlipData.myRefList.addValueEventListener(valueEventListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        loanSlipData.myRefList.removeEventListener(valueEventListener);
    }
}