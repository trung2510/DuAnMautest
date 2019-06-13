package com.example.duanmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.duanmau.adapter.SachAdapter;
import com.example.duanmau.dao.SachDAO;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListSachActivity extends AppCompatActivity {

    RecyclerView rcSach;
    SachAdapter sachAdapter;
    SachDAO sachDAO;
    List<Sach> sachList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sach);

        rcSach = findViewById(R.id.rcSach);

        sachDAO = new SachDAO(this);

        sachList = sachDAO.getAllSach();

        sachAdapter = new SachAdapter(ListSachActivity.this,sachList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListSachActivity.this,LinearLayoutManager.VERTICAL,false);

        rcSach.setLayoutManager(linearLayoutManager);

        rcSach.setAdapter(sachAdapter);
    }

    public void themSach(View view) {
        startActivity(new Intent(ListSachActivity.this,SachActivity.class));
    }
}
