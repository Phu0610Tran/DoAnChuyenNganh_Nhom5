package com.example.doanchuyennganh_nhom5.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doanchuyennganh_nhom5.Adapter.CategoryAdapter;
import com.example.doanchuyennganh_nhom5.Adapter.QLVideoAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.DataBase.DB;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Category;
import com.example.doanchuyennganh_nhom5.model.Video;

import java.util.ArrayList;
import java.util.List;

public class QL_Video extends AppCompatActivity {

    Spinner spn_Qlvideo;
    CategoryAdapter categoryDAO;
    RecyclerView recV_DanhSachVIDEO;
    DAO dao;
    Video video;
    ArrayList<Video> listVD;
    ArrayList<Category> listCategory;
    QLVideoAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_video);

        AnhXa();

        listCategory = getListCategort();
        categoryDAO = new CategoryAdapter(QL_Video.this, R.layout.item_selected, listCategory);
        spn_Qlvideo.setAdapter(categoryDAO);
        registerForContextMenu(recV_DanhSachVIDEO);

        listVD = new ArrayList<>();
        adapter = new QLVideoAdapter(this,listVD);
        spn_Qlvideo.setSelection(0);
        Load();

        recV_DanhSachVIDEO.setLayoutManager( new LinearLayoutManager(QL_Video.this,LinearLayoutManager.VERTICAL,false));
        recV_DanhSachVIDEO.setAdapter(adapter);

        spn_Qlvideo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Load();
                Toast.makeText(QL_Video.this, "Bạn vừa chọn " + categoryDAO.getItem(position).getName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void AnhXa() {
        dao = new DAO(this);
        spn_Qlvideo = findViewById(R.id.spn_qlvideo);
        recV_DanhSachVIDEO = findViewById(R.id.recV_DanhSachVIDEO);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iAdd:
                startActivity(new Intent(this, Them_Video.class));
                return true;
            case R.id.iTimkiem:

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void Load(){
        listVD.clear();
        listVD.addAll(dao.QuanLyVideo(listCategory.get(getPos()).getIDcategory()));
        adapter.notifyDataSetChanged();
    }

    private int getPos(){
        for(int i =0; i < listCategory.size();i++)
        {
            if (i == spn_Qlvideo.getSelectedItemPosition()){
                return i;
            }
        }
        return -1;
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = QLVideoAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iSua:
                Intent iCapnhat = new Intent(this, CapNhat_Video.class);
                iCapnhat.putExtra("IDVD", listVD.get(position).getIDVD());
                startActivity(iCapnhat);
                return true;
            case R.id.iXoa:
                video = listVD.get(position);
                ShowDialog();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc muốn xóa nó hay không ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.XoaVideo(video.getIDVD());
                onBackPressed();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private ArrayList<Category> getListCategort() {
        ArrayList<Category> list = new ArrayList<>();

        list.add(new Category("Du Lịch","DULICH"));
        list.add(new Category("Hoạt Hình","HOATHINH"));
        list.add(new Category("Thể Thao","THETHAO"));
        list.add(new Category("Âm Nhạc", "AMNHAC"));
        list.add(new Category("Ẩm Thực", "AMTHUC"));
        list.add(new Category("Chưa Xét Duyệt", "CHUAXETDUYET"));
        return list;
    }
}