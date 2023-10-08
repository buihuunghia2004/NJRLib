package com.example.njrlib.database;

import android.content.Context;
import android.util.Log;

<<<<<<< HEAD
=======
import androidx.annotation.NonNull;

>>>>>>> d2b3ea0 (Initial commit)
import com.example.njrlib.fragments.Member.CurrentMember;
import com.example.njrlib.model.Book;
import com.example.njrlib.model.Cart;
import com.example.njrlib.model.KindOfBook;
<<<<<<< HEAD
import com.example.njrlib.model.Member;
import com.example.njrlib.tool.Tool;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
=======
import com.example.njrlib.model.Librarian;
import com.example.njrlib.model.LoanSlip;
import com.example.njrlib.model.Member;
import com.example.njrlib.tool.Tool;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
>>>>>>> d2b3ea0 (Initial commit)
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
<<<<<<< HEAD
=======
import java.util.concurrent.CountDownLatch;
>>>>>>> d2b3ea0 (Initial commit)

public class DataFireBase {
    //tool
    Tool tool;
    CurrentMember currentMember;
    //realtime và storage
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    //Singleton Database
    private static DataFireBase instance;
    private Context context;
    public DataFireBase(Context context) {
        this.context = context;
        tool=Tool.getInstance(context);
        currentMember=CurrentMember.getInstance(context);
    }
    public static synchronized DataFireBase getInstance(Context context){
        if (instance==null){
            instance=new DataFireBase(context.getApplicationContext());
        }
        return instance;
    }

<<<<<<< HEAD

    //Sách
    public class BooksData {
=======
    //Sách
    public class BooksData {
        Book book=new Book();
>>>>>>> d2b3ea0 (Initial commit)
        public DatabaseReference myRefList = database.getReference().child("books");
        public void setAdd(Book book){
            Map<String, Object> bookData = new HashMap<>();
            bookData.put("name", book.getName());
            bookData.put("rentCost",book.getRentCost());
            bookData.put("idKOB",book.getIdKOB());
            bookData.put("linkImg",book.getLinkImg());
            DatabaseReference childRef = myRefList.child(book.getId());
            childRef.setValue(bookData);
        }
        public void setRemove(Book book){
            myRefList.child(book.getId()).removeValue();
        }
        public void setUpdate(Book oldBook, Book newBook){
            setRemove(oldBook);
            setAdd(newBook);
        }
        public ArrayList<Book> getList(DataSnapshot dataSnapshot){
            ArrayList<Book> list=new ArrayList<>();

            HashMap<String,HashMap<String,HashMap<String, Object>>> map= (HashMap<String,HashMap<String,HashMap<String, Object>>>) dataSnapshot.getValue();
            if (map!=null){
                list.clear();
                Log.d("entry", String.valueOf(map));

                for(Map.Entry<String, HashMap<String, HashMap<String, Object>>>  entry : map.entrySet()){
                    Log.d("entry", String.valueOf(entry.getValue()));
                    HashMap<String, HashMap<String, Object>> map2=entry.getValue();

                    Book book=new Book();
                    book.setId(entry.getKey());
                    for(Map.Entry<String, HashMap<String, Object>> entry2 : map2.entrySet()){
                        Log.d("EEE", String.valueOf(entry2.getValue()));
                        String key = entry2.getKey(); // lấy key
                        Object value = entry2.getValue(); // lấy giá trị tương ứng với key
                        // Thực hiện các xử lý khác với key và value tại đây
                        switch (key){
                            case "idKOB":book.setIdKOB(value.toString());break;
                            case "name":book.setName(value.toString());break;
                            case "linkImg":book.setLinkImg(value.toString());break;
                            case "rentCost":book.setRentCost(Integer.parseInt(String.valueOf((long) value)));break;
                        }
                    }
                    list.add(book);
                }
            }
            return list;
        }
<<<<<<< HEAD

