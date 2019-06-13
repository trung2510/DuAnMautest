package com.example.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.NguoiDungActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.NguoiDungDAO;
import com.example.duanmau.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends ArrayAdapter {

    Context context;
    int resource;
    List<NguoiDung> nguoiDungList;
    NguoiDungDAO nguoiDungDAO;

    public NguoiDungAdapter(Context context, int resource, List<NguoiDung> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.nguoiDungList = objects;
        this.nguoiDungDAO = new NguoiDungDAO(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nguoi_dung,parent,false);
            holder = new ViewHolder();
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        final NguoiDung nguoiDung = nguoiDungList.get(position);
        holder.tvName.setText(nguoiDung.getUserName());
        holder.tvPhone.setText(nguoiDung.getPhone());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nguoiDungDAO.deleteNguoiDung(nguoiDung.getUserName());

                nguoiDungList.remove(nguoiDungList.get(position));
                notifyDataSetChanged();
                Toast.makeText(context, "Delete Complete!", Toast.LENGTH_SHORT).show();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Update Người Dùng", Toast.LENGTH_SHORT).show();

                NguoiDung nguoiDung1 = nguoiDungList.get(position);

                Intent intent = new Intent(context, NguoiDungActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("userName",nguoiDung1.getUserName());
                bundle.putString("password",nguoiDung1.getPassword());
                bundle.putString("phone",nguoiDung1.getPhone());
                bundle.putString("hoTen",nguoiDung1.getHoTen());
                intent.putExtra("bun",bundle);

                context.startActivity(intent);

            }
        });


        return convertView;
    }

    public class ViewHolder{
        private ImageView ivIcon;
        private TextView tvName;
        private TextView tvPhone;
        private ImageView ivDelete;

    }
}
