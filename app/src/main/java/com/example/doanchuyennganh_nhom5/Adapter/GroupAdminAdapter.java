package com.example.doanchuyennganh_nhom5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.Activity.Dangnhap;
import com.example.doanchuyennganh_nhom5.Admin.MainActivity;
import com.example.doanchuyennganh_nhom5.Admin.QL_Taikhoan;
import com.example.doanchuyennganh_nhom5.Admin.QL_Video;
import com.example.doanchuyennganh_nhom5.R;

import java.util.List;

public class GroupAdminAdapter extends RecyclerView.Adapter<GroupAdminAdapter.ViewHolder> {
    Context context;
    List<Integer> listKey;

    public GroupAdminAdapter(Context context, List<Integer> listKey) {
        this.context = context;
        this.listKey = listKey;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_groupadmin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int key = listKey.get(position);

        switch (key){
            case MainActivity.Key_QLTK:
                holder.txtV_groupadmin.setText("Quản lý tài khoản");
                break;
            case MainActivity.Key_QLVIDEO:
                holder.txtV_groupadmin.setText("Quản lý Video");
                break;
            case MainActivity.Key_LOGOUT:
                holder.txtV_groupadmin.setText("Đăng Xuất");
                break;
        }

        holder.cardV_groupadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (key){
                    case MainActivity.Key_QLTK:
                        context.startActivity(new Intent(context, QL_Taikhoan.class));
                        break;
                    case MainActivity.Key_QLVIDEO:
                        context.startActivity(new Intent(context, QL_Video.class));
                        break;
                    case MainActivity.Key_LOGOUT:
                        context.startActivity(new Intent(context, Dangnhap.class));
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listKey != null){
            return listKey.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgV_groupadmin;
        TextView txtV_groupadmin;
        CardView cardV_groupadmin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_groupadmin = itemView.findViewById(R.id.imgV_groupadmin);
            txtV_groupadmin = itemView.findViewById(R.id.txtV_groupadmin);
            cardV_groupadmin = itemView.findViewById(R.id.cardV_groupadmin);
        }
    }
}
