package com.example.doanchuyennganh_nhom5.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.Adapter.DanhMucAdapter;
import com.example.doanchuyennganh_nhom5.Test.LoaispAdapter;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.Test.Loaisp;
import com.example.doanchuyennganh_nhom5.model.DanhMuc;
import com.example.doanchuyennganh_nhom5.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ArrayList mangloaisp;
    ImageView imageView;
    DrawerLayout drawerLayout;
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
        ActionBar();
        Menu();

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





    private void Menu() {
        mangloaisp.add(new Loaisp(0,"NGƯỜI DÙNG"));
        mangloaisp.add(new Loaisp(0,"MUA GÓI THÀNH VIÊN"));
        mangloaisp.add(new Loaisp(0,"GÓP Ý"));
        mangloaisp.add(new Loaisp(0,"KHO VIDEO"));
        mangloaisp.add(new Loaisp(0,"CÀI ĐẶT"));
        mangloaisp.add(new Loaisp(0,"LIÊN HỆ"));
        mangloaisp.add(new Loaisp(0,"ĐĂNG XUẤT"));
    }

    private void Anhxa() {
        imageView = findViewById(R.id.img_caidat_home);
        drawerLayout = findViewById(R.id.drawerLayout);
        //mRecyclerView = findViewById(R.id.recV_LoadvieoTC);

        listviewmanhinhchinh = findViewById(R.id.listviewmanhinhchinh);
        mangloaisp = new ArrayList();
        loaispAdapter = new LoaispAdapter(mangloaisp,getApplicationContext());
        listviewmanhinhchinh.setAdapter(loaispAdapter);


    }

    private void ActionBar() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

    }

}