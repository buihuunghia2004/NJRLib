package com.example.njrlib.fragments.admin.LoanShip;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.R;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentOrderManaBinding;
import com.example.njrlib.fragments.Member.MyOrder.ConfirmOrderMemFragment;
import com.example.njrlib.fragments.Member.MyOrder.ReadingOrderMemFragment;
import com.example.njrlib.fragments.Member.MyOrder.ReturnedOrderMemFragment;
import com.example.njrlib.fragments.Member.MyOrder.WaitOrderMemFragment;
import com.example.njrlib.model.LoanSlip;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderManaFragment extends Fragment {
    DataFireBase dataFireBase;
    DataFireBase.LoanSlipData loanSlipData;
    FirebaseAuth mAuth;
    Context context;
    ArrayList<LoanSlip> list;
    FragmentOrderManaBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        mAuth=FirebaseAuth.getInstance();
        dataFireBase=DataFireBase.getInstance(context);
        loanSlipData=dataFireBase.new LoanSlipData();
        list=new ArrayList<>();
        setList();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentOrderManaBinding.inflate(inflater);

        //khai báo các fragment hiển thị
        Fragment confirmFragment=new ConfirmLoanSlipManaFragment();
        Fragment waitFragment=new WaitOrderManaFragment();
        Fragment readingFragment=new ReadingOrderManaFragment();
        Fragment returnedFragment=new ReturnedOrderMemFragment();

        Fragment[] arrFragment={confirmFragment,waitFragment,readingFragment,returnedFragment};

        FragmentManager manager= requireActivity().getSupportFragmentManager();

        Fragment defaultFragment=arrFragment[0];
//        defaultFragment.setArguments(getBundleByCondition(0));

        manager.beginTransaction()
                .add(R.id.flMyOrder,defaultFragment)
                .commit();


        TabLayout.Tab tab=binding.tlyFragmentChooseBook.getTabAt(0);
        binding.tlyFragmentChooseBook.selectTab(tab);
        binding.tlyFragmentChooseBook.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment;
                switch (tab.getPosition()){
                    case 0:fragment=confirmFragment;break;
                    case 1:fragment=waitFragment;break;
                    case 2:fragment=readingFragment;break;
                    default:fragment=confirmFragment;
                }
                manager.beginTransaction().replace(R.id.flMyOrder,fragment).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Xử lý sự kiện khi tab được chọn lại
            }
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
//    private Bundle getBundleByCondition(int condition){
//        Bundle bundle=new Bundle();
//        ArrayList<LoanSlip> listNeed=new ArrayList<>();
//        for (LoanSlip loanSlip:list){
//            if (loanSlip.getCondition()==condition){
//                listNeed.add(loanSlip);
//            }
//        }
//        Log.d("BBBB", "listnedd"+"s"+String.valueOf(listNeed.size())+"t"+String.valueOf(condition));
//        bundle.putSerializable("list",listNeed);
//        return bundle;
//    }
    private void setList(){
        loanSlipData.myRefList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    list.clear();
                    // Lấy thông tin của phiếu mượn từ childSnapshot
                    LoanSlip loanSlip=childSnapshot.getValue(LoanSlip.class);
                    list.add(loanSlip);
                    Log.d("BBBB", "onDataChange: "+loanSlip.getId());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}