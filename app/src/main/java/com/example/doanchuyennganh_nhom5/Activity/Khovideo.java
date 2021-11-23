package com.example.doanchuyennganh_nhom5.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.Adapter.VideoAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Video;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Khovideo extends AppCompatActivity {
    ArrayList<Video> videoArrayList;
    RecyclerView Rec_khovideo;
    VideoAdapter adapter;
    DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khovideo);
        Rec_khovideo =findViewById(R.id.Rec_khovideo);
        videoArrayList = new ArrayList<>();
        dao = new DAO(Khovideo.this);
        videoArrayList = dao.ListLuuVideo(Home.taiKhoan.getIDTK());
        Rec_khovideo.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new VideoAdapter(this, videoArrayList);

        Rec_khovideo.setAdapter(adapter);

    }




}