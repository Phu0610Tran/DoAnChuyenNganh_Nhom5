package com.example.doanchuyennganh_nhom5.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.doanchuyennganh_nhom5.Adapter.DanhMucAdapterAdmin;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.DanhMuc;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ThemListVideo extends AppCompatActivity {
    ImageView imageView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout contentView;
    RecyclerView rec_Chung;
    ArrayList<DanhMuc> ListCate;
    DAO dao;
    String keylist;
    DanhMucAdapterAdmin categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Anhxa();

        Intent intent = getIntent();
        keylist = intent.getStringExtra("Listvideo");
        rec_Chung =findViewById(R.id.rec_Chung);
        ListCate = new ArrayList<>();
    }


    @Override
    protected void onStart() {

        ListCate.add(new DanhMuc("Danh sách video",keylist));
        rec_Chung.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        categoryAdapter = new DanhMucAdapterAdmin(this, ListCate);
        rec_Chung.setAdapter(categoryAdapter);
        super.onStart();
    }

    private void Anhxa() {
        dao = new DAO(ThemListVideo.this);

        imageView = findViewById(R.id.img_caidat_home);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.home_nav_view);
        contentView = findViewById(R.id.content_view);
    }
}