package com.example.njrlib.adapters.admin;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njrlib.R;
import com.example.njrlib.databinding.ItemKindofbookBinding;
import com.example.njrlib.interfaces.ClickItemRcv;
import com.example.njrlib.interfaces.ItemChooseListener;
import com.example.njrlib.model.KindOfBook;

import java.util.ArrayList;

public class KOBListAdapter extends RecyclerView.Adapter<KOBListAdapter.ViewHolder> {
    ArrayList<KindOfBook> list;
    Context context;
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

    public KOBListAdapter(ArrayList<KindOfBook> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public KOBListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        ItemKindofbookBinding binding = ItemKindofbookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull KOBListAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemKindofbookBinding binding;
        public ViewHolder(ItemKindofbookBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bind(KindOfBook kind,int position){
            //set UI
            binding.tvID.setText(kind.getId());
            binding.tvName.setText(kind.getName());

            //item long click
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    binding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.black));
                    binding.tvName.setTextColor(ContextCompat.getColor(context, R.color.white));
                    binding.tvID.setTextColor(ContextCompat.getColor(context, R.color.white));

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
                            binding.tvID.setTextColor(ContextCompat.getColor(context, R.color.black));

                            arrItemChoose.remove((Object) position);
                            if (arrItemChoose.size()==0){
                                isItemChoose=false;
                            }
                        }else{
                            binding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.black));
                            binding.tvName.setTextColor(ContextCompat.getColor(context, R.color.white));
                            binding.tvID.setTextColor(ContextCompat.getColor(context, R.color.white));

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
<<<<<<< HEAD

=======
>>>>>>> d2b3ea0 (Initial commit)
        }
    }
    public ArrayList<Integer> getArrItemChoose(){
        return arrItemChoose;
    }
}
