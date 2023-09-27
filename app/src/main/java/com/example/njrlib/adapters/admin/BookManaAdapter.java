package com.example.njrlib.adapters.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.njrlib.R;
import com.example.njrlib.databinding.ItemBookManageBinding;
import com.example.njrlib.interfaces.ClickItemRcv;
import com.example.njrlib.interfaces.ItemChooseListener;
import com.example.njrlib.model.Book;
import com.example.njrlib.tool.Tool;

import java.util.ArrayList;

public class BookManaAdapter extends RecyclerView.Adapter<BookManaAdapter.ViewHolder> {
    Context context;
    ArrayList<Book> list;
    //
    Boolean isItemChoose=false;
    ArrayList<Integer> arrItemChoose=new ArrayList<>();
    private ItemChooseListener itemChooseListener;
    private ClickItemRcv clickItem;
    public void sizeArrItemListener(ItemChooseListener itemChooseListener){
        this.itemChooseListener=itemChooseListener;
    }
    public void setClickItem(ClickItemRcv clickItem) {
        this.clickItem = clickItem;
    }
    //
    public BookManaAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }

    public BookManaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookManageBinding binding = ItemBookManageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookManaAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemBookManageBinding itemBinding;
        public ViewHolder( ItemBookManageBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding=itemBinding;
        }
        private void bind(int position){
            Book book=list.get(position);

            //setUI
            Glide.with(context).load(book.getLinkImg()).into(itemBinding.imgCover);
            itemBinding.tvID.setText(book.getId());
            itemBinding.tvName.setText(book.getName());
            itemBinding.tvKOB.setText(book.getIdKOB());
            itemBinding.tvRentCost.setText(String.valueOf(book.getRentCost())+" đ/ngày");

            //item long click
            itemBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemBinding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.black));
                    itemBinding.tvName.setTextColor(ContextCompat.getColor(context, R.color.white));
                    itemBinding.tvID.setTextColor(ContextCompat.getColor(context, R.color.white));

                    arrItemChoose.add(position);
                    isItemChoose=true;

                    if (itemChooseListener!=null){
                        itemChooseListener.itemChooseListener(arrItemChoose.size());
                    }
                    return true;
                }
            });
            //item click
            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //nếu có item được chọn
                    if (isItemChoose){
                        if (have()){
                            itemBinding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                            itemBinding.tvName.setTextColor(ContextCompat.getColor(context, R.color.black));
                            itemBinding.tvID.setTextColor(ContextCompat.getColor(context, R.color.black));

                            arrItemChoose.remove((Object) position);
                            if (arrItemChoose.size()==0){
                                isItemChoose=false;
                            }
                        }else{
                            itemBinding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.black));
                            itemBinding.tvName.setTextColor(ContextCompat.getColor(context, R.color.white));
                            itemBinding.tvID.setTextColor(ContextCompat.getColor(context, R.color.white));

                            arrItemChoose.add(position);
                        }
                    }else{
                        clickItem.clickItemRcv(position);
                    }
                    if (itemChooseListener!=null){
                        itemChooseListener.itemChooseListener(arrItemChoose.size());
                    }
                }
                private boolean have(){
                    for (int i:arrItemChoose){
                        if (i==position){
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }
    public ArrayList<Integer> getArrItemChoose(){
        return arrItemChoose;
    }
}
