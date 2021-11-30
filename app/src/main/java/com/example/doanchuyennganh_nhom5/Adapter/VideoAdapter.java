package com.example.doanchuyennganh_nhom5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.Activity.PlayvideoActivity;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    private ArrayList<Video> ListVideo;
    private Context context;
    View v;
    DAO dao;
    public VideoAdapter() {
    }

    public VideoAdapter(Context context, ArrayList<Video> listVideo) {
        this.ListVideo = listVideo;
        this.context = context;

    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_video, null);


        return new VideoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        Video video = ListVideo.get(position);

//        byte[] hinhVideo = video.getHinh_VIDEO();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhVideo,0,hinhVideo.length);

        holder.txt_TieuDeVideo.setText(video.getTieuDeVD());
        //holder.img_HinhVideo.setImageURI(video.getHinh_VIDEO());


//        for (int i=0;i < ListVideo.size();i++){
//            Video vo = ListVideo.get(i);
//            if(dao.isTonTaiVideo(vo.getIDVD()) == false){
//
//                dao.TaoVideo(vo.getIDVD(), vo.getHinhVD(), vo.getTHELOAI(), vo.getTieuDeVD().replaceAll("'","''"), vo.getNoiDungVD().replaceAll("'","''"), vo.getTacGia());
//            }
//        }
        Picasso.with(context).load(video.getHinhVD())
                .placeholder(R.drawable.logodt)
                .error(R.drawable.logodt).into(holder.img_HinhVideo);


        Log.e("Tdm",video.getIDVD());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlayvideoActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("Video", video);
                intent.putExtra("ID_VIDEO",video.getIDVD());
                dao.luotxem(video.getIDVD());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(ListVideo != null){
            return ListVideo.size();
        }
        return 0;
    }

    public ArrayList<Video> getListVideo() {
        return ListVideo;
    }

    public void setListVideo(ArrayList<Video> listVideo) {
        ListVideo = listVideo;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        dao = new DAO(context);
    }

    public class VideoHolder extends RecyclerView.ViewHolder{

        private TextView txt_TieuDeVideo;
        private ImageView img_HinhVideo;
        private ConstraintLayout layout;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layoutVideo);
            txt_TieuDeVideo = itemView.findViewById(R.id.txt_TieuDeVideo);
            img_HinhVideo = itemView.findViewById(R.id.img_HinhVideo);

        }
    }
}
