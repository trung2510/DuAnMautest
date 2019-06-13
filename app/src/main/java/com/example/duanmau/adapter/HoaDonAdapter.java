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

import com.example.duanmau.HoaDonChiTietActivity;
import com.example.duanmau.R;
import com.example.duanmau.TheLoaiActivity;
import com.example.duanmau.dao.HoaDonDAO;
import com.example.duanmau.dao.TheLoaiDAO;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.TheLoai;

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {

    Context context;
    List<HoaDon> hoaDonList;
    HoaDonDAO hoaDonDAO;

    public HoaDonAdapter(Context context, List<HoaDon> hoaDonList) {
        this.context = context;
        this.hoaDonList = hoaDonList;
    }

    @NonNull
    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_bill,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.ViewHolder viewHolder, final int i) {
       HoaDon hoaDon = hoaDonList.get(i);
        viewHolder.edtId.setText(hoaDon.getMaHoaDon());
        viewHolder.edtDate.setText(hoaDon.getNgayMua());

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xoa trong database(bat buoc phai xoa trong database trươc khi xoa tren recyclerview neu khong se bi crash app);
                hoaDonDAO = new HoaDonDAO(context);
                hoaDonDAO.deleteHoaDon(hoaDonList.get(i).getMaHoaDon());

                //Xoa tren recyclerview
                hoaDonList.remove(hoaDonList.get(i));

                notifyDataSetChanged();

            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, HoaDonChiTietActivity.class);
                Bundle bundle = new Bundle();
                HoaDon hoaDon1 = hoaDonList.get(i);
                bundle.putString("maHoaDon",hoaDon1.getMaHoaDon());
                intent.putExtra("bun",bundle);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView edtId;
        TextView edtDate;
        ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = (ImageView) itemView.findViewById(R.id.imgView);
            edtId = (TextView) itemView.findViewById(R.id.edtId);
            edtDate = (TextView) itemView.findViewById(R.id.edtDate);
            imgDelete = (ImageView) itemView.findViewById(R.id.imgDelete);

        }
    }
}
