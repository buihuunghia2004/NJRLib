package com.example.njrlib.adapters.Member;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
<<<<<<< HEAD
import com.example.njrlib.databinding.ItemCartMemberBinding;
import com.example.njrlib.interfaces.RemoveCartListener;
import com.example.njrlib.model.Book;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<Book> list;

    public CartAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }
    //interface
    RemoveCartListener removeCartListener;
    public void RemoveCartListener(RemoveCartListener removeCartListener){
        this.removeCartListener=removeCartListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
=======
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.ItemCartMemberBinding;
import com.example.njrlib.interfaces.ListItemCartListener;
import com.example.njrlib.interfaces.RemoveCartListener;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.ItemCart;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<ItemCart> listItemCart;

    public CartAdapter(ArrayList<ItemCart> listItemCart) {
        this.listItemCart=listItemCart;
    }
    //interface
    ListItemCartListener listItemCartListener;
    RemoveCartListener removeCartListener;
    public void ListItemCartListener(ListItemCartListener listItemCartListener){
        this.listItemCartListener=listItemCartListener;
    }
    public void RemoveCartListener(RemoveCartListener removeCartListener){
        this.removeCartListener=removeCartListener;
    }
    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
>>>>>>> d2b3ea0 (Initial commit)
        ItemCartMemberBinding binding=ItemCartMemberBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
<<<<<<< HEAD
=======

        Book book=listItemCart.get(position).getBook();
        Glide.with(context).load(book.getLinkImg()).into(holder.binding.imgCover);
        holder.binding.tvName.setText(book.getName());
        holder.binding.tvRentCost.setText(book.getRentCost() +"/ngày");
        holder.binding.tvKOB.setText(book.getIdKOB());
        holder.binding.tvCount.setText("1");
        Log.d("PPPP1", "size"+String.valueOf(listItemCart.size()));
        Log.d("PPPP1", "id"+String.valueOf(listItemCart.get(position).getBook().getId()));

>>>>>>> d2b3ea0 (Initial commit)
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
<<<<<<< HEAD
        return list.size();
=======
        return listItemCart.size();
>>>>>>> d2b3ea0 (Initial commit)
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCartMemberBinding binding;
        public ViewHolder( ItemCartMemberBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bind(int position) {
<<<<<<< HEAD
            Book book=list.get(position);

            Glide.with(context).load(book.getLinkImg()).into(binding.imgCover);
            binding.tvName.setText(book.getName());
            binding.tvRentCost.setText(book.getRentCost() +"/ngày");
            binding.tvKOB.setText(book.getIdKOB());
            binding.tvCount.setText("1");

            //
            binding.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count= Integer.parseInt(binding.tvCount.getText().toString());
                    binding.tvCount.setText(String.valueOf(count+=1));
=======
            Book book=listItemCart.get(position).getBook();

            binding.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count=listItemCart.get(position).getNum()+1;
                    listItemCart.get(position).setNum(count);

                    //setText
                    binding.tvCount.setText(String.valueOf(count));
                    //total money
                    if (binding.cbChoose.isChecked()){
                        listItemCartListener.setListItemCart(listItemCart);
                    }
>>>>>>> d2b3ea0 (Initial commit)
                }
            });
            binding.btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
<<<<<<< HEAD
                    int count= Integer.parseInt(binding.tvCount.getText().toString());
=======
                    int count=listItemCart.get(position).getNum();
>>>>>>> d2b3ea0 (Initial commit)
                    if (count==1){

                    }else{
                        count-=1;
<<<<<<< HEAD
                    }
                    binding.tvCount.setText(String.valueOf(count));
=======
                        listItemCart.get(position).setNum(count);
                    }

                    //setText
                    binding.tvCount.setText(String.valueOf(count));
                    //total money
                    if (binding.cbChoose.isChecked()){
                        listItemCartListener.setListItemCart(listItemCart);
                    }
>>>>>>> d2b3ea0 (Initial commit)
                }
            });
            binding.cbChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
<<<<<<< HEAD

=======
                    if (isChecked){
                        listItemCart.get(position).setIscheck(isChecked);
                        listItemCartListener.setListItemCart(listItemCart);
                    }else{
                        listItemCart.get(position).setIscheck(false);
                        listItemCartListener.setListItemCart(listItemCart);
                    }
>>>>>>> d2b3ea0 (Initial commit)
                }
            });
            binding.btnRemoveBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
<<<<<<< HEAD
                    removeCartListener.removeCartListener(position);
                    Log.d("RRRR", list.get(position).getName()+list.get(position).getId()+"lll");
=======
                    removeCartListener.removeCartListener(book.getId());
>>>>>>> d2b3ea0 (Initial commit)
                }
            });
        }
    }
<<<<<<< HEAD
=======
    public ArrayList<ItemCart> getListItemCart(){
        return listItemCart;
    }

>>>>>>> d2b3ea0 (Initial commit)
}
