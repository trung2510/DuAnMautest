package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public static  final  String SQL_HOA_DON_CHI_TIET = "CREATE TABLE HoaDonChiTiet (" +
            " id text primary key, " +
            " idbill text, " +
            " idbook text" +
            " soluong INTEGER)";

    public static final String TABLE_HOA_DON_CHI_TIET = "HoaDonChiTiet" ;

    public HoaDonChiTietDAO(Context context) {//khoi tao csdl
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    public int insertHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet){
        ContentValues values = new ContentValues();
        values.put("id",hoaDonChiTiet.getId());
        values.put("idbill",hoaDonChiTiet.getIdbill());
        values.put("idbook",hoaDonChiTiet.getIdbook());
        values.put("soluong",hoaDonChiTiet.getSoluong());
        try{
            if(db.insert(TABLE_HOA_DON_CHI_TIET,null,values)<0){
                return -1;
            }

        }
        catch (Exception ex){
            Log.e("HoaDonChiTietDAO ", ex.getMessage());
        }
        return 1;
    }

    public int updateHoaDon(HoaDonChiTiet hoaDonChiTiet){
        ContentValues values = new ContentValues();
        values.put("id",hoaDonChiTiet.getId());
        values.put("idbill",hoaDonChiTiet.getIdbill());
        values.put("idbook",hoaDonChiTiet.getIdbook());
        values.put("soluong",hoaDonChiTiet.getSoluong());
        try{
            if(db.update(TABLE_HOA_DON_CHI_TIET,values,"id=?",new String[]{hoaDonChiTiet.getId()})<0){
                return -1;
            }

        }
        catch (Exception ex){
            Log.e("HoaDonChiTietDAO ", ex.getMessage());
        }
        return 1;
    }

    public List<HoaDonChiTiet> getAllHoaDonChiTiet(){
        List<HoaDonChiTiet> ls = new ArrayList<HoaDonChiTiet>();
        Cursor cursor = db.query(TABLE_HOA_DON_CHI_TIET,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setIdbill(cursor.getString(0));
                hoaDonChiTiet.setIdbook(cursor.getString(1));
                hoaDonChiTiet.setSoluong(cursor.getInt(1));
                ls.add(hoaDonChiTiet);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return ls;
    }

    public void deleteHoaDon(String id){
        db.delete(TABLE_HOA_DON_CHI_TIET,"id=?",new String[]{id});
    }
}
