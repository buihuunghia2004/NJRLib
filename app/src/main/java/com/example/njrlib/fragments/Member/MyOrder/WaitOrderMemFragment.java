package com.example.njrlib.fragments.Member.MyOrder;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.njrlib.R;
import com.example.njrlib.adapters.Member.FragmentMyOrder.ConfirmOderMemAdapter;
import com.example.njrlib.adapters.Member.FragmentMyOrder.WaitOrderMemAdapter;
import com.example.njrlib.databinding.FragmentWaitOrderMemBinding;
import com.example.njrlib.model.LoanSlip;

import java.util.ArrayList;

public class WaitOrderMemFragment extends Fragment {
    ArrayList<LoanSlip> list;
    WaitOrderMemAdapter adapter;
    Bundle bundle;
    FragmentWaitOrderMemBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentWaitOrderMemBinding.inflate(inflater);
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
        adapter=new WaitOrderMemAdapter(list);
        binding.rcv.setAdapter(adapter);
    }

}