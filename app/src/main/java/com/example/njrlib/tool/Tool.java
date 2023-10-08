package com.example.njrlib.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.Date;

public class Tool {
    private static Tool instance;
    private Context context;

    public Tool(Context context) {
        this.context = context;
    }
    public static synchronized Tool getInstance(Context context){
        if (instance==null){
            instance=new Tool(context.getApplicationContext());
        }
        return instance;
    }

    public byte[] switchImageToByteArrays(Drawable drawable){
        // Lấy ảnh từ ImageView
        BitmapDrawable db= (BitmapDrawable) drawable;
        Bitmap bitmap;
        if (db!=null){
            bitmap=db.getBitmap();
            // Chuyển bitmap thành mảng byte
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            return byteArray;
        }else {
            return null;
        }
    }
    public Bitmap switchBlobToImg(byte[] data){
        Bitmap bitmap = null;
        if (data!=null){
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        }
        return bitmap;
    }
<<<<<<< HEAD
    public String switchDateToString(Date yourDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date;
        date = formatter.format(yourDate);
        return date;
    }
    public Date switchStringToDate(String yourDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = formatter.parse(yourDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
=======
>>>>>>> d2b3ea0 (Initial commit)
}
