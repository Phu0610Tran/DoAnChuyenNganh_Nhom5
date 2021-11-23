package com.example.doanchuyennganh_nhom5.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayvideoActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    String id = "";
    DAO dao;
    TextView txt_tieude_playvideo,rmtxtV_Xemthem,txt_luuvideo_playvideo;
    LinearLayout layout1,layout2,layoutLuu_playvideo;
    int REQUEST_VIDEO =12;
    ImageView img_like_playvideo,img_dislike_playvideo,img_luuVideo_playvideo;
    YouTubePlayerView youTubePlayerView;
    boolean isdisLike, isLike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        Anhxa();

        dao = new DAO(PlayvideoActivity.this);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID_VIDEO");
        youTubePlayerView.initialize(Home.KEY_API,this);
        GetData();
        Luuvideo();
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLike == false){
                    img_like_playvideo.setColorFilter(getResources().getColor(R.color.colorPrimary));
                }else {
                    img_like_playvideo.setColorFilter(getResources().getColor(R.color.black));
                }
                isLike = !isLike;

            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isdisLike == false){
                    img_dislike_playvideo.setColorFilter(getResources().getColor(R.color.colorPrimary));
                }else {
                    img_dislike_playvideo.setColorFilter(getResources().getColor(R.color.black));
                }
                isdisLike = !isdisLike;
            }
        });
        layoutLuu_playvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Home.taiKhoan.getIDTK()==-1){
                    Toast.makeText(PlayvideoActivity.this, " Bạn chưa đăng nhập ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (dao.isTonTaiLuuVideo(Home.taiKhoan.getIDTK(), id)){
                        img_luuVideo_playvideo.setImageResource(R.drawable.ic_bookmark_border_black_48dp);
                        dao.XoaLuuvideo(Home.taiKhoan.getIDTK(), id);
                        txt_luuvideo_playvideo.setText("Lưu");
                        Toast.makeText(PlayvideoActivity.this, "Đã xóa khỏi kho video", Toast.LENGTH_SHORT).show();

                    } else {
                        img_luuVideo_playvideo.setImageResource(R.drawable.ic_bookmark_black_48dp);
                        dao.Luuvideo(Home.taiKhoan.getIDTK(), id);
                        txt_luuvideo_playvideo.setText("Đã Lưu");
                        Toast.makeText(PlayvideoActivity.this, "Đã lưu vào kho video", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void Luuvideo() {
        if (dao.isTonTaiLuuVideo(Home.taiKhoan.getIDTK(), id) == false){
            img_luuVideo_playvideo.setImageResource(R.drawable.ic_bookmark_border_black_48dp);
            txt_luuvideo_playvideo.setText("Lưu");
        } else {
            img_luuVideo_playvideo.setImageResource(R.drawable.ic_bookmark_black_48dp);
            txt_luuvideo_playvideo.setText("Đã lưu");
        }
    }

    private void Anhxa() {
        img_like_playvideo = (ImageView) findViewById(R.id.img_like_playvideo);
        img_dislike_playvideo = (ImageView) findViewById(R.id.img_dislike_playvideo);
        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout)findViewById(R.id.layout2);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.myvideo);
        txt_tieude_playvideo = (TextView) findViewById(R.id.txt_tieude_playvideo);
        rmtxtV_Xemthem = (TextView) findViewById(R.id.rmtxtV_Xemthem);
        layoutLuu_playvideo = (LinearLayout) findViewById(R.id.layoutLuu_playvideo);
        img_luuVideo_playvideo = (ImageView) findViewById(R.id.img_luuVideo_playvideo);
        txt_luuvideo_playvideo = (TextView) findViewById(R.id.txt_luuvideo_playvideo);
    }

    private void GetData() {

        Video video = dao.thongtinvideo(id);
        txt_tieude_playvideo.setText(video.getTieuDeVD());
        rmtxtV_Xemthem.setText(video.getNoiDungVD());

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(id);
//        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(PlayvideoActivity.this,REQUEST_VIDEO);
        }else
        {
            Toast.makeText(this,"loi",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_VIDEO)
        {
            youTubePlayerView.initialize(Home.KEY_API,PlayvideoActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}