package com.example.njrlib.adapters.Member;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.njrlib.R;
import com.example.njrlib.activities.Member.DetailBookActivity;
import com.example.njrlib.databinding.ItemBookRcvMemberActivityBinding;
import com.example.njrlib.model.Book;
import com.example.njrlib.tool.ActivitySwitcher;

import java.util.ArrayList;

public class BookListInKOBMemberAdapter extends RecyclerView.Adapter<BookListInKOBMemberAdapter.ViewHolder> {
    Context context;
    ArrayList<Book> list;

    public BookListInKOBMemberAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BookListInKOBMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookRcvMemberActivityBinding binding=ItemBookRcvMemberActivityBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListInKOBMemberAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemBookRcvMemberActivityBinding itemBinding;
        public ViewHolder(ItemBookRcvMemberActivityBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding=itemBinding;
        }

        public void bind(int position) {
            ActivitySwitcher activitySwitcher=ActivitySwitcher.getInstance(context);
            Book book=list.get(position);
            Glide.with(context).load(book.getLinkImg()).into(itemBinding.imgCover);
            itemBinding.tvNameBook.setText(book.getName());
            itemBinding.tvRentCost.setText(String.valueOf(book.getRentCost())+"/ngày");
            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DetailBookActivity.class);
                    intent.putExtra("book",book);
                    context.startActivity(intent);
                }
            });
        }
    }
}
