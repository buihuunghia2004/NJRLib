package com.example.njrlib.fragments.Member.MyOrder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.adapters.Member.FragmentMyOrder.ConfirmOderMemAdapter;
import com.example.njrlib.databinding.FragmentConfirmOrderMemBinding;
import com.example.njrlib.model.LoanSlip;

import java.util.ArrayList;

public class ConfirmOrderMemFragment extends Fragment {
    FragmentConfirmOrderMemBinding binding;
    ArrayList<LoanSlip> list;
    ConfirmOderMemAdapter adapter;
    Bundle bundle;
    public ConfirmOrderMemFragment() {
    }
    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getArguments();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentConfirmOrderMemBinding.inflate(inflater);
        initUI();
        return binding.getRoot();
    }
    private void initUI(){
        if (bundle!=null){
            list= (ArrayList<LoanSlip>) bundle.getSerializable("list");
        }else{
            list=new ArrayList<>();
        }
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new ConfirmOderMemAdapter(list,0);
        binding.rcv.setAdapter(adapter);
    }
}