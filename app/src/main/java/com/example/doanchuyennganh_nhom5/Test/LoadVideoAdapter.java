package com.example.doanchuyennganh_nhom5.Test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.d.Video;

import java.util.List;

public class LoadVideoAdapter extends RecyclerView.Adapter<LoadVideoAdapter.LoadvieoHodler> {
    List<Video> mListVideo;
    Context context;

    public LoadVideoAdapter(Context context,List<Video> ListVideo) {
        this.mListVideo = ListVideo;
        this.context = context;
    }

    @NonNull
    @Override
    public LoadvieoHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_video,parent,false);


        return new LoadvieoHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadvieoHodler holder, int position) {
        Video mvideo = mListVideo.get(position);

        if(mvideo == null){
            return;
        }

//        byte[] hinhSP = mvideo.getHinh();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhSP,0,hinhSP.length);
//
//        holder.txt_Tieude.setText(mvideo.getTieude());
//        holder.img_Hinh.setImageBitmap(bitmap);

//        holder.layout_video.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Intent intent = new Intent(context, )
//            }
//        });
    }


    @Override
    public int getItemCount() {
        if(mListVideo != null){
            return mListVideo.size();
        }
        return 0;
    }

    public class LoadvieoHodler extends  RecyclerView.ViewHolder{
        private TextView txt_Tieude;
        private ImageView img_Hinh;
        private ConstraintLayout layout_video;

        public LoadvieoHodler(@NonNull View itemView) {
            super(itemView);

//            img_Hinh = itemView.findViewById(R.id.img_Hinhvideo);
//            txt_Tieude = itemView.findViewById(R.id.txt_Tieudevideo);
//            layout_video = itemView.findViewById(R.id.layout_video);
        }
    }
}
