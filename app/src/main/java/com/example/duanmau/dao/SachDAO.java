package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    public static final String TAG = "SachDAO";
    public static final String SQL_SACH = "CREATE TABLE Sach (" +
            " MaSach text primary key," +
            " MaTheLoai text," +
            " TacGia text," +
            " NXB text," +
            " giaBia float," +
            " soLuong integer" +
            ")";
    public static final String TABLE_SACH = "Sach";

    public SachDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }
    public int insertSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("MaSach",sach.getMaSach());
        values.put("MaTheLoai",sach.getMaTheLoai());
        values.put("TacGia",sach.getTacGia());
        values.put("NXB",sach.getNXB());
        values.put("giaBia",sach.getGiaBia());
        values.put("soLuong",sach.getSoLuong());
        try{
            if (db.insert(TABLE_SACH,null,values)<0) {
                return -1;
            }
        }
        catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }
        return 1;
    }

    public List<Sach> getAllSach(){
        String SELECT = "SELECT * FROM " + TABLE_SACH;
        Cursor cursor = db.rawQuery(SELECT,null);
        List<Sach> sachList = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Sach sach = new Sach();

            sach.setMaSach(cursor.getString(0));
            sach.setMaTheLoai(cursor.getString(1));
            sach.setTacGia(cursor.getString(2));
            sach.setNXB(cursor.getString(3));
            sach.setGiaBia(cursor.getFloat(4));
            sach.setSoLuong(cursor.getInt(5));

            sachList.add(sach);

            cursor.moveToNext();
        }
        cursor.close();

        return sachList;
    }

    public List<String> getMaSach(){
        String SELECT = "SELECT * FROM " + TABLE_SACH;
        Cursor cursor = db.rawQuery(SELECT,null);
        List<String> stringList = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){

            stringList.add(cursor.getString(0));

            cursor.moveToNext();
        }
        cursor.close();

        return stringList;
    }

    public int deleteSach(String id){
        int result = db.delete(TABLE_SACH,"MaSach=?",new String[]{id});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int updateSach(Sach sach){
        ContentValues values = new ContentValues();
        //values.put("MaSach",sach.getMaSach());
        values.put("MaTheLoai",sach.getMaTheLoai());
        values.put("TacGia",sach.getTacGia());
        values.put("NXB",sach.getNXB());
        values.put("giaBia",sach.getGiaBia());
        values.put("soLuong",sach.getSoLuong());
        try{
            if (db.update(TABLE_SACH,values,"MaSach=?",new String[]{sach.getMaSach()})<0) {
                return -1;
            }
        }
        catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }
        return 1;
    }
}
