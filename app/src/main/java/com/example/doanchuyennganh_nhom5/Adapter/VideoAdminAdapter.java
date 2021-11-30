package com.example.doanchuyennganh_nhom5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.Activity.PlayvideoActivity;
import com.example.doanchuyennganh_nhom5.Admin.Sua_Video_List;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoAdminAdapter extends RecyclerView.Adapter<VideoAdminAdapter.VideoHolder>{

    public static ArrayList<Video> ListVideo;
    private Context context;
    View v;
    DAO dao;
    public VideoAdminAdapter() {
    }

    public VideoAdminAdapter(Context context, ArrayList<Video> listVideo) {
        this.ListVideo = listVideo;
        this.context = context;

    }

    @NonNull
    @Override
    public VideoAdminAdapter.VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_listvideoadmin, null);


        return new VideoAdminAdapter.VideoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdminAdapter.VideoHolder holder, int position) {
        Video video = ListVideo.get(position);
        int vitri = position;
        holder.txt_TieuDeVideo.setText(video.getTieuDeVD());
        Log.e("tieu de ",video.getTieuDeVD() );

        // thêm dữ liệu vào BanPhu
        for (int i=0;i < ListVideo.size();i++){
            Video vo = ListVideo.get(i);
            if(dao.isTonTaiVideoAdmin(vo.getIDVD()) == false){

                dao.TaoVideoAdmin(vo.getIDVD(), vo.getHinhVD(), vo.getTHELOAI(), vo.getTieuDeVD().replaceAll("'","''"), vo.getNoiDungVD().replaceAll("'","''"), vo.getTacGia());
            }
        }
        Picasso.with(context).load(video.getHinhVD())
                .placeholder(R.drawable.logodt)
                .error(R.drawable.logodt).into(holder.img_HinhVideo);



        if(dao.isTonTaiVideo(video.getIDVD())){
            holder.layoutVideo.setBackgroundResource(R.color.xam);
        }

        holder.layoutVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dao.isTonTaiVideo(video.getIDVD()) == false)
                {
                    Intent intent = new Intent(context, Sua_Video_List.class);
                    intent.putExtra("ID_VIDEO",vitri);
                    context.startActivity(intent);
                    Toast.makeText(context, " id = " + video.getIDVD(), Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(context, " Video đã tồn tại " , Toast.LENGTH_SHORT).show();

                }

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
        private ConstraintLayout layoutVideo;


        public VideoHolder(@NonNull View itemView) {
            super(itemView);

            layoutVideo = itemView.findViewById(R.id.layoutVideo);
            txt_TieuDeVideo = itemView.findViewById(R.id.txt_TieuDeVideo);
            img_HinhVideo = itemView.findViewById(R.id.img_HinhVideo);

        }
    }
}
