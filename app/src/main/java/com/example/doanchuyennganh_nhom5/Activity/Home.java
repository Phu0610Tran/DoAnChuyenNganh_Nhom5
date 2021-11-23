package com.example.doanchuyennganh_nhom5.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
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
    ArrayList mangloaisp;
    ImageView imageView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout contentView;
    LoaispAdapter loaispAdapter;
    ListView listviewmanhinhchinh;
    RecyclerView mRecyclerView;
    private  static ArrayList<Video> x = new ArrayList<>();
    ArrayList<Video> videoYouTubeArrayList;
    public static String URL_GETJSON = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=";
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

//        videoYouTubeArrayList = new ArrayList<>();
//        GetJsonYouTube(URL_GETJSON);
        ListCate = new ArrayList<>();
        ListCate.add(new DanhMuc("Hoạt hình","PLiJQ-xWoNAYv39NJXl82yfIMKIOE1eX32"));
        ListCate.add(new DanhMuc("Thể thao","PLiJQ-xWoNAYs0BqMlN6705zsQtMQdDFxr"));
        ListCate.add(new DanhMuc("Ẩm thực","PLiJQ-xWoNAYt0xD8bQcrI_POMJ5MDh_Hq"));
        ListCate.add(new DanhMuc("Du lịch","PLiJQ-xWoNAYveId1AIh09ynJjnlSOh7V9"));
        ListCate.add(new DanhMuc("Âm nhạc","PLiJQ-xWoNAYvy82qhU2Mjptq3F8hvOoUI"));
        rec_Chung.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DanhMucAdapter categoryAdapter = new DanhMucAdapter(this, ListCate);
        rec_Chung.setAdapter(categoryAdapter);


    }

    public interface VolleyCallback {
        void onSuccessResponse(String result);
    }


    private void Anhxa() {
        imageView = findViewById(R.id.img_caidat_home);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.home_nav_view);
        contentView = findViewById(R.id.content_view);
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
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, , "Open Navigation Drawer", "Close Navigation Drawer");
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

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

//        animateNavigationDrawer();

    }

//    private void animateNavigationDrawer() {
//        drawerLayout.setScrimColor(getResources().getColor(R.color.teal_700));
//        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                final float diffScaleOffset = slideOffset * (1 - 0.7f);
//                final float offsetScale = 1 - diffScaleOffset;
//                contentView.setScaleX(offsetScale);
//                contentView.setScaleX(offsetScale);
//
//                final float xOffset = drawerView.getWidth() * slideOffset;
//                final float xOffsetDiff = contentView.getWidth() * diffScaleOffset / 2;
//                final float xTranslation = xOffset - xOffsetDiff;
//                contentView.setTranslationX(xTranslation);
//
//            }
//        });
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.iHome) {
            startActivity(new Intent(this, Home.class));
        }else if (id == R.id.iNguoiDung) {
            startActivity(new Intent(this, Home.class));
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