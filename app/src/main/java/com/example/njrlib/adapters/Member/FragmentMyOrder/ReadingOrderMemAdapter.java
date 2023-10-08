package com.example.njrlib.adapters.Member.FragmentMyOrder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.ItemReadingOrderMemberBinding;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.LoanSlip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadingOrderMemAdapter extends RecyclerView.Adapter<ReadingOrderMemAdapter.ViewHolder> {
    DataFireBase dataFireBase;
    DataFireBase.BooksData booksData;
    Context context;
    ArrayList<LoanSlip> list;
    Book book;

    public ReadingOrderMemAdapter(ArrayList<LoanSlip> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ReadingOrderMemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        dataFireBase=DataFireBase.getInstance(context);
        booksData=dataFireBase.new BooksData();
        ItemReadingOrderMemberBinding binding=ItemReadingOrderMemberBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingOrderMemAdapter.ViewHolder holder, int position) {
        LoanSlip loanSlip=list.get(position);
        Query query = booksData.myRefList.orderByChild("id").equalTo(loanSlip.getIdBook());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    // Lấy thông tin của phiếu mượn từ childSnapshot
                    book=childSnapshot.getValue(Book.class);
                    Glide.with(context).load(book.getLinkImg()).into(holder.binding.imgCover);
                    holder.binding.tvName.setText(book.getName());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        String dayLeft=String.valueOf(loanSlip.getDayReturn()-loanSlip.getDayOfBorrowing());
        holder.binding.tvDayLeft.setText("còn "+dayLeft+"ngày");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemReadingOrderMemberBinding binding;
        public ViewHolder(ItemReadingOrderMemberBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
