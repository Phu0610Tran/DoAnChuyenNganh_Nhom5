package com.example.doanchuyennganh_nhom5.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.Activity.Home;
import com.example.doanchuyennganh_nhom5.Activity.PlayvideoActivity;
import com.example.doanchuyennganh_nhom5.Adapter.CategoryAdapter;
import com.example.doanchuyennganh_nhom5.Adapter.VideoAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Category;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Them_Video extends AppCompatActivity {
    Spinner spnTheloai;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> listCategory;
    private boolean isEnabled;
    EditText edtMavideo, edtHinhnen, edtTieude, edtNoidung, edtTacgia;
    ImageButton ibtnQuatrai, ibtnQuaphai;
    ImageView HinhVideo;
    Button btnThem, btnHuy, btnGuilink;
    String URL_LIST = "https://www.youtube.com/playlist?list=PLiJQ-xWoNAYvy82qhU2Mjptq3F8hvOoUI";
    String URL_VIDEO = "https://www.youtube.com/watch?v=DMkzIOLppf4";
    DAO dao;
    String theloai;


    String URL = "https://www.googleapis.com/youtube/v3/videos?id=DMkzIOLppf4&part=snippet&key=AIzaSyCfbNBRWnZmFFQxpLjtGYxN8W97YRjui8U";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_video);

        AnhXa();
        enableControl();
        spnTheloai = findViewById(R.id.spnAddTheloai);
        listCategory = getListCategory();
        dao = new DAO(Them_Video.this);
        categoryAdapter = new CategoryAdapter(Them_Video.this, R.layout.item_selected, listCategory);
        spnTheloai.setAdapter(categoryAdapter);
        spnTheloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Them_Video.this, "Bạn vừa chọn thể loại: " + categoryAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
                theloai = categoryAdapter.getItem(position).getIDcategory();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnGuilink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //XuLy(URL_LIST);
                if(edtMavideo.getText().length() !=0){
                    XuLy(edtMavideo.getText().toString());
                }
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dao.isTonTaiVideo(edtMavideo.getText().toString()))
                {
                    Toast.makeText(Them_Video.this, " Video đã tồn tại vui lòng kiểm tra lại ", Toast.LENGTH_SHORT).show();
                }
                else {
                    dao.TaoVideo(edtMavideo.getText().toString(),edtHinhnen.getText().toString(),theloai,edtTieude.getText().toString(),
                            edtNoidung.getText().toString(),edtTacgia.getText().toString());
                }



            }
        });

    }

    void XuLy(String link){
        boolean isMatches_List,isMatches_aVideo;
        isMatches_List = link.matches( "https:\\/\\/www\\.youtube\\.com\\/playlist\\?list=(.{34})");//Pattern.compile().matcher(link).matches();
        isMatches_aVideo = link.matches("https:\\/\\/www\\.youtube\\.com\\/watch\\?v=(.{11})");// Pattern.compile().matcher(link).matches();
        String [] arr;
        if(isMatches_List){
            arr = link.split("list=");
            Intent intent = new Intent(Them_Video.this, ThemListVideo.class);
            String keylist = arr[1];
            intent.putExtra("Listvideo",keylist);
            startActivity(intent);
            Toast.makeText(Them_Video.this, "List = " + arr[1], Toast.LENGTH_SHORT).show();
        }
        else if (isMatches_aVideo){
            arr = link.split("v=");
            GetJsonYouTubeALONE(Home.URL_GETJSONALONE+ arr[1] + Home.KET_API_ALONE);
//            Toast.makeText(Them_Video.this, "A video = " + arr[1], Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(Them_Video.this, "Vui lòng gán đúng đường dẫn", Toast.LENGTH_SHORT).show();
        }
    }
    //------------------------them video
    private void GetJsonYouTubeALONE(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(Them_Video.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("d","d");
                    JSONArray jsonItems = response.getJSONArray("items");
                    String title = ""; String url = ""; String idVideo=""; String description="";String videoOwnerChannelTitle="";
                    for (int i = 0; i < jsonItems.length(); i++)
                    {
                        JSONObject jsonItem = jsonItems.getJSONObject(i);
                        idVideo = jsonItem.getString("id");

                        JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                        title = jsonSnippet.getString("title");
                        description = jsonSnippet.getString("description");
                        videoOwnerChannelTitle = jsonSnippet.getString("channelTitle");
                        JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                        url = jsonMedium.getString("url");
                        Log.e("Thong Tin ", " Thông tin  = " +  idVideo + " , " + title + " , " + description + " , " + videoOwnerChannelTitle
                                + " , " + url);
                        edtMavideo.setText(idVideo);
                        edtHinhnen.setText(url);
                        edtTieude.setText(title);
                        edtNoidung.setText(description);
                        edtTacgia.setText(videoOwnerChannelTitle);
                        Picasso.with(Them_Video.this).load(url)
                                .placeholder(R.drawable.logodt)
                                .error(R.drawable.logodt).into(HinhVideo);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Them_Video.this, "ssssssssss", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }



    private void AnhXa() {
        edtMavideo = findViewById(R.id.edtAddMavideo);
        edtHinhnen = findViewById(R.id.edtAddHinhdaidien);
        edtTieude = findViewById(R.id.edtAddTieude);
        edtNoidung = findViewById(R.id.edtAddNoidung);
        edtTacgia = findViewById(R.id.edtAddTacgia);
        ibtnQuatrai = findViewById(R.id.ibtnQuaTrai);
        btnThem = findViewById(R.id.btnThem);
        btnHuy = findViewById(R.id.btnHuy);
        ibtnQuaphai = findViewById(R.id.ibtnQuaPhai);
        btnGuilink = findViewById(R.id.btnGuilink);
        HinhVideo = findViewById(R.id.HinhVideo);
    }

    private void enableControl() {

    }

    private ArrayList<Category> getListCategory() {
        ArrayList<Category> list = new ArrayList<>();

        list.add(new Category("Du Lịch","DULICH"));
        list.add(new Category("Hoạt Hình","HOATHINH"));
        list.add(new Category("Thể Thao","THETHAO"));
        list.add(new Category("Âm Nhạc", "AMNHAC"));
        list.add(new Category("Ẩm Thực", "AMTHUC"));

        return list;
    }
}