        public Book getBook(DataSnapshot snapshot){
            Book book=new Book();
=======
        public Book getBook(DataSnapshot snapshot){
>>>>>>> d2b3ea0 (Initial commit)
            HashMap<String,HashMap<String, Object>> map= (HashMap<String, HashMap<String, Object>>) snapshot.getValue();
            if (map!=null){
                for(Map.Entry<String, HashMap<String, Object>>  entry : map.entrySet()){
                    Log.d("EEE2", String.valueOf(entry.getValue()));
                    String key = entry.getKey(); // lấy key
                    Object value = entry.getValue(); // lấy giá trị tương ứng với key
                    // Thực hiện các xử lý khác với key và value tại đây
                    book.setId(snapshot.getKey());
                    switch (key){
                        case "idKOB":book.setIdKOB(value.toString());break;
                        case "name":book.setName(value.toString());break;
                        case "linkImg":book.setLinkImg(value.toString());break;
                        case "rentCost":book.setRentCost(Integer.parseInt(String.valueOf((long) value)));break;
                    }
                }
            }
            return book;
        }
    }
    //Loại sách
    public class KOBData {
        public DatabaseReference myRefList = database.getReference("kob");
        public ArrayList<KindOfBook> getList(DataSnapshot dataSnapshot){
            ArrayList<KindOfBook> list=new ArrayList<>();

            HashMap<String,HashMap<String,String>> map= (HashMap<String,HashMap<String,String>>) dataSnapshot.getValue();
            if (map!=null){
                list.clear();
                for(Map.Entry<String, HashMap<String, String>> entry : map.entrySet()){
                    KindOfBook kob=new KindOfBook(entry.getKey(),String.valueOf(entry.getValue()));
                    list.add(kob);
                }
            }

            return list;
        }
        public void setAdd(KindOfBook kob){
            myRefList.child(kob.getId()).setValue(kob.getName());
        }
        public void setRemove(KindOfBook kob){
            myRefList.child(kob.getId()).removeValue();
        }
        public void setUpdate(KindOfBook olKOB, KindOfBook newKOB){
            setRemove(olKOB);
            setAdd(newKOB);
        }
    }
    //Thành viên
    public class MembersData{
        public DatabaseReference myRefList = database.getReference().child("members");
        public void setAdd(Member member){
            Map<String, Object> memData = new HashMap<>();
            memData.put("name", member.getName());
<<<<<<< HEAD
            memData.put("dob",tool.switchDateToString(member.getDob()));
=======
            memData.put("dob",member.getDob());
>>>>>>> d2b3ea0 (Initial commit)
            memData.put("phoneNumber",member.getPhoneNumber());
            memData.put("money",member.getMoney());
            memData.put("linkAvatar",member.getLinkAvatar());
            DatabaseReference childRef = myRefList.child(member.getId());
            childRef.setValue(memData);
        }
        public void setRemove(String id){
            myRefList.child(id).removeValue();
        }
        public void setUpdate(String id, Member newMem){
            Map<String, Object> updates = new HashMap<>();
<<<<<<< HEAD
            updates.put("dob",tool.switchDateToString(newMem.getDob()));
=======
            updates.put("dob",newMem.getDob());
>>>>>>> d2b3ea0 (Initial commit)
            updates.put("linkAvatar",newMem.getLinkAvatar());
            updates.put("money",newMem.getMoney());
            updates.put("name",newMem.getName());
            updates.put("phoneNumber",newMem.getPhoneNumber());

            myRefList.child(id).updateChildren(updates);
        }
        public ArrayList<Member> getList(DataSnapshot dataSnapshot){
            ArrayList<Member> list=new ArrayList<>();

            HashMap<String,HashMap<String,HashMap<String, Object>>> map= (HashMap<String,HashMap<String,HashMap<String, Object>>>) dataSnapshot.getValue();
            if (map!=null){
                list.clear();
                Log.d("entry", String.valueOf(map));

                for(Map.Entry<String, HashMap<String, HashMap<String, Object>>>  entry : map.entrySet()){
                    Log.d("entry", String.valueOf(entry.getValue()));
                    HashMap<String, HashMap<String, Object>> map2=entry.getValue();

                    Member member=new Member();
                    member.setId(entry.getKey());
                    for(Map.Entry<String, HashMap<String, Object>> entry2 : map2.entrySet()){
                        Log.d("EEE", String.valueOf(entry2.getValue()));
                        String key = entry2.getKey(); // lấy key
                        Object value = entry2.getValue(); // lấy giá trị tương ứng với key
                        // Thực hiện các xử lý khác với key và value tại đây
                        switch (key){
                            case "name":member.setName(value.toString());break;
<<<<<<< HEAD
                            case "dob":member.setDob(tool.switchStringToDate(value.toString()));break;
=======
                            case "dob":member.setDob(Long.parseLong(String.valueOf(value)));break;
>>>>>>> d2b3ea0 (Initial commit)
                            case "phoneNumber":member.setPhoneNumber(value.toString());break;
                            case "money":member.setMoney(Integer.parseInt(String.valueOf((long) value)));break;
                            case "linkAvatar":member.setLinkAvatar(value.toString());break;
                        }
                    }
                    list.add(member);
                }
            }
            return list;
        }
        public Member getMember(DataSnapshot snapshot){
            HashMap<String, HashMap<String, Object>> map= (HashMap<String, HashMap<String, Object>>) snapshot.getValue();
            Member member=new Member();
            member.setId(snapshot.getKey());
            if (map!=null){
                for(Map.Entry<String, HashMap<String, Object>> entry : map.entrySet()){
                    String key = entry.getKey(); // lấy key
                    Object value = entry.getValue(); // lấy giá trị tương ứng với key
                    // Thực hiện các xử lý khác với key và value tại đây
                    switch (key){
                        case "name":member.setName(value.toString());break;
<<<<<<< HEAD
                        case "dob":member.setDob(tool.switchStringToDate(value.toString()));break;
=======
                        case "dob":member.setDob((Long) value);break;
>>>>>>> d2b3ea0 (Initial commit)
                        case "phoneNumber":member.setPhoneNumber(value.toString());break;
                        case "money":member.setMoney(Integer.parseInt(String.valueOf((long) value)));break;
                        case "linkAvatar":member.setLinkAvatar(value.toString());break;
                    }
                }
            }
            currentMember.setCurrentMember(member);
            return currentMember.getCurrentMember();
        }
        public boolean updateChildMember(String uid,String key,Object value){
            Map<String, Object> updates = new HashMap<>();
            updates.put(key,value);

            //myRefList.child(uid).updateChildren(updates);
            return false;
        }
    }
    //Thủ thư
    public class LibrarianData{
<<<<<<< HEAD

    }
    //Phiếu mượn
    public class ticketBookData{

    }

=======
        public DatabaseReference myRefList = database.getReference().child("librarian");

