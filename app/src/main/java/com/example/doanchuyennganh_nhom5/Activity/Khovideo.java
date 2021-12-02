package com.example.doanchuyennganh_nhom5.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.Adapter.CategoryAdapter;
import com.example.doanchuyennganh_nhom5.Adapter.VideoAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Category;
import com.example.doanchuyennganh_nhom5.model.Video;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Khovideo extends AppCompatActivity {
    ArrayList<Video> videoArrayList,videoArrayListOLD;
    ImageButton out_khovideo;
    RecyclerView Rec_khovideo;

    VideoAdapter adapter;
    DAO dao;
    Spinner spnTheloai;
    CategoryAdapter categoryAdapter;
    String Danhmuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khovideo);
        AnhXa();

        videoArrayList = dao.ListLuuVideo(Home.taiKhoan.getIDTK());

        Rec_khovideo.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new VideoAdapter(this, videoArrayList);
        Rec_khovideo.setAdapter(adapter);

        categoryAdapter = new CategoryAdapter(Khovideo.this, R.layout.item_selected, getListCategory());
        spnTheloai.setAdapter(categoryAdapter);
        Danhmuc = "";

        SuKien();
    }

    private void SuKien() {

        out_khovideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onBackPressed(); }
        });

        spnTheloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Danhmuc = categoryAdapter.getItem(position).getIDcategory();
                Toast.makeText(Khovideo.this, " bạn chọn " + Danhmuc, Toast.LENGTH_SHORT).show();

                videoArrayListOLD = new ArrayList<>();
                videoArrayListOLD = dao.ListLuuVideoTHELOAI(Home.taiKhoan.getIDTK(),Danhmuc);
                adapter.setListVideo(videoArrayListOLD);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void AnhXa() {
        dao = new DAO(Khovideo.this);
        out_khovideo = findViewById(R.id.out_khovideo);
        Rec_khovideo =findViewById(R.id.Rec_khovideo);
        spnTheloai = findViewById(R.id.spnTheloai);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private ArrayList<Category> getListCategory() {
        ArrayList<Category> list = new ArrayList<>();

        list.add(new Category("Âm Nhạc","AMNHAC"));
        list.add(new Category("Hoạt Hình","HOATHINH"));
        list.add(new Category("Ẩm Thực","AMTHUC"));
        list.add(new Category("Du Lịch", "DULICH"));
        list.add(new Category("Thể Thao", "THETHAO"));

        return list;
    }
}