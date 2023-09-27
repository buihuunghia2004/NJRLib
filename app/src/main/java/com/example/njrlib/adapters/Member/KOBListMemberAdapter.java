package com.example.njrlib.adapters.Member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njrlib.databinding.ItemListBookMemberActivityBinding;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.KindOfBook;

import java.util.ArrayList;

public class KOBListMemberAdapter extends RecyclerView.Adapter<KOBListMemberAdapter.ViewHolder> {
    Context context;
    ArrayList<KindOfBook> list;
    ArrayList<Book> bookArrayList;
    ArrayList<Book> bookArrayListStamp;

    public KOBListMemberAdapter(Context context, ArrayList<KindOfBook> list, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.list = list;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public KOBListMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBookMemberActivityBinding binding=ItemListBookMemberActivityBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull KOBListMemberAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemListBookMemberActivityBinding itemBinding;
        public ViewHolder( ItemListBookMemberActivityBinding itemBinding) {
            super(itemBinding.getRoot());
           this.itemBinding=itemBinding;
        }

        public void bind(int position) {
            KindOfBook kob=list.get(position);
            itemBinding.tvKOB.setText(kob.getName());

            //handle data
            bookArrayListStamp=new ArrayList<>();
            for (Book book : bookArrayList){
                if (book.getIdKOB().equals(kob.getId())){
                    bookArrayListStamp.add(book);
                }
            }

            BookListInKOBMemberAdapter adapter=new BookListInKOBMemberAdapter(context,bookArrayListStamp);
            itemBinding.rcvListBookInKOB.setAdapter(adapter);
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            itemBinding.rcvListBookInKOB.setLayoutManager(layoutManager);
        }
    }
}
