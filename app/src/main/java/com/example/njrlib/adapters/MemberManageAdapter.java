package com.example.njrlib.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.njrlib.R;
import com.example.njrlib.databinding.ItemMemberBinding;
import com.example.njrlib.model.Member;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MemberManageAdapter extends RecyclerView.Adapter<MemberManageAdapter.ViewHolder> {
    ArrayList<Member> list;
    Context context;

    public MemberManageAdapter(ArrayList<Member> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public MemberManageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMemberBinding binding=ItemMemberBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberManageAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMemberBinding binding;
        public ViewHolder(ItemMemberBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bind(Member member){
            binding.tvName.setText(member.getName());
            binding.tvDOB.setText(String.valueOf(member.getDob()));
            binding.tvPhoneNumber.setText(member.getPhoneNumber());
            Glide.with(context).load(member.getLinkAvatar().trim()).into(binding.imgAvatar);
        }
    }
}
