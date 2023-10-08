package com.example.njrlib.activities.Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.njrlib.R;
import com.example.njrlib.activities.SplashScreenActivity;
import com.example.njrlib.adapters.AdapterViewpager;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.ActivityMemberBinding;
import com.example.njrlib.databinding.HeaderNavDrawerBinding;
import com.example.njrlib.databinding.NavDrawerBinding;
import com.example.njrlib.fragments.Member.CurrentMember;
import com.example.njrlib.model.Member;
import com.example.njrlib.tool.ActivitySwitcher;
import com.example.njrlib.tool.Tool;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MemberActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DataFireBase dataFireBase;
    private DataFireBase.MembersData membersData;

    ActivityMemberBinding binding;
    NavDrawerBinding navDrawerBinding;
    HeaderNavDrawerBinding headerNavDrawerBinding;

    private Tool tool;
    private ActivitySwitcher activitySwitcher;

    AdapterViewpager adapterViewpager;
    String uid;
    Member curentMember=new Member();
    CurrentMember cMember;
<<<<<<< HEAD
=======
    ValueEventListener valueEventListener;
>>>>>>> d2b3ea0 (Initial commit)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMemberBinding.inflate(getLayoutInflater());
        navDrawerBinding=NavDrawerBinding.inflate(getLayoutInflater());
        View headerNavView=binding.navigationView.getHeaderView(0);
        setContentView(binding.getRoot());
        //firebase
        dataFireBase=DataFireBase.getInstance(this);
        membersData=dataFireBase.new MembersData();
        mAuth=FirebaseAuth.getInstance();
        //tool
        tool=Tool.getInstance(this);
        activitySwitcher=ActivitySwitcher.getInstance(this);
        cMember=CurrentMember.getInstance(this);
        //curent member
        uid=mAuth.getUid();
        Log.d("uid",uid);
        curentMember.setId(uid);

<<<<<<< HEAD
        membersData.myRefList.child(uid).addValueEventListener(new ValueEventListener() {
=======
        valueEventListener=new ValueEventListener() {
>>>>>>> d2b3ea0 (Initial commit)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                curentMember=membersData.getMember(snapshot);

                ImageView imgAvatar=headerNavView.findViewById(R.id.imgAvatarHeaderNav);
                TextView tvName=headerNavView.findViewById(R.id.tvNameHeaderNav);

                Glide.with(MemberActivity.this).load(curentMember.getLinkAvatar()).into(imgAvatar);
                tvName.setText(curentMember.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
<<<<<<< HEAD
        });
=======
        };
        membersData.myRefList.child(uid).addValueEventListener(valueEventListener);
>>>>>>> d2b3ea0 (Initial commit)

        //set up UI
        adapterViewpager=new AdapterViewpager(getSupportFragmentManager(),getLifecycle());

        activitySwitcher=ActivitySwitcher.getInstance(this);
        //toolbar
        Toolbar toolbar=binding.toolbar.getRoot();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_dehaze_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.open();
            }
        });
        //nav
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id == R.id.itemLogout){
                    mAuth.signOut();
                    activitySwitcher.switchToActivity(SplashScreenActivity.class);
                    finish();
                }
                return true;
            }
        });
<<<<<<< HEAD
        viewPagerAndBottomNav();
=======
>>>>>>> d2b3ea0 (Initial commit)
        binding.viewPagerMain.setAdapter(adapterViewpager);
        int[] arrId={
                R.id.itemChooseBook,
                R.id.itemFavorite,
                R.id.itemNotification,
                R.id.itemIndividual
        };
        binding.viewPagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:binding.bottomNavigationView.setSelectedItemId(arrId[position]);break;
                    case 1:binding.bottomNavigationView.setSelectedItemId(arrId[position]);break;
                    case 2:binding.bottomNavigationView.setSelectedItemId(arrId[position]);break;
                    case 3:binding.bottomNavigationView.setSelectedItemId(arrId[position]);break;
                }
            }
        });
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id== R.id.itemChooseBook){
                    binding.viewPagerMain.setCurrentItem(0);
                }else if (id== R.id.itemFavorite){
                    binding.viewPagerMain.setCurrentItem(1);
                }else if (id== R.id.itemNotification){
                    binding.viewPagerMain.setCurrentItem(2);
                }else {
                    binding.viewPagerMain.setCurrentItem(3);
                }
                return true;
            }
        });
    }
<<<<<<< HEAD
    private void viewPagerAndBottomNav(){

=======

    @Override
    protected void onStop() {
        super.onStop();
        membersData.myRefList.removeEventListener(valueEventListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (valueEventListener!=null){
            membersData.myRefList.addValueEventListener(valueEventListener);
        }
>>>>>>> d2b3ea0 (Initial commit)
    }
}