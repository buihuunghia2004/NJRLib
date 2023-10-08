package com.example.njrlib.adapters.admin.Order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njrlib.databinding.ItemReadingOrderManaBinding;
import com.example.njrlib.databinding.ItemWaitOrderManaBinding;
import com.example.njrlib.interfaces.ConditionListener;
import com.example.njrlib.model.LoanSlip;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReadingOrderManaAdapter extends RecyclerView.Adapter<ReadingOrderManaAdapter.ViewHolder> {
    Context context;
    ArrayList<LoanSlip> list;

    ConditionListener conditionListener;
    public void setConditionListener(ConditionListener conditionListener){
        this.conditionListener=conditionListener;
    }

    public ReadingOrderManaAdapter(ArrayList<LoanSlip> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ReadingOrderManaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        ItemReadingOrderManaBinding binding=ItemReadingOrderManaBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingOrderManaAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LoanSlip loanSlip=list.get(position);
        holder.binding.tvIdLoanSlip.setText(loanSlip.getId());
        holder.binding.tvIdMember.setText(loanSlip.getMemerId());
        holder.binding.tvIdBook.setText(loanSlip.getIdBook());
        holder.binding.tvDayOfBorrowing.setText(String.valueOf(LocalDate.ofEpochDay(loanSlip.getDayOfBorrowing())));
        holder.binding.tvDayReturn.setText(String.valueOf(LocalDate.ofEpochDay(loanSlip.getDayReturn())));
        //holder.binding.tvMoneyAndCount.setText(loanSlip.get);
        holder.binding.tvTotalMoney.setText(String.valueOf(loanSlip.getMoney()));
        long dayLeft=loanSlip.getDayReturn()-LocalDate.now().toEpochDay();
        holder.binding.tvDayLeft.setText("Hôm nay");
        if (dayLeft>0){
            holder.binding.btnConfirm.setVisibility(View.GONE);
            holder.binding.tvDayLeft.setText("Còn "+String.valueOf(dayLeft)+" ngày");
        }else{

        }
        holder.binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionListener.setConditionListener(3,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemReadingOrderManaBinding binding;
        public ViewHolder(ItemReadingOrderManaBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
