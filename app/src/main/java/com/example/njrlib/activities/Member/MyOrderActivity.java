package com.example.njrlib.activities.Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.njrlib.R;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.ActivityMyOrderBinding;
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

public class MyOrderActivity extends AppCompatActivity {
    DataFireBase dataFireBase;
    DataFireBase.LoanSlipData loanSlipData;
    FirebaseAuth mAuth;
    ActivityMyOrderBinding binding;
    ArrayList<LoanSlip> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMyOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth=FirebaseAuth.getInstance();
        dataFireBase=DataFireBase.getInstance(this);
        loanSlipData=dataFireBase.new LoanSlipData();
        list=new ArrayList<>();
        setList();

        //khai báo các fragment hiển thị
        Fragment confirmFragment=new ConfirmOrderMemFragment();
        Fragment waitFragment=new WaitOrderMemFragment();
        Fragment readingFragment=new ReadingOrderMemFragment();
        Fragment returnedFragment=new ReturnedOrderMemFragment();

        Fragment[] arrFragment={confirmFragment,waitFragment,readingFragment,returnedFragment};


        FragmentManager manager= getSupportFragmentManager();
        Intent intent=getIntent();
        int type=0;
        if (intent!=null){
            type=intent.getIntExtra("type",0);
            Fragment defaultFragment=arrFragment[type];
            defaultFragment.setArguments(getBundleByCondition(type));

            manager.beginTransaction()
                    .add(R.id.flMyOrder,defaultFragment)
                    .commit();
        }



        TabLayout.Tab tab=binding.tlyFragmentChooseBook.getTabAt(type);
        binding.tlyFragmentChooseBook.selectTab(tab);
        binding.tlyFragmentChooseBook.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment;
                switch (tab.getPosition()){
                    case 0:fragment=confirmFragment;fragment.setArguments(getBundleByCondition(0));break;
                    case 1:fragment=waitFragment;fragment.setArguments(getBundleByCondition(1));break;
                    case 2:fragment=readingFragment;fragment.setArguments(getBundleByCondition(2));break;
                    default:fragment=returnedFragment;fragment.setArguments(getBundleByCondition(3));
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
    }
    private void setList(){
        Query query = loanSlipData.myRefList.orderByChild("memberId").equalTo(mAuth.getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    // Lấy thông tin của phiếu mượn từ childSnapshot
                    LoanSlip loanSlip=childSnapshot.getValue(LoanSlip.class);
                    list.add(loanSlip);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private Bundle getBundleByCondition(int condition){
        Bundle bundle=new Bundle();
        ArrayList<LoanSlip> listNeed=new ArrayList<>();
        for (LoanSlip loanSlip:list){
            if (loanSlip.getCondition()==condition){
                listNeed.add(loanSlip);
            }
        }
        Log.d("BBBB", "listnedd"+"s"+String.valueOf(listNeed.size())+"t"+String.valueOf(condition));
        bundle.putSerializable("list",listNeed);
        return bundle;
    }
}