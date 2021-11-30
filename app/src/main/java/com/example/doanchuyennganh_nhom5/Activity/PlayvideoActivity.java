package com.example.doanchuyennganh_nhom5.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.Adapter.BinhLuanAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.BinhLuan;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class PlayvideoActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    String id = "";
    DAO dao;
    EditText edt_noidungbl_playvideo;
    TextView txt_tieude_playvideo,rmtxtV_Xemthem,txt_luuvideo_playvideo,txt_luotlike_playvide,txt_luotdislike_playvide;
    TextView txt_luotxem_playvide;
    LinearLayout layout1,layout2,layoutLuu_playvideo;
    NestedScrollView scrollV;
    int REQUEST_VIDEO =12;
    ImageView img_like_playvideo,img_dislike_playvideo,img_luuVideo_playvideo;
    YouTubePlayerView youTubePlayerView;
    boolean isdisLike, isLike;
    RecyclerView recV_chatbox;
    ArrayList<BinhLuan> listBL;
    TextView txtNoiDungBL;
    Button btn_GuiBl;

    BinhLuanAdapter binhLuanAdapter;

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
        loadlike();
        loadluotxem();

        edt_noidungbl_playvideo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                scrollV.scrollTo(0,edt_noidungbl_playvideo.getScrollY());
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(isLike == true){
                        img_like_playvideo.setImageResource(R.drawable.ic_thumb_up_black_48dp);
                        dao.xoathem(id,Home.taiKhoan.getIDTK(), 1);
                    }else {
                        if(isdisLike == true){
                            img_dislike_playvideo.setImageResource(R.drawable.ic_thumb_down_off_alt_black_48dp);
                            dao.xoathem(id,Home.taiKhoan.getIDTK(), 2);
                            isdisLike = !isdisLike;
                        }
                        img_like_playvideo.setImageResource(R.drawable.like);
                        dao.them(id,Home.taiKhoan.getIDTK(), 1);
                    }
                loadluotxem();
                isLike = !isLike;
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(isdisLike == true){

                        img_dislike_playvideo.setImageResource(R.drawable.ic_thumb_down_off_alt_black_48dp);
                        dao.xoathem(id,Home.taiKhoan.getIDTK(), 2);
                    }else {
                        if(isLike == true){
                            img_like_playvideo.setImageResource(R.drawable.ic_thumb_up_black_48dp);
                            dao.xoathem(id,Home.taiKhoan.getIDTK(), 1);
                            isLike = !isLike;
                        }
                        img_dislike_playvideo.setImageResource(R.drawable.dislike);
                        dao.them(id,Home.taiKhoan.getIDTK(), 2);
                    }
                loadluotxem();
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

        btn_GuiBl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Home.taiKhoan.getIDTK() == -1) {
                    Toast.makeText(PlayvideoActivity.this, "Bạn chưa đăng nhập !", Toast.LENGTH_SHORT).show();
                } else {
                    if (txtNoiDungBL.getText().length() > 0); {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date());
                        dao.ThemBL(Home.taiKhoan.getIDTK(),id,txtNoiDungBL.getText().toString(),currentDateandTime);

                        listBL.add(0, new BinhLuan(
                                Home.taiKhoan.getIDTK(),Home.taiKhoan.getHinhTK(),
                                txtNoiDungBL.getText().toString(),currentDateandTime
                        ));
                        Log.e("Tag",String.valueOf(listBL.size()));
                        binhLuanAdapter.notifyItemInserted(0);
                        txtNoiDungBL.setText("");
                    }
                }
            }
        });
    }
    private void loadluotxem(){
        txt_luotxem_playvide.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(dao.Soluotxem(id)) + " Lượt xem"));
        txt_luotlike_playvide.setText(String.valueOf(dao.Solike(id,1)));
        txt_luotdislike_playvide.setText(String.valueOf(dao.Solike(id,2)));
    }
    private void loadlike() {
        img_like_playvideo.setImageResource(R.drawable.ic_thumb_up_black_48dp);
        img_dislike_playvideo.setImageResource(R.drawable.ic_thumb_down_off_alt_black_48dp);


        if (dao.isDaLike(id,Home.taiKhoan.getIDTK()) == 1){
            isLike = true;
            isdisLike = false;
            img_like_playvideo.setImageResource(R.drawable.like);
        } else if(dao.isDaLike(id,Home.taiKhoan.getIDTK()) == 2){
            isdisLike = true;
            isLike = false;
            img_dislike_playvideo.setImageResource(R.drawable.dislike);
        }
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
    @Override
    protected void onStart() {
        listBL = dao.LayBinhLuan(id);
        Log.e("BL",String.valueOf(listBL.size()));
        binhLuanAdapter = new BinhLuanAdapter(listBL);
        //Load();


        recV_chatbox.setAdapter(binhLuanAdapter);
        recV_chatbox.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        super.onStart();
    }
    private void Anhxa() {
        scrollV = (NestedScrollView) findViewById(R.id.scrollV);
        edt_noidungbl_playvideo = (EditText) findViewById(R.id.edt_noidungbl_playvideo);
        txt_luotxem_playvide = (TextView) findViewById(R.id.txt_luotxem_playvide);
        txt_luotlike_playvide = (TextView) findViewById(R.id.txt_luotlike_playvide);
        txt_luotdislike_playvide = (TextView) findViewById(R.id.txt_luotdislike_playvide);
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
        recV_chatbox = (RecyclerView) findViewById(R.id.rec_Binhluan_playvideo);
        btn_GuiBl = (Button) findViewById(R.id.btn_GuiBl);
        txtNoiDungBL = (TextView) findViewById(R.id.edt_noidungbl_playvideo);
    }
    private void GetData() {
        Video video = dao.thongtinvideo(id);
        txt_tieude_playvideo.setText(video.getTieuDeVD());
        rmtxtV_Xemthem.setText(video.getNoiDungVD());
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(id);
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