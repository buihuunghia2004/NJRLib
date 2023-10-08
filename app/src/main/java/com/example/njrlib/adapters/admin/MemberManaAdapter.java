package com.example.njrlib.adapters.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.njrlib.databinding.ItemMemberBinding;
import com.example.njrlib.model.Member;
import com.example.njrlib.tool.Tool;

import java.util.ArrayList;

public class MemberManaAdapter extends RecyclerView.Adapter<MemberManaAdapter.ViewHolder> {
    Context context;
    ArrayList<Member> list;

    public MemberManaAdapter(Context context, ArrayList<Member> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MemberManaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMemberBinding binding=ItemMemberBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberManaAdapter.ViewHolder holder, int position) {
        holder.binding(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Tool tool=Tool.getInstance(context);
        ItemMemberBinding binding;
        public ViewHolder(ItemMemberBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void binding(int position) {
            Member member=list.get(position);
            Glide.with(context).load(member.getLinkAvatar()).into(binding.imgAvatar);
            binding.tvName.setText(member.getName());
            binding.tvDOB.setText("1");
            binding.tvPhoneNumber.setText(member.getPhoneNumber());
        }
    }
}
