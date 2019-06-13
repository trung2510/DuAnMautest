package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public static  final  String SQL_HOA_DON = "CREATE TABLE HoaDon (" +
            " maHoaDon text primary key, " +
            " ngayMua text);";

    public static final String TABLE_HOA_DON = "HoaDon" ;

    public HoaDonDAO(Context context) {//khoi tao csdl
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    public int insertHoaDon(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hoaDon.getMaHoaDon());
        values.put("ngayMua",hoaDon.getNgayMua());
           try{
            if(db.insert(TABLE_HOA_DON,null,values)<0){
                return -1;
            }

        }
        catch (Exception ex){
            Log.e("HoaDonDAO ", ex.getMessage());
        }
        return 1;
    }

    public int updateHoaDon(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hoaDon.getMaHoaDon());
        values.put("ngayMua",hoaDon.getNgayMua());
        try{
            if(db.update(TABLE_HOA_DON,values,"maHoaDon=?",new String[]{hoaDon.getMaHoaDon()})<0){
                return -1;
            }

        }
        catch (Exception ex){
            Log.e("HoaDonDAO ", ex.getMessage());
        }
        return 1;
    }

    public List<HoaDon> getAllHoaDon(){
        List<HoaDon> ls = new ArrayList<HoaDon>();
        Cursor cursor = db.query(TABLE_HOA_DON,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(cursor.getString(0));
                hoaDon.setNgayMua(cursor.getString(1));
                ls.add(hoaDon);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return ls;
    }

    public void deleteHoaDon(String id){
        db.delete(TABLE_HOA_DON,"maHoaDon=?",new String[]{id});
    }

}
