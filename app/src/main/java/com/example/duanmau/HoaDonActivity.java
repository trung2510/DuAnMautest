package com.example.duanmau;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.adapter.HoaDonAdapter;
import com.example.duanmau.adapter.TheLoaiAdapter;
import com.example.duanmau.dao.HoaDonDAO;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity {
    EditText edtMaHoaDon;
    EditText edtNgayMua;
    Button btnAdd;
    RecyclerView rcViewHoaDon;
    HoaDonDAO hoaDonDAO;
    List<HoaDon> hoaDonList;
    HoaDonAdapter hoaDonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        rcViewHoaDon = (RecyclerView) findViewById(R.id.rcViewHoaDon);
        edtMaHoaDon = (EditText) findViewById(R.id.edtMaHoaDon);
        edtNgayMua = (EditText) findViewById(R.id.edtNgayMua);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);
        hoaDonList = hoaDonDAO.getAllHoaDon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HoaDonActivity.this,LinearLayoutManager.VERTICAL,false);

        hoaDonAdapter = new HoaDonAdapter(HoaDonActivity.this,hoaDonList);

        rcViewHoaDon.setLayoutManager(linearLayoutManager);

        rcViewHoaDon.setAdapter(hoaDonAdapter);


    }

    public void ThemHoaDon(View view) {

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_bill);

        edtMaHoaDon = dialog.findViewById(R.id.edtMaHoaDon);
        edtNgayMua =  dialog.findViewById(R.id.edtNgayMua);
        btnAdd = dialog.findViewById(R.id.btnAdd);




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mahoadon=edtMaHoaDon.getText().toString().trim();
                String ngaymua=edtNgayMua.getText().toString().trim();
                if (mahoadon.equals("")){
                    edtMaHoaDon.setError("Chưa có gì");
                    return;
                }
                else if (ngaymua.equals("")){
                    edtNgayMua.setError("Chưa có gì");
                    return;
                }
                else {
                    HoaDon hoaDon = new HoaDon(mahoadon,ngaymua);
                    hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);
                    if (hoaDonDAO.insertHoaDon(hoaDon) > 0) {
                        Toast.makeText(HoaDonActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                        hoaDonList = hoaDonDAO.getAllHoaDon();

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HoaDonActivity.this,LinearLayoutManager.VERTICAL,false);

                        hoaDonAdapter = new HoaDonAdapter(HoaDonActivity.this,hoaDonList);

                        rcViewHoaDon.setLayoutManager(linearLayoutManager);

                        rcViewHoaDon.setAdapter(hoaDonAdapter);

                        dialog.dismiss();
                    }
                    else {
                        Toast.makeText(HoaDonActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        dialog.show();


    }
}
