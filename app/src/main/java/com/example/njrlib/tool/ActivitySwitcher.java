package com.example.njrlib.tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivitySwitcher {
    private static ActivitySwitcher instance;
    private Context context;
    public ActivitySwitcher(Context context) {
        this.context = context;
    }

    public static synchronized ActivitySwitcher getInstance(Context context){
        if (instance==null){
            instance=new ActivitySwitcher(context.getApplicationContext());
        }
        return instance;
    }
    public void switchToActivity(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(context, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
