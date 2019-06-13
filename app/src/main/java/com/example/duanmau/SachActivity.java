package com.example.duanmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau.dao.SachDAO;
import com.example.duanmau.dao.TheLoaiDAO;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {

    private EditText edtIdBook;
    private Spinner spnSpinner;
    private ImageView imgThem;
    private EditText edtTacGia;
    private EditText edtNXB;
    private EditText edtPrice;
    private EditText edtSoLuong;
    private Button btnSave;
    private Button btnCancel;
    private Button btnShow;

    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    List<String> stringList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        AnhXa();
        AddSach();

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SachActivity.this,ListSachActivity.class));
            }
        });

        theLoaiDAO = new TheLoaiDAO(getApplicationContext());
        stringList = theLoaiDAO.getMaTheLoai();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,stringList);
        spnSpinner.setAdapter(arrayAdapter);


        try{
            Intent intent = getIntent();
            if (intent != null){
                Bundle bundle = intent.getBundleExtra("bun");
                edtIdBook.setText(bundle.getString("MaSach"));
                edtTacGia.setText(bundle.getString("TacGia"));
                edtNXB.setText(bundle.getString("NXB"));
                edtPrice.setText(bundle.getFloat("giaBia") + "");
                edtSoLuong.setText(bundle.getInt("soLuong")+ "");
            }}catch (Exception e){

        }
    }

    public void UpdateSach(View view) {

        sachDAO = new SachDAO(SachActivity.this);

        String id = edtIdBook.getText().toString();
        String tg = edtTacGia.getText().toString();
        String nxb = edtNXB.getText().toString();
        float gia = Float.parseFloat(edtPrice.getText().toString());
        int sl = Integer.parseInt(edtSoLuong.getText().toString());

        if (id.equals("")) {
            edtIdBook.setError("Nhập id");
        }
        if (tg.equals("")) {
            edtTacGia.setError("Nhập tác giả");
        }
        if (nxb.equals("")) {
            edtTacGia.setError("Nhập nhà xuất bản");
        }

        Sach sach = new Sach(id, tg, nxb, gia, sl);
        if (sachDAO.updateSach(sach) == 1) {
            Toast.makeText(SachActivity.this, "Update thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SachActivity.this, "Update thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void AnhXa() {
        edtIdBook = (EditText) findViewById(R.id.edtIdBook);
        spnSpinner = (Spinner) findViewById(R.id.spnSpinner);
        imgThem = (ImageView) findViewById(R.id.imgThem);
        edtTacGia = (EditText) findViewById(R.id.edtTacGia);
        edtNXB = (EditText) findViewById(R.id.edtNXB);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtSoLuong = (EditText) findViewById(R.id.edtSoLuong);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnShow = (Button) findViewById(R.id.btnShow);
    }

    public void AddSach() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachDAO = new SachDAO(SachActivity.this);

                String id = edtIdBook.getText().toString();
                String tg = edtTacGia.getText().toString();
                String nxb = edtNXB.getText().toString();
                float gia = Float.parseFloat(edtPrice.getText().toString());
                int sl = Integer.parseInt(edtSoLuong.getText().toString());

                if(id.equals("")){
                    edtIdBook.setError("Nhập id");
                }
                if(tg.equals("")){
                    edtTacGia.setError("Nhập tác giả");
                }
                if(nxb.equals("")){
                    edtTacGia.setError("Nhập nhà xuất bản");
                }

                Sach sach = new Sach(id,tg,nxb,gia,sl);
                if (sachDAO.insertSach(sach) == 1) {
                    Toast.makeText(SachActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SachActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
