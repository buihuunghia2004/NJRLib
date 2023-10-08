package com.example.njrlib.adapters.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njrlib.R;
import com.example.njrlib.databinding.ItemLibrarianBinding;
import com.example.njrlib.interfaces.ClickItemRcv;
import com.example.njrlib.interfaces.ItemChooseListener;
import com.example.njrlib.model.Librarian;

import java.util.ArrayList;

public class LibrarianManaAdapter extends RecyclerView.Adapter<LibrarianManaAdapter.ViewHolder> {
    Context context;
    ArrayList<Librarian> list;
    Boolean isItemChoose=false;
    ArrayList<Integer> arrItemChoose=new ArrayList<>();
    private ItemChooseListener itemChooseListener;
    private ClickItemRcv clickItemKOB;
    public void sizeArrItemListener(ItemChooseListener itemChooseListener){
        this.itemChooseListener=itemChooseListener;
    }
    public void setClickItemKOB(ClickItemRcv clickItemKOB) {
        this.clickItemKOB = clickItemKOB;
    }


    public LibrarianManaAdapter(Context context, ArrayList<Librarian> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LibrarianManaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLibrarianBinding binding=ItemLibrarianBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrarianManaAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemLibrarianBinding binding;
        public ViewHolder(ItemLibrarianBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }

        public void bind(int position){
            Librarian librarian=list.get(position);

            //setUI
            binding.tvId.setText(librarian.getId());
            binding.tvPassword.setText(librarian.getPassword());
            binding.tvName.setText(librarian.getName());

            //item long click
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    binding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.black));
                    binding.tvName.setTextColor(ContextCompat.getColor(context, R.color.white));
                    binding.tvId.setTextColor(ContextCompat.getColor(context, R.color.white));
                    binding.tvPassword.setTextColor(ContextCompat.getColor(context, R.color.white));

                    arrItemChoose.add(position);
                    isItemChoose=true;

                    if (itemChooseListener!=null){
                        itemChooseListener.itemChooseListener(arrItemChoose.size());
                    }
                    return true;
                }
            });
            //item click
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isItemChoose){
                        if (have()){
                            binding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                            binding.tvName.setTextColor(ContextCompat.getColor(context, R.color.black));
                            binding.tvId.setTextColor(ContextCompat.getColor(context, R.color.black));
                            binding.tvPassword.setTextColor(ContextCompat.getColor(context, R.color.black));

                            arrItemChoose.remove((Object) position);
                            if (arrItemChoose.size()==0){
                                isItemChoose=false;
                            }
                        }else{
                            binding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.black));
                            binding.tvName.setTextColor(ContextCompat.getColor(context, R.color.white));
                            binding.tvId.setTextColor(ContextCompat.getColor(context, R.color.white));
                            binding.tvPassword.setTextColor(ContextCompat.getColor(context, R.color.white));

                            arrItemChoose.add(position);
                        }
                    }else{
                        clickItemKOB.clickItemRcv(position);
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
