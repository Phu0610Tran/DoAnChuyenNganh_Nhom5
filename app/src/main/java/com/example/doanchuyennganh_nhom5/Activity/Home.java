package com.example.doanchuyennganh_nhom5.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.Adapter.DanhMucAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.Test.LoaispAdapter;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.DanhMuc;
import com.example.doanchuyennganh_nhom5.model.TaiKhoan;
import com.example.doanchuyennganh_nhom5.d.Video;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DAO dao;
    public static TaiKhoan taiKhoan = new TaiKhoan();
    ImageView imageView,img_search_home;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout contentView;

    private  static ArrayList<Video> x = new ArrayList<>();
    public static String URL_GETJSON = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=";
    public static String URL_GETJSONALONE = "https://www.googleapis.com/youtube/v3/videos?id=";
    public static String KET_API_ALONE = "&part=snippet&key=AIzaSyCfbNBRWnZmFFQxpLjtGYxN8W97YRjui8U";
    public static String KEY_API =  "&key=AIzaSyCfbNBRWnZmFFQxpLjtGYxN8W97YRjui8U&maxResults=50";

    RecyclerView rec_Chung;
    ArrayList<DanhMuc> ListCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Anhxa();
        dao = new DAO(Home.this);
        ActionBar();

        rec_Chung =findViewById(R.id.rec_Chung);
        ArrayList<Video> videos= new ArrayList<>();

        ListCate = new ArrayList<>();
        ListCate.add(new DanhMuc("Hoạt hình","PLiJQ-xWoNAYv39NJXl82yfIMKIOE1eX32"));
        ListCate.add(new DanhMuc("Thể thao","PLiJQ-xWoNAYs0BqMlN6705zsQtMQdDFxr"));
        ListCate.add(new DanhMuc("Ẩm thực","PLiJQ-xWoNAYt0xD8bQcrI_POMJ5MDh_Hq"));
        ListCate.add(new DanhMuc("Du lịch","PLiJQ-xWoNAYveId1AIh09ynJjnlSOh7V9"));
        ListCate.add(new DanhMuc("Âm nhạc","PLiJQ-xWoNAYvy82qhU2Mjptq3F8hvOoUI"));
        rec_Chung.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DanhMucAdapter categoryAdapter = new DanhMucAdapter(this, ListCate);
        rec_Chung.setAdapter(categoryAdapter);
        img_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,TimKiem.class));
            }
        });

    }

    public interface VolleyCallback {
        void onSuccessResponse(String result);
    }


    private void Anhxa() {
        imageView = findViewById(R.id.img_caidat_home);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.home_nav_view);
        contentView = findViewById(R.id.content_view);
        img_search_home = findViewById(R.id.img_search_home);
        //mRecyclerView = findViewById(R.id.recV_LoadvieoTC);


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    private void ActionBar() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.iHome);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (drawerLayout.isDrawerVisible(GravityCompat.END))
                   drawerLayout.closeDrawer(GravityCompat.END);
               else drawerLayout.openDrawer(GravityCompat.END);
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.iHome) {
            startActivity(new Intent(this, Home.class));
        }else if (id == R.id.iNguoiDung) {
            if(Home.taiKhoan.getIDTK() != -1){
                startActivity(new Intent(this, CapNhat.class));
            }else {
                startActivity(new Intent(this, Dangnhap.class));
            }

        }else if (id == R.id.iKhoVideo) {
            startActivity(new Intent(this, Khovideo.class));
        }else if (id == R.id.iGoi) {
            startActivity(new Intent(this, Goithanhvien.class));
        }else if (id == R.id.iGopy) {
            startActivity(new Intent(this, GopY.class));
        }else if (id == R.id.iCaiDat) {
            startActivity(new Intent(this, Home.class));
        }else if (id == R.id.iDangXuat) {
            startActivity(new Intent(this, Dangnhap.class));
        }


        drawerLayout.closeDrawer(GravityCompat.END);
        return true;
    }

}