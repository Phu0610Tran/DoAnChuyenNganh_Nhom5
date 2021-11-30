package com.example.doanchuyennganh_nhom5.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.Adapter.DanhMucAdapterAdmin;
import com.example.doanchuyennganh_nhom5.Adapter.VideoAdminAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.DanhMuc;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThemListVideo extends AppCompatActivity {
    ImageView imageView,img_search_home;
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
        dao = new DAO(ThemListVideo.this);
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

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void Anhxa() {
        imageView = findViewById(R.id.img_caidat_home);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.home_nav_view);
        contentView = findViewById(R.id.content_view);
    }
}