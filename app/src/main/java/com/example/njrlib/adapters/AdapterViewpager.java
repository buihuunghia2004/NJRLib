package com.example.njrlib.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.njrlib.fragments.Member.ChooseBookFragment;
import com.example.njrlib.fragments.Member.CartFragment;
import com.example.njrlib.fragments.Member.IndividualFragment;
import com.example.njrlib.fragments.Member.NotificationFragment;

import java.util.ArrayList;

public class AdapterViewpager extends FragmentStateAdapter {
    private final ArrayList<Fragment> fragmentsList = new ArrayList<>();

    public AdapterViewpager(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {     // Khởi tạo Fragment tại vị trí
        Fragment fragment;
        switch (position){
            case 0:fragment=new ChooseBookFragment();break;
            case 1:fragment=new CartFragment();break;
            case 2:fragment=new NotificationFragment();break;
            default:fragment=new IndividualFragment();
        }

        return fragment;
    }

    @Override
    public int getItemCount() {                    // Số lượng Fragment
        return 5;
    }
}
