package com.example.njrlib.fragments.Member;

import android.content.Context;

import com.example.njrlib.model.Cart;
import com.example.njrlib.model.Member;

public class CurrentMember {
    private  static CurrentMember instance;
    private Context context;
    private static Member currentMember;

    public CurrentMember(Context context) {
        this.context = context;
    }
    public static synchronized CurrentMember getInstance(Context context){
        if (instance==null){
            instance=new CurrentMember(context.getApplicationContext());
        }
        return instance;
    }

    public void setCurrentMember(Member member){
        currentMember=member;
    }
    public Member getCurrentMember(){
        return currentMember;
    }

}
