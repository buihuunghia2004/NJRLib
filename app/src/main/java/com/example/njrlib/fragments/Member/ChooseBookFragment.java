package com.example.njrlib.fragments.Member;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.R;
import com.example.njrlib.databinding.FragmentChooseBookBinding;
import com.example.njrlib.databinding.FragmentMemberManageBinding;
import com.example.njrlib.fragments.Member.FagmentChooseBook.CBListFragment;
import com.example.njrlib.fragments.Member.FagmentChooseBook.CBTop10Fragment;
import com.google.android.material.tabs.TabLayout;

public class ChooseBookFragment extends Fragment {
    FragmentChooseBookBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentChooseBookBinding.inflate(inflater);

        Fragment cbListFragment=new CBListFragment();
        Fragment cbTop10Fragment=new CBTop10Fragment();

        FragmentManager manager= requireActivity().getSupportFragmentManager();
        //set default fragment
        manager.beginTransaction()
                .add(R.id.frame_fragment_choosebook,cbListFragment)
                .commit();


        binding.tlyFragmentChooseBook.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment;
                switch (tab.getPosition()){
                    case 0:fragment=cbListFragment;break;
                    default:fragment=cbTop10Fragment;
                }
                manager.beginTransaction().replace(R.id.frame_fragment_choosebook,fragment).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Xử lý sự kiện khi tab bị huỷ chọn
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Xử lý sự kiện khi tab được chọn lại
            }
        });

        return binding.getRoot();
    }
}