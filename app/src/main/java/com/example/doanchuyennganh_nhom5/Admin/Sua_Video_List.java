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

import com.example.doanchuyennganh_nhom5.Adapter.CategoryAdapter;
import com.example.doanchuyennganh_nhom5.Adapter.VideoAdminAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Category;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Sua_Video_List extends AppCompatActivity {

    Spinner spnTheloai;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> listCategory;
    private boolean isEnabled;
    EditText edtMavideo, edtHinhnen, edtTieude, edtNoidung, edtTacgia;
    ImageButton ibtnQuatrai, ibtnQuaphai;
    ImageView HinhVideo;
    Button btnThem, btnHuy, btnGuilink;
    DAO dao;
    public String theloai;
    int qq;
    int id;
    String KeyVD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_video_list);
        Intent intent = getIntent();
        id = intent.getIntExtra("ID_VIDEO",123);
        AnhXa();
        GetData();
        enableControl();

        dao = new DAO(Sua_Video_List.this);
//        spnTheloai = findViewById(R.id.spnAddTheloai);
//        listCategory = getListCategory();
//        categoryAdapter = new CategoryAdapter(Sua_Video_List.this, R.layout.item_selected, listCategory);
//        spnTheloai.setAdapter(categoryAdapter);
//        spnTheloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(Sua_Video_List.this, "Bạn vừa chọn thể loại: " + categoryAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
//                theloai = categoryAdapter.getItem(position).getIDcategory();
//                qq = position;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Sua_Video_List.this, "sss : " + qq, Toast.LENGTH_SHORT).show();
                    dao.TaoVideo(KeyVD,edtHinhnen.getText().toString(),"CHUAXETDUYET",edtTieude.getText().toString(),
                            edtNoidung.getText().toString(),edtTacgia.getText().toString());
                    Toast.makeText(Sua_Video_List.this, " Thêm thành công ", Toast.LENGTH_SHORT).show();
                    dao.XoaVideoAdmin(KeyVD);
                    startActivity(new Intent(Sua_Video_List.this,ThemListVideo.class));

                }




        });

    }

    private void GetData() {
//        Video video = dao.thongtinvideoAdmin(id);
        Video video = VideoAdminAdapter.ListVideo.get(id);
        edtHinhnen.setText(video.getHinhVD());
        edtTieude.setText(video.getTieuDeVD());
        edtNoidung.setText(video.getNoiDungVD());
        edtTacgia.setText(video.getTacGia());
        KeyVD = video.getIDVD();
        Log.e(" thong tin " , video.getIDVD() + video.getHinhVD() +video.getTieuDeVD()+ video.getNoiDungVD() + video.getTacGia() );
        Picasso.with(Sua_Video_List.this).load(video.getHinhVD())
                .placeholder(R.drawable.logodt)
                .error(R.drawable.logodt).into(HinhVideo);
    }


    private void AnhXa() {
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

//    private ArrayList<Category> getListCategory() {
//        ArrayList<Category> list = new ArrayList<>();
//
//        list.add(new Category("Du Lịch","DULICH"));
//        list.add(new Category("Hoạt Hình","HOATHINH"));
//        list.add(new Category("Thể Thao","THETHAO"));
//        list.add(new Category("Âm Nhạc", "AMNHAC"));
//        list.add(new Category("Ẩm Thực", "AMTHUC"));
//
//        return list;
//    }
}