package com.example.doanchuyennganh_nhom5.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanchuyennganh_nhom5.Activity.CapNhat;
import com.example.doanchuyennganh_nhom5.Activity.Home;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Video;

import java.io.ByteArrayOutputStream;

public class CapNhat_Video extends AppCompatActivity {

    EditText edtMaVideo, edtHinhDaiDien, edtTheLoai, edtTieuDe, edtNoiDung, edtTacGia;
    Button btnCapNhatVideo, btnHuy;
    private boolean isEnabled;
    DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_video);

        AnhXa();
        Intent intent = getIntent();
        String ID = intent.getStringExtra("IDVD");


        if (ID == null){
            return;
        }
        Video video = dao.TT1Video(ID);

        edtMaVideo.setText(ID);
        edtMaVideo.setEnabled(false);
        edtHinhDaiDien.setText(video.getHinhVD());
        edtTheLoai.setText(video.getTHELOAI());
        edtTieuDe.setText(video.getTieuDeVD());
        edtNoiDung.setText(video.getNoiDungVD());
        edtTacGia.setText(video.getTacGia());

    }

    private void enableControl(){
        edtHinhDaiDien.setEnabled(isEnabled);
        edtTheLoai.setEnabled(isEnabled);
        edtTieuDe.setEnabled(isEnabled);
        edtNoiDung.setEnabled(isEnabled);
        edtTacGia.setEnabled(isEnabled);
    }

    private void AnhXa() {
        dao = new DAO(this);
        edtMaVideo = findViewById(R.id.edtMaVideo);
        edtHinhDaiDien = findViewById(R.id.edtHinhDaiDien);
        edtTheLoai = findViewById(R.id.edtTheLoai);
        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        edtTacGia = findViewById(R.id.edtTacGia);
        enableControl();

        btnHuy = findViewById(R.id.btnHuyCapNhatVideo);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnCapNhatVideo = findViewById(R.id.btnCapNhatVideo);
        btnCapNhatVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnabled = !isEnabled;
                enableControl();
                if (isEnabled){
                    btnCapNhatVideo.setText("Lưu");
                }
                else{
                    btnCapNhatVideo.setText("Cập nhật");
                    if (edtHinhDaiDien.getText().length() != 0 && edtTheLoai.getText().length() != 0 && edtTieuDe.getText().length() != 0
                            && edtNoiDung.getText().length() != 0 && edtTacGia.getText().length() !=0) {
                        dao.SuaVideo(edtMaVideo.getText().toString(), edtHinhDaiDien.getText().toString(), edtTheLoai.getText().toString(),
                                edtTieuDe.getText().toString(), edtNoiDung.getText().toString(), edtTacGia.getText().toString());
                        Toast.makeText(CapNhat_Video.this, "Đã lưu !", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(CapNhat_Video.this, "Hãy nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}