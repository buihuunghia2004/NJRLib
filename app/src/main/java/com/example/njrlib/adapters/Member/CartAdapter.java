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
        ItemCartMemberBinding binding=ItemCartMemberBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCartMemberBinding binding;
        public ViewHolder( ItemCartMemberBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bind(int position) {
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
                }
            });
            binding.btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count= Integer.parseInt(binding.tvCount.getText().toString());
                    if (count==1){

                    }else{
                        count-=1;
                    }
                    binding.tvCount.setText(String.valueOf(count));
                }
            });
            binding.cbChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            binding.btnRemoveBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeCartListener.removeCartListener(position);
                    Log.d("RRRR", list.get(position).getName()+list.get(position).getId()+"lll");
                }
            });
        }
    }
}
