package com.example.doanchuyennganh_nhom5.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh_nhom5.R;

public class GopY extends AppCompatActivity {
    Button btn_Hotrokhachhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop_y);

        AnhXa();
    }

    private void AnhXa() {
        btn_Hotrokhachhang = findViewById(R.id.btn_hotrokhachhang);
        btn_Hotrokhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GopY.this, HoTroKhachHang.class));
            }
        });
    }
}