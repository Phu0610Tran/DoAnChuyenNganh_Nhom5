package com.example.doanchuyennganh_nhom5.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh_nhom5.R;
public class GopY extends AppCompatActivity {
    Button btn_Hotrokhachhang,btn_guigopy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop_y);

        AnhXa();
        SuKien();
    }

    private void SuKien() {
        btn_Hotrokhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(GopY.this, HoTroKhachHang.class)); }
        });

        btn_guigopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { Toast.makeText(GopY.this, "Gửi thành công", Toast.LENGTH_SHORT).show(); }
        });
    }

    private void AnhXa() {
        btn_guigopy = findViewById(R.id.btn_guigopy);
        btn_Hotrokhachhang = findViewById(R.id.btn_hotrokhachhang);
    }
}