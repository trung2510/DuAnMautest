package com.example.duanmau.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.HoaDonChiTietActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.HoaDonChiTietDAO;
import com.example.duanmau.model.HoaDonChiTiet;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietAdapter extends RecyclerView.Adapter<HoaDonChiTietAdapter.ViewHoler> {

    private final ArrayList<HoaDonChiTiet> billdetails;
    private final ArrayList<Sach> bookArrayList;
    private final HoaDonChiTietActivity context;
    private final int i;

    public HoaDonChiTietAdapter(ArrayList<HoaDonChiTiet> billdetails, ArrayList<Sach> bookArrayList, HoaDonChiTietActivity context, int i) {
        this.billdetails = billdetails;
        this.bookArrayList = bookArrayList;
        this.context = context;
        this.i = i;
    }

    @NonNull
    @Override
    public HoaDonChiTietAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardview_billdetail,viewGroup,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonChiTietAdapter.ViewHoler viewHoler, final int position) {

        final HoaDonChiTiet bill=billdetails.get(position);
        int pos=-1;
        for (int i = 0; i < billdetails .size(); i++){
            if (!bookArrayList.isEmpty()){
                Sach book=bookArrayList.get(i);
                if (book.getMaSach().equalsIgnoreCase(bill.getIdbook())){
                    pos = i;
                    break;
                }
            }
            else {
                viewHoler.txtSoLuong.setText("Số lượng:?");
                viewHoler.txtMaSach.setText("Mã sách:Không tồn tại");
                viewHoler.txtGiaBia.setText("Giá bìa:?");
                viewHoler.txtYhanhTien.setText("Thành tiền:?");
                return;
            }
        }

        Sach book=bookArrayList.get(pos);



        viewHoler.txtSoLuong.setText("Số lượng:" + bill.getSoluong());
        viewHoler.txtMaSach.setText("Mã sách:" + bill.getIdbook());
        viewHoler.txtGiaBia.setText("Giá bìa:" + book.getGiaBia() + "\tVND");
        viewHoler.txtYhanhTien.setText("Thành tiền:" + bill.getSoluong() * book.getGiaBia() + "\tVND");


        viewHoler.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return billdetails.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        public final TextView txtMaSach;
        public final TextView txtSoLuong;
        public final TextView txtGiaBia;
        public final TextView txtYhanhTien;
        public final ImageView imageView;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

                txtMaSach = itemView.findViewById(R.id.txtMaSach);
                txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
                txtGiaBia = itemView.findViewById(R.id.txtGiaBia);
                txtYhanhTien = itemView.findViewById(R.id.txtYhanhTien);
                imageView=itemView.findViewById(R.id.imgDelete);
            }
        }
    }
