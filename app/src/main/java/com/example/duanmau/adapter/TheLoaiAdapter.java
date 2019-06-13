package com.example.duanmau.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.ListTheLoaiActivity;
import com.example.duanmau.R;
import com.example.duanmau.TheLoaiActivity;
import com.example.duanmau.dao.NguoiDungDAO;
import com.example.duanmau.dao.TheLoaiDAO;
import com.example.duanmau.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ItemHolder> {

    TheLoaiDAO theLoaiDAO;
    Context context;
    List<TheLoai> arrTheLoai;

    public TheLoaiAdapter(Context context, List<TheLoai> arrTheLoai) {
        this.context = context;
        this.arrTheLoai = arrTheLoai;

    }

    @NonNull
    @Override
    public TheLoaiAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_the_loai,viewGroup,false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiAdapter.ItemHolder itemHolder, final int i) {
        TheLoai theLoai = arrTheLoai.get(i);
        itemHolder.tvMaTheLoai.setText(theLoai.getMaTheLoai());
        itemHolder.tvTenTheLoai.setText(theLoai.getTenTheLoai());

        itemHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xoa trong database(bat buoc phai xoa trong database trươc khi xoa tren recyclerview neu khong se bi crash app);
                theLoaiDAO = new TheLoaiDAO(context);
                theLoaiDAO.deleteTheLoai(arrTheLoai.get(i).getMaTheLoai());

                //Xoa tren recyclerview
                arrTheLoai.remove(arrTheLoai.get(i));

                notifyDataSetChanged();


            }
        });

        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Update dữ liệu", Toast.LENGTH_SHORT).show();
                TheLoai theLoai1 = arrTheLoai.get(i);
                Intent intent = new Intent(context, TheLoaiActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("maTheLoai",theLoai1.getMaTheLoai());
                bundle.putString("tenTheLoai",theLoai1.getTenTheLoai());
                bundle.putString("moTa",theLoai1.getMoTa());
                bundle.putString("viTri",theLoai1.getViTri());
                intent.putExtra("bun",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrTheLoai == null) return 0;
        return arrTheLoai.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        public ImageView ivIcon;
        public TextView tvMaTheLoai;
        public TextView tvTenTheLoai;
        public ImageView ivDelete;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvMaTheLoai = (TextView) itemView.findViewById(R.id.tvMaTheLoai);
            tvTenTheLoai = (TextView) itemView.findViewById(R.id.tvTenTheLoai);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);

        }
    }
}
