package com.example.duanmau.adapter;

import android.content.ContentValues;
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

import com.example.duanmau.R;
import com.example.duanmau.SachActivity;
import com.example.duanmau.TheLoaiActivity;
import com.example.duanmau.dao.SachDAO;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.TheLoai;

import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {

    Context context;
    List<Sach> sachList;

    public SachAdapter(Context context, List<Sach> sachList) {
        this.context = context;
        this.sachList = sachList;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_sach,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Sach sach = sachList.get(i);
        viewHolder.tvMaSach.setText(sach.getMaSach());
        viewHolder.tvGiaBia.setText(sach.getGiaBia()+"");

        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sach sach1 = sachList.get(i);

                SachDAO sachDAO = new SachDAO(context);
                if (sachDAO.deleteSach(sach1.getMaSach()) == 1){
                    sachList.remove(sach1);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Update dữ liệu", Toast.LENGTH_SHORT).show();
                Sach sach1 = sachList.get(i);
                Intent intent = new Intent(context, SachActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("MaSach",sach1.getMaSach());
                bundle.putString("MaTheLoai",sach1.getMaTheLoai());
                bundle.putString("TacGia",sach1.getTacGia());
                bundle.putString("NXB",sach1.getNXB());
                bundle.putFloat("giaBia",sach1.getGiaBia());
                bundle.putInt("soLuong", sach1.getSoLuong());
                intent.putExtra("bun",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        ImageView ivDelete;
        TextView tvMaSach;
        TextView tvGiaBia;



        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvMaSach = (TextView) itemView.findViewById(R.id.tvMaSach);
            tvGiaBia = (TextView) itemView.findViewById(R.id.tvGiaBia);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);
        }
    }
}
