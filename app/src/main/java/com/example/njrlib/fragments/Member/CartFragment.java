package com.example.njrlib.fragments.Member;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
<<<<<<< HEAD
=======
import androidx.annotation.Nullable;
>>>>>>> d2b3ea0 (Initial commit)
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
=======
import android.widget.CompoundButton;
import android.widget.Toast;
>>>>>>> d2b3ea0 (Initial commit)

import com.example.njrlib.R;
import com.example.njrlib.adapters.Member.CartAdapter;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.FragmentCartBinding;
<<<<<<< HEAD
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
=======

import com.example.njrlib.interfaces.ListItemCartListener;
import com.example.njrlib.interfaces.RemoveCartListener;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.Cart;
import com.example.njrlib.model.ItemCart;
import com.example.njrlib.model.LoanSlip;
import com.example.njrlib.model.Member;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment {
    FirebaseAuth mAuth;
    DataFireBase dataFireBase;
    DataFireBase.CartsData cartsData;
    DataFireBase.BooksData booksData;
    DataFireBase.MembersData membersData;
    CurrentMember currentMember;
    FragmentCartBinding binding;
    Context context;
    HashMap<String,Integer> hashMap;
    //material
    ArrayList<String> listId;
    ArrayList<Integer> listNum;
    ArrayList<ItemCart> listItemCart;
    CartAdapter adapter;
    ValueEventListener valueEventListener;
    Member member;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //data
        mAuth=FirebaseAuth.getInstance();
        dataFireBase=DataFireBase.getInstance(context);
        cartsData=dataFireBase.new CartsData();
        booksData=dataFireBase.new BooksData();
        membersData=dataFireBase.new MembersData();

        listItemCart=new ArrayList<>();
        setListItemCart();

        membersData.myRefList.child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                member=snapshot.getValue(Member.class);
                Log.d("MMMM", "onDataChange: "+member.getId());
>>>>>>> d2b3ea0 (Initial commit)
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
<<<<<<< HEAD

        return binding.getRoot();
    }
    void showDialogRemove(int id){
=======
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding=FragmentCartBinding.inflate(inflater);
        context=getContext();

        initUI();

        adapter.RemoveCartListener(new RemoveCartListener() {
            @Override
            public void removeCartListener(String id) {
                showDialogRemove(id);
            }
        });
        adapter.ListItemCartListener(new ListItemCartListener() {
            @Override
            public void setListItemCart(ArrayList<ItemCart> listItemCart) {
                int totalMoney=0;
                for (ItemCart itemCart:listItemCart){
                    if (itemCart.isIscheck()){
                        totalMoney+=itemCart.getBook().getRentCost()*itemCart.getNum();
                    }
                }
                binding.tvCost.setText(String.valueOf(totalMoney));
            }
        });

        binding.btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiểm tra tiền

                //tạo phiếu mượn
                //
                if (member.getMoney()>=Integer.parseInt(binding.tvCost.getText().toString())){
                    for (ItemCart itemCart:listItemCart){
                        if (itemCart.isIscheck()){
                            //tạo phiếu mượn
                            LoanSlip loanSlip=new LoanSlip(
                                    member.getId(),
                                    null,
                                    itemCart.getBook().getId(),
                                    LocalDate.now().toEpochDay(),
                                    LocalDate.now().toEpochDay()+itemCart.getNum(),
                                    itemCart.getNum()*itemCart.getBook().getRentCost(),
                                    0);
                            DataFireBase.LoanSlipData loanSlipData=dataFireBase.new LoanSlipData();
                            loanSlipData.setAdd(loanSlip);
                            //xóa đơn hàng ra khỏi giỏ hàng
                            cartsData.myRefList.child(member.getId()).child(itemCart.getBook().getId()).removeValue();
                        }
                    }
                    Toast.makeText(context, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Tài khoản không đủ tiền", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //button rent click

        return binding.getRoot();
    }
    void showDialogRemove(String id){
>>>>>>> d2b3ea0 (Initial commit)
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo"); // Tiêu đề của dialog
        builder.setMessage("Bạn chắc chắn muốn bỏ sách này ?"); // Nội dung của dialog

<<<<<<< HEAD
// Button "Đồng ý"
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng nhấn nút "Đồng ý"
                cartsData.setRemove(currentMember.getCurrentMember().getId(),id);
                Log.d("RRRR", currentMember.getCurrentMember().getId());
=======
        // Button "Đồng ý"
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                cartsData.myRefList.child(mAuth.getUid()).child(id).removeValue();
>>>>>>> d2b3ea0 (Initial commit)
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
<<<<<<< HEAD
=======
    private void initUI(){
        adapter=new CartAdapter(listItemCart);
        binding.rcvListBookRent.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvListBookRent.setAdapter(adapter);
    }
    private void setListItemCart(){
        cartsData.myRefList.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listItemCart.clear();
                for (DataSnapshot childSnapshotCart:snapshot.getChildren()){
                    Log.d("KKKK", "onDataChange: "+childSnapshotCart.getKey());
                    Query query = booksData.myRefList.orderByChild("id").equalTo(childSnapshotCart.getKey());
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot childSnapshotBook : snapshot.getChildren()) {
                                // Lấy thông tin của phiếu mượn từ childSnapshot
                                Book book=childSnapshotBook.getValue(Book.class);
                                ItemCart itemCart=new ItemCart(book,Integer.parseInt(String.valueOf(childSnapshotCart.getValue())),false);
                                listItemCart.add(itemCart);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
>>>>>>> d2b3ea0 (Initial commit)
}