package com.example.doanchuyennganh_nhom5.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doanchuyennganh_nhom5.Adapter.CategoryAdapter;
import com.example.doanchuyennganh_nhom5.Adapter.QLTaikhoanAdapter;
import com.example.doanchuyennganh_nhom5.Adapter.QLVideoAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Category;
import com.example.doanchuyennganh_nhom5.model.TaiKhoan;
import com.example.doanchuyennganh_nhom5.model.Video;

import java.util.ArrayList;

public class QL_Taikhoan extends AppCompatActivity {

    RecyclerView recV_DanhSachTaiKhoan;
    DAO dao;
    TaiKhoan taiKhoan;
    ArrayList<TaiKhoan> listTaiKhoan;
    QLTaikhoanAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_taikhoan);

        dao = new DAO(this);
        recV_DanhSachTaiKhoan = findViewById(R.id.recV_DanhSachTaikhoan);
        registerForContextMenu(recV_DanhSachTaiKhoan);

        listTaiKhoan = new ArrayList<>();
        adapter = new QLTaikhoanAdapter(this, listTaiKhoan);
        Load();

        recV_DanhSachTaiKhoan.setLayoutManager( new LinearLayoutManager(QL_Taikhoan.this, LinearLayoutManager.VERTICAL,false));
        recV_DanhSachTaiKhoan.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });
    }

    @Override
    protected void onStart() {
        Load();
        super.onStart();
    }

    public void Load(){
        listTaiKhoan.clear();
        listTaiKhoan.addAll(dao.QuanLyTaiKhoan());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = QLTaikhoanAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iSua:
                Intent iCapnhat = new Intent(this, CapNhat_TaiKhoan.class);
                iCapnhat.putExtra("IDTK", listTaiKhoan.get(position).getIDTK());
                Toast.makeText(this, "ID tài khoản là: " + listTaiKhoan.get(position).getIDTK(), Toast.LENGTH_SHORT).show();
                startActivity(iCapnhat);
                return true;
            case R.id.iXoa:
                taiKhoan = listTaiKhoan.get(position);
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
                dao.XoaTK(taiKhoan.getIDTK());
                Toast.makeText(QL_Taikhoan.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
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
}