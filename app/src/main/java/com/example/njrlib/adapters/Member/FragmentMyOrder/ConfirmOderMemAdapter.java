package com.example.njrlib.adapters.Member.FragmentMyOrder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.njrlib.database.DataFireBase;
import com.example.njrlib.databinding.ItemLoanslipMemberConfirmBinding;
import com.example.njrlib.databinding.ItemWaitOrderMemBinding;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.Librarian;
import com.example.njrlib.model.LoanSlip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConfirmOderMemAdapter extends RecyclerView.Adapter<ConfirmOderMemAdapter.ViewHolder> {
    //firebase
    Context context;
    DataFireBase dataFireBase;
    DataFireBase.BooksData booksData;
    DataFireBase.LibrarianData librarianData;
    Book book;
    ArrayList<LoanSlip> list;
    int type;
    public ConfirmOderMemAdapter(ArrayList<LoanSlip> list,int type) {
        this.list = list;
        this.type=type;
    }
    @NonNull
    @Override
    public ConfirmOderMemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLoanslipMemberConfirmBinding binding=ItemLoanslipMemberConfirmBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context=parent.getContext();
        dataFireBase=DataFireBase.getInstance(context);
        booksData=dataFireBase.new BooksData();
        librarianData=dataFireBase.new LibrarianData();
        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ConfirmOderMemAdapter.ViewHolder holder, int position) {
        LoanSlip loanSlip;
        loanSlip=list.get(position);
        Query query = booksData.myRefList.orderByChild("id").equalTo(loanSlip.getIdBook());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    // Lấy thông tin của phiếu mượn từ childSnapshot
                    book=childSnapshot.getValue(Book.class);
                    Glide.with(context).load(book.getLinkImg()).into(holder.binding.imgCover);
                    holder.binding.tvName.setText(book.getName());
                    holder.binding.tvPrice.setText(String.valueOf(book.getRentCost())+"đ/ngày");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.binding.tvCountDay.setText(String.valueOf(loanSlip.getDayReturn()-loanSlip.getDayOfBorrowing()));
        holder.binding.tvTotalMoney.setText(String.valueOf(loanSlip.getMoney())+"đ");
        holder.binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemLoanslipMemberConfirmBinding binding;
        public ViewHolder(ItemLoanslipMemberConfirmBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
