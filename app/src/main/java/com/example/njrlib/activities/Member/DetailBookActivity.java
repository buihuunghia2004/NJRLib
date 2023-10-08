package com.example.njrlib.activities.Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.njrlib.R;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.ActivityDetailBookBinding;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.Cart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailBookActivity extends AppCompatActivity {
    DataFireBase dataFireBase;
    DataFireBase.CartsData cartsData;
    FirebaseAuth mAuth;
    ActivityDetailBookBinding binding;
<<<<<<< HEAD
=======
    ValueEventListener valueEventListener;
>>>>>>> d2b3ea0 (Initial commit)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //firebase
        dataFireBase=DataFireBase.getInstance(this);
        cartsData=dataFireBase.new CartsData();
        mAuth=FirebaseAuth.getInstance();

        //button trở lại
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        //nhận data từ intent
        Intent intent=getIntent();
        Book book= (Book) intent.getSerializableExtra("book");
        //setUI
        Glide.with(this).load(book.getLinkImg()).into(binding.imgCover);
        binding.tvName.setText(book.getName());
        binding.tvRentCost.setText(String.valueOf(book.getRentCost())+"đ/ngày");

        //thêm sách vào giỏ hàng
        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

<<<<<<< HEAD
                ValueEventListener valueEventListener=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<String> listIdBook=cartsData.getList(snapshot);

                        if (listIdBook.contains(book.getId())){
                            Toast.makeText(DetailBookActivity.this, "Sách đã có trong giỏ hàng ", Toast.LENGTH_SHORT).show();
                        }else{
                            listIdBook.add(book.getId());
                            Cart cart=new Cart(mAuth.getUid(),listIdBook);
                            cartsData.setAdd(cart);
                            cartsData.myRefList.removeEventListener(this);
                            Toast.makeText(DetailBookActivity.this, "Đã thêm sách vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                cartsData.myRefList.addValueEventListener(valueEventListener);
=======
                valueEventListener=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        cartsData.myRefList.child(mAuth.getUid()).child(book.getId()).setValue(1);
                        Toast.makeText(DetailBookActivity.this, "Đã thêm sách vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        //Bổ sung chức năng kiểm tra sách đã có trong giỏ hàng hay chưa

                        cartsData.myRefList.child(mAuth.getUid()).removeEventListener(this);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                };
                cartsData.myRefList.child(mAuth.getUid()).addValueEventListener(valueEventListener);
>>>>>>> d2b3ea0 (Initial commit)
            }
        });
        //thuê sách
        binding.btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Book> list=new ArrayList<>();
                list.add(book);

                Intent rentIntent=new Intent(DetailBookActivity.this, RentBookActivity.class);
                intent.putExtra("list",list);
                startActivity(rentIntent);
            }
        });
    }
<<<<<<< HEAD
=======

    @Override
    protected void onResume() {
        super.onResume();
        if (valueEventListener!=null){
            cartsData.myRefList.child(mAuth.getUid()).addValueEventListener(valueEventListener);
        }
    }
>>>>>>> d2b3ea0 (Initial commit)
}