package com.example.njrlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
>>>>>>> d2b3ea0 (Initial commit)
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.njrlib.activities.SplashScreenActivity;
import com.example.njrlib.databinding.ActivityMainBinding;
<<<<<<< HEAD
import com.example.njrlib.fragments.MemberManageFragment;
import com.example.njrlib.fragments.admin.ManageBookFragment;
import com.example.njrlib.fragments.admin.ManageKOBFragment;
import com.example.njrlib.model.Member;
=======
import com.example.njrlib.fragments.admin.LoanShip.OrderManaFragment;
import com.example.njrlib.fragments.admin.ManageBookFragment;
import com.example.njrlib.fragments.admin.ManageKOBFragment;
import com.example.njrlib.fragments.admin.ManageLibrarianFragment;
import com.example.njrlib.fragments.admin.LoanShip.ConfirmLoanSlipManaFragment;
import com.example.njrlib.fragments.admin.ManageMemberFragment;
>>>>>>> d2b3ea0 (Initial commit)
import com.example.njrlib.tool.ActivitySwitcher;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

<<<<<<< HEAD
import java.util.ArrayList;

=======
>>>>>>> d2b3ea0 (Initial commit)

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ActivityMainBinding binding;
    ActivitySwitcher activitySwitcher;
    //ui
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        activitySwitcher=ActivitySwitcher.getInstance(this);
        //toolbar
        Toolbar toolbar=binding.toolbar.getRoot();
<<<<<<< HEAD
=======
        toolbar.setTitle("Quản lý thành viên");
>>>>>>> d2b3ea0 (Initial commit)
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_dehaze_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "open", Toast.LENGTH_SHORT).show();
                binding.drawerLayout.open();
            }
        });
        //frame layout
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

<<<<<<< HEAD
        MemberManageFragment memberManageFragment=new MemberManageFragment();
=======
        ManageMemberFragment memberManageFragment=new ManageMemberFragment();
>>>>>>> d2b3ea0 (Initial commit)
        fragmentTransaction.replace(R.id.frameLayout,memberManageFragment);
        fragmentTransaction.commit();

        //drawer layout
        binding.navDrawer.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (R.id.itemMemberMana == id){
<<<<<<< HEAD

                }else if (R.id.itemTicketMana == id){

                }else if (R.id.itemBookMana == id){
                    changeFragment(new ManageBookFragment());
                    binding.drawerLayout.close();
                }else if (R.id.itemKOBMana == id){
                    changeFragment(new ManageKOBFragment());
                    binding.drawerLayout.close();
=======
                    changeFragment(new ManageMemberFragment());
                    binding.drawerLayout.close();
                    toolbar.setTitle("Quản lý thành viên");
                }else if (R.id.itemLibrarianMana == id){
                    changeFragment(new ManageLibrarianFragment());
                    binding.drawerLayout.close();
                    toolbar.setTitle("Quản lý thủ thư");
                }
                else if (R.id.itemTicketMana == id){
                    changeFragment(new OrderManaFragment());
                    binding.drawerLayout.close();
                    toolbar.setTitle("Quản lý phiếu mượn");
                }else if (R.id.itemBookMana == id){
                    changeFragment(new ManageBookFragment());
                    binding.drawerLayout.close();
                    toolbar.setTitle("Quản lý sách");
                }else if (R.id.itemKOBMana == id){
                    changeFragment(new ManageKOBFragment());
                    binding.drawerLayout.close();
                    toolbar.setTitle("Quản lý loại sách");
>>>>>>> d2b3ea0 (Initial commit)
                }else if (R.id.itemTop10 == id){

                }else if (R.id.itemRevenue == id){

                } else if (R.id.itemChangePassword == id){

                }else if(R.id.itemLogout == id){
<<<<<<< HEAD
                    mAuth.signOut();
                    Intent intent=new Intent(MainActivity.this, SplashScreenActivity.class);
=======
                    Intent intent=new Intent(MainActivity.this, SplashScreenActivity.class);

                    SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
>>>>>>> d2b3ea0 (Initial commit)
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });
<<<<<<< HEAD

=======
>>>>>>> d2b3ea0 (Initial commit)
    }
    private void changeFragment(Fragment newFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, newFragment);
        fragmentTransaction.commit();
    }

}