        public void setAdd(Librarian librarian){
            Map<String, Object> libData = new HashMap<>();
            libData.put("name",librarian.getName());
            libData.put("password",librarian.getPassword());
            DatabaseReference childRef = myRefList.child(librarian.getId());
            childRef.setValue(libData);
        }
        public void setRemove(Librarian librarian){
            myRefList.child(librarian.getId()).removeValue();
        }
        public void setUpdate(Librarian oldLib, Librarian newLib){
            setRemove(oldLib);
            setAdd(newLib);
        }

        public ArrayList<Librarian> getList(DataSnapshot dataSnapshot){
            ArrayList<Librarian> list=new ArrayList<>();

            HashMap<String,HashMap<String,HashMap<String, Object>>> map= (HashMap<String,HashMap<String,HashMap<String, Object>>>) dataSnapshot.getValue();
            if (map!=null){
                list.clear();
                Log.d("entry", String.valueOf(map));

                for(Map.Entry<String, HashMap<String, HashMap<String, Object>>>  entry : map.entrySet()){
                    Log.d("entry", String.valueOf(entry.getValue()));
                    HashMap<String, HashMap<String, Object>> map2=entry.getValue();

                    Librarian librarian=new Librarian();
                    librarian.setId(entry.getKey());
                    for(Map.Entry<String, HashMap<String, Object>> entry2 : map2.entrySet()){
                        Log.d("EEE", String.valueOf(entry2.getValue()));
                        String key = entry2.getKey(); // lấy key
                        Object value = entry2.getValue(); // lấy giá trị tương ứng với key
                        // Thực hiện các xử lý khác với key và value tại đây
                        switch (key){
                            case "name":librarian.setName(value.toString());break;
                            case "password":librarian.setPassword(value.toString());break;
                        }
                    }
                    list.add(librarian);
                }
            }
            return list;
        }
        public Librarian getLibrarianById(DataSnapshot snapshot){
            Librarian librarian=new Librarian();
            HashMap<String, HashMap<String, Object>> map= (HashMap<String, HashMap<String, Object>>) snapshot.getValue();
            librarian.setId(snapshot.getKey());
            if (map!=null){
                for(Map.Entry<String, HashMap<String, Object>> entry : map.entrySet()){
                    String key = entry.getKey(); // lấy key
                    Object value = entry.getValue(); // lấy giá trị tương ứng với key
                    // Thực hiện các xử lý khác với key và value tại đây
                    switch (key){
                        case "name":librarian.setName(value.toString());break;
                        case "password":librarian.setPassword(value.toString());break;
                     }
                }
            }
            return librarian;
        }
    }
    //Phiếu mượn
    ArrayList<LoanSlip> list=new ArrayList<>();
    public class LoanSlipData{

        public DatabaseReference myRefList = database.getReference().child("loanSlip");
        public ArrayList<LoanSlip> getList(DataSnapshot snapshot){
            ArrayList<LoanSlip> list=new ArrayList<>();

            HashMap<String,HashMap<String,HashMap<String, Object>>> map= (HashMap<String,HashMap<String,HashMap<String, Object>>>) snapshot.getValue();
            if (map!=null){
                list.clear();
                Log.d("entry", String.valueOf(map));

                for(Map.Entry<String, HashMap<String, HashMap<String, Object>>>  entry : map.entrySet()){
                    Log.d("entry", String.valueOf(entry.getValue()));
                    HashMap<String, HashMap<String, Object>> map2=entry.getValue();

                    LoanSlip loanSlip=new LoanSlip();
                    loanSlip.setId(entry.getKey());
                    for(Map.Entry<String, HashMap<String, Object>> entry2 : map2.entrySet()){
                        Log.d("EEE", String.valueOf(entry2.getValue()));
                        String key = entry2.getKey(); // lấy key
                        Object value = entry2.getValue(); // lấy giá trị tương ứng với key
                        // Thực hiện các xử lý khác với key và value tại đây
                        switch (key){
                            case "memberId":loanSlip.setMemerId(value.toString());break;
                            case "librarianId":loanSlip.setLibrarianId(String.valueOf(value));break;
                            case "idBook":loanSlip.setIdBook(String.valueOf(value));break;
                            case "dayOfBorrowing":loanSlip.setDayOfBorrowing(Long.parseLong(String.valueOf(value)));break;
                            case "dayReturn":loanSlip.setDayReturn(Long.parseLong(String.valueOf(value)));break;
                            case "money":loanSlip.setMoney(Integer.parseInt(String.valueOf(value)));break;
                            case "condition":loanSlip.setCondition(Integer.parseInt(String.valueOf(value)));break;
                        }
                    }
                    list.add(loanSlip);
                }
            }

            return list;
        }
        public void setAdd(LoanSlip loanSlip){
            Map<String, Object> libData = new HashMap<>();
            DatabaseReference childRef = myRefList.push();
            libData.put("id",childRef.getKey());
            libData.put("memberId",loanSlip.getMemerId());
            libData.put("librarianId",loanSlip.getLibrarianId());
            libData.put("idBook",loanSlip.getIdBook());
            libData.put("dayOfBorrowing",loanSlip.getDayOfBorrowing());
            libData.put("dayReturn",loanSlip.getDayReturn());
            libData.put("money",loanSlip.getMoney());
            libData.put("condition",loanSlip.getCondition());
            //DatabaseReference childRef = myRefList.child(loanSlip.getId());
            childRef.setValue(libData);
        }
    }
>>>>>>> d2b3ea0 (Initial commit)
    //Giỏ hàng
    public class CartsData{
        public DatabaseReference myRefList = database.getReference("cart");

        public void setAdd(Cart cart){
            myRefList.child(cart.getUid()).setValue(cart.getListIdBook());
        }
        public void setRemove(String uid,int positon){
            myRefList.child(uid).child(String.valueOf(positon)).removeValue();
            Log.d("RRRR", "setRemove: "+positon);
            Log.d("RRRR", "setRemove: "+myRefList.child(uid).getKey());
        }
<<<<<<< HEAD

        public ArrayList<String> getList(DataSnapshot dataSnapshot){
            ArrayList<String> ss =new ArrayList<>();
            HashMap<String,Object> map= (HashMap<String,Object>) dataSnapshot.getValue();
            Log.d("AAAA", String.valueOf(map)+"map");
            if (map!=null){
                for(Map.Entry<String, Object> entry : map.entrySet()){
                    Log.d("AAAA", String.valueOf(entry)+"en");
                    Log.d("AAAA", String.valueOf(entry.getKey())+"s2");
                    Log.d("AAAA", String.valueOf(entry.getValue())+"va");

                    ss= (ArrayList<String>) entry.getValue();
                }
                for (String s:ss){
                    if (s==null){
                        ss.remove(s);
                    }
                }
            }
            return ss;
        }
    }

=======
        public HashMap<String,Integer> getList(DataSnapshot dataSnapshot){
            HashMap<String,Integer> map= (HashMap<String,Integer>) dataSnapshot.getValue();
            return map;
        }

    }
>>>>>>> d2b3ea0 (Initial commit)
    //different
    public StorageReference imageRef;
    public UploadTask upLoadImg(String linkImg,byte[] arrByteImg){
        final String[] imageUrl = new String[1];
        imageRef = storageRef.child(linkImg);
        UploadTask uploadTask = imageRef.putBytes(arrByteImg);

        return uploadTask;
    }
}
