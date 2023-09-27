package com.example.njrlib.fragments.Member.FagmentChooseBook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.R;
import com.example.njrlib.adapters.Member.KOBListMemberAdapter;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentCBListBinding;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.KindOfBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CBListFragment extends Fragment {
    FragmentCBListBinding binding;
    DataFireBase dataFireBase;
    DataFireBase.KOBData kobData;
    DataFireBase.BooksData booksData;
    Context context;
    KOBListMemberAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding=FragmentCBListBinding.inflate(inflater);
        context=getContext();
        dataFireBase=new DataFireBase(context);
        kobData=dataFireBase.new KOBData();
        booksData=dataFireBase.new BooksData();




        kobData.myRefList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotKOB) {
                booksData.myRefList.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshotBook) {
                        ArrayList<KindOfBook> listKOB=new ArrayList<>();
                        ArrayList<Book> listBook=new ArrayList<>();
                        listKOB=kobData.getList(snapshotKOB);
                        listBook=booksData.getList(snapshotBook);

                        adapter=new KOBListMemberAdapter(context,listKOB,listBook);
                        binding.rcv.setAdapter(adapter);
                        binding.rcv.setLayoutManager(new LinearLayoutManager(context));

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return binding.getRoot();
    }
}