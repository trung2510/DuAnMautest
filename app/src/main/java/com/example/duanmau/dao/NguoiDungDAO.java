package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    public static  final  String TAG = "NguoiDungDAO: ";
    public static  final  String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung (" +
            " userName text primary key, " +
            " password text," +
            " phone text," +
            " hoTen text);";
    public static final String TABLE_NguoiDung = "NguoiDung" ;

    public NguoiDungDAO(Context context) {//khoi tao csdl
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    public int insertNguoiDung(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        values.put("userName",nguoiDung.getUserName());
        values.put("password",nguoiDung.getPassword());
        values.put("phone",nguoiDung.getPhone());
        values.put("hoTen",nguoiDung.getHoTen());
        try{
            if(db.insert(TABLE_NguoiDung,null,values)<0){
                return -1;
            }

        }
        catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }
        return 1;
    }

    public List<NguoiDung> getAllNguoiDung(){
        List<NguoiDung> ls = new ArrayList<NguoiDung>();
        Cursor cursor = db.query(TABLE_NguoiDung,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setUserName(cursor.getString(0));
                nguoiDung.setPassword(cursor.getString(1));
                nguoiDung.setPhone(cursor.getString(2));
                nguoiDung.setHoTen(cursor.getString(3));
                ls.add(nguoiDung);
            }
            while (cursor.moveToNext());
        }
        db.close();
    return ls;
    }

    public void deleteNguoiDung(String id){
        db.delete(TABLE_NguoiDung,"userName=?",new String[]{id});
    }

    public int updateNguoiDung(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        //values.put("userName",nguoiDung.getUserName());
        values.put("password",nguoiDung.getPassword());
        values.put("phone",nguoiDung.getPhone());
        values.put("hoTen",nguoiDung.getHoTen());
        try{
            if(db.update(TABLE_NguoiDung,values,"userName=?",new String[]{nguoiDung.getUserName()})<0){
                return -1;
            }

        }
        catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }
        return 1;
    }

    public int checkLogin(String userName,String pass){
        Cursor cursor = db.query(TABLE_NguoiDung,null,"userName=? and password=?",new String[]{userName,pass},null,null,null);
        int i = cursor.getCount();
        if (i == 1) {
            return 1;//login thành công
        }
        return -1;//login không thành công

    }

    public int checkLogin1(String userName,String pass){
        if (db.delete(TABLE_NguoiDung,"userName=? and password=?",new String[]{userName,pass}) == 0) {
            return -1;//login khong thanh cong
        }
        return 1;//login thanh cong
    }

}
