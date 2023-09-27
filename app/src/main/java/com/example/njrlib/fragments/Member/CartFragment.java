package com.example.njrlib.fragments.Member;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njrlib.R;
import com.example.njrlib.adapters.Member.CartAdapter;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentCartBinding;
import com.example.njrlib.interfaces.RemoveCartListener;
import com.example.njrlib.model.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    DataFireBase dataFireBase;
    DataFireBase.CartsData cartsData;
    DataFireBase.BooksData booksData;
    CurrentMember currentMember;
    FragmentCartBinding binding;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        binding=FragmentCartBinding.inflate(inflater);
        context=getContext();
        //data
        dataFireBase=DataFireBase.getInstance(context);
        cartsData=dataFireBase.new CartsData();
        booksData=dataFireBase.new BooksData();
        currentMember=CurrentMember.getInstance(context);

        //
        cartsData.myRefList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotCart) {
                ArrayList<String> list= cartsData.getList(snapshotCart);

                booksData.myRefList.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshotBook) {
                        ArrayList<Book> listBook=new ArrayList<>();

                        for (String s:list){
                            DataSnapshot dataSnapshot2=snapshotBook.child(s);
                            Log.d("SSSS", dataSnapshot2.getKey());
                            listBook.add(booksData.getBook(dataSnapshot2));
                        }

                        CartAdapter adapter=new CartAdapter(context,listBook);
                        binding.rcvListBookRent.setAdapter(adapter);
                        binding.rcvListBookRent.setLayoutManager(new LinearLayoutManager(context));

                        adapter.RemoveCartListener(new RemoveCartListener() {
                            @Override
                            public void removeCartListener(int id) {
                                showDialogRemove(id);
                            }
                        });
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
    void showDialogRemove(int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo"); // Tiêu đề của dialog
        builder.setMessage("Bạn chắc chắn muốn bỏ sách này ?"); // Nội dung của dialog

// Button "Đồng ý"
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng nhấn nút "Đồng ý"
                cartsData.setRemove(currentMember.getCurrentMember().getId(),id);
                Log.d("RRRR", currentMember.getCurrentMember().getId());
                dialog.dismiss();
            }
        });

// Button "Hủy"
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng nhấn nút "Hủy"
                dialog.dismiss();
            }
        });

// Tạo và hiển thị dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}