package com.example.njrlib.fragments.Member.MyOrder;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.R;
import com.example.njrlib.adapters.Member.FragmentMyOrder.ReadingOrderMemAdapter;
import com.example.njrlib.adapters.Member.FragmentMyOrder.WaitOrderMemAdapter;
import com.example.njrlib.databinding.FragmentReadingOrderMemBinding;
import com.example.njrlib.model.LoanSlip;

import java.util.ArrayList;

public class ReadingOrderMemFragment extends Fragment {
    ArrayList<LoanSlip> list;
    ReadingOrderMemAdapter adapter;
    Bundle bundle;
    FragmentReadingOrderMemBinding binding;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        bundle=getArguments();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentReadingOrderMemBinding.inflate(inflater);
        // Inflate the layout for this fragment
        initUI();
        return binding.getRoot();
    }
    private void initUI(){
        if (bundle!=null){
            list= (ArrayList<LoanSlip>) bundle.getSerializable("list");
        }else{
            list=new ArrayList<>();
        }
        Log.d("OOO", "initUI: "+String.valueOf(list.size()));
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new ReadingOrderMemAdapter(list);
        binding.rcv.setAdapter(adapter);
    }
}