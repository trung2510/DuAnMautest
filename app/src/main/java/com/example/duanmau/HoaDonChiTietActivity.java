package com.example.duanmau;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.adapter.HoaDonChiTietAdapter;
import com.example.duanmau.dao.HoaDonChiTietDAO;
import com.example.duanmau.dao.SachDAO;
import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.HoaDonChiTiet;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {

    EditText edtIDBill;
    Spinner spMaSach;
    EditText edtSoLuong;
    Button btnAdd;
    Button btnThanhToan;
    TextView tvThanhTien;
    RecyclerView recyclerviewBilldetail;
    List<String> stringList = new ArrayList<>();
    private HoaDonChiTietDAO hoaDonChiTietDAO;
    private int i;
    private int vitri;
    private String mahoadon2;
    private  String mhoadon1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        anhXa();


        SachDAO sachDAO = new SachDAO(getApplicationContext());
        stringList = sachDAO.getMaSach();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stringList);
        spMaSach.setAdapter(arrayAdapter);


        Intent intent = getIntent();
        try {
            if (intent != null) {
                Bundle bundle = intent.getBundleExtra("bun");
                edtIDBill.setText(bundle.getString("maHoaDon"));
                edtIDBill.setEnabled(false);
            }
        } catch (Exception e) {

        }


    }

    private void anhXa() {
        edtIDBill = (EditText) findViewById(R.id.edtIDBill);
        spMaSach = (Spinner) findViewById(R.id.spMaSach);
        edtSoLuong = (EditText) findViewById(R.id.edtSoLuong);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien);
        recyclerviewBilldetail = (RecyclerView) findViewById(R.id.recyclerviewBilldetail);
        recyclerviewBilldetail = findViewById(R.id.recyclerviewBilldetail);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
//
//                if (hoaDonChiTietDAO.insertHoaDonChiTiet() > 0) {
//                    Toast.makeText(HoaDonChiTietActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(HoaDonChiTietActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }



}
