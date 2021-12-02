package com.example.doanchuyennganh_nhom5.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.TaiKhoan;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QLTaikhoanAdapter extends RecyclerView.Adapter<QLTaikhoanAdapter.Viewholder> {
    ArrayList<TaiKhoan> listTaiKhoan;
    Context context;
    public static int position;
    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public QLTaikhoanAdapter( Context context,ArrayList<TaiKhoan> listTaiKhoan) {
        this.listTaiKhoan = listTaiKhoan;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_qltaikhoan,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        TaiKhoan taiKhoan = listTaiKhoan.get(position);

        byte[] hinhAnh = taiKhoan.getHinhTK();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
        holder.imgV_Hinh_qlTaikhoan.setImageBitmap(bitmap);

        holder.txtV_Hovaten_qlTaikhoan.setText(taiKhoan.getHovaTen());

        holder.itemView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(holder.getPosition());
                holder.itemView .performLongClick();
            }
        });
    }



    @Override
    public int getItemCount() {
        if(listTaiKhoan != null){
            return listTaiKhoan.size();
        }
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        ImageView imgV_Hinh_qlTaikhoan;
        TextView txtV_Hovaten_qlTaikhoan;
        CardView viewholder_qltaikhoan;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtV_Hovaten_qlTaikhoan = itemView.findViewById(R.id.txtV_Hovaten_qltaikhoan);
            imgV_Hinh_qlTaikhoan = itemView.findViewById(R.id.imgV_Hinh_qltaikhoan);
            viewholder_qltaikhoan = itemView.findViewById(R.id.viewholder_qltaikhoan);

            itemView.setOnCreateContextMenuListener(this);

            viewholder_qltaikhoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewholder_qltaikhoan.showContextMenu();
                }
            });
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(menu.NONE, R.id.iSua,
                    menu.NONE, "Sửa");
            menu.add(menu.NONE, R.id.iXoa,
                    menu.NONE, "Xóa");
        }
    }
}
