package com.example.doanchuyennganh_nhom5.Adapter;

import android.content.Context;
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
import com.example.doanchuyennganh_nhom5.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QLVideoAdapter extends RecyclerView.Adapter<QLVideoAdapter.Viewholder> {
    ArrayList<Video> listVideo;
    Context context;
    public static int position;

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public QLVideoAdapter( Context context,ArrayList<Video> listVideo) {
        this.listVideo = listVideo;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_qlvideo,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Video video = listVideo.get(position);

        Picasso.with(context).load(video.getHinhVD())
                .placeholder(R.drawable.logodt)
                .error(R.drawable.logodt).into(holder.imgV_Hinh_qlVideo);
        holder.txtV_Ten_qlVideo.setText(video.getTieuDeVD());

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
        if(listVideo != null){
            return listVideo.size();
        }
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        ImageView imgV_Hinh_qlVideo;
        TextView txtV_Ten_qlVideo;
        CardView viewholder_qlvideo;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtV_Ten_qlVideo = itemView.findViewById(R.id.txtV_Ten_qlTaikhoan);
            imgV_Hinh_qlVideo = itemView.findViewById(R.id.imgV_Hinh_qlTaikhoan);
            viewholder_qlvideo = itemView.findViewById(R.id.viewholder_qlvideo);

            itemView.setOnCreateContextMenuListener(this);

            viewholder_qlvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewholder_qlvideo.showContextMenu();
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
