package com.example.doanchuyennganh_nhom5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.Activity.PlayvideoActivity;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.viewHolder> {
    Context context;
    ArrayList<Video> videoArrayList;

    public TimKiemAdapter(Context context, ArrayList<Video> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_listvideoadmin, null);

        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimKiemAdapter.viewHolder holder, int position) {
        Video video = videoArrayList.get(position);

        Picasso.with(context).load(video.getHinhVD())
                .placeholder(R.drawable.logodt)
                .error(R.drawable.logodt).into(holder.img_HinhVideo);

        holder.txt_TieuDeVideo.setText(video.getTieuDeVD());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlayvideoActivity.class);
                intent.putExtra("ID_VIDEO",video.getIDVD());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(videoArrayList != null){
            return videoArrayList.size();
        }
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView img_HinhVideo;
        TextView txt_TieuDeVideo;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img_HinhVideo = itemView.findViewById(R.id.img_HinhVideo);
            txt_TieuDeVideo = itemView.findViewById(R.id.txt_TieuDeVideo);
        }
    }
}
