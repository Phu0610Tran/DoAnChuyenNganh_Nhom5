package com.example.doanchuyennganh_nhom5.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh_nhom5.Admin.MainActivity;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;

public class Dangnhap extends AppCompatActivity {

    private EditText edtSdt, edtMatkhau;
    private Button btnLogin;
    private TextView txtDangKy;
    DAO dao;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        AnhXa();
        SuKien();

    }
    private void SuKien() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sdt = edtSdt.getText().toString().trim();
                String Matkhau = edtMatkhau.getText().toString().trim();
                if(edtSdt.getText().length() != 0 && edtMatkhau.getText().length() != 0){
                    if(dao.isTonTaiTK(Sdt)){
                        Home.taiKhoan = dao.DangNhap(Sdt,Matkhau);
                        if(Home.taiKhoan.getIDTK() != -1){
                            if(Home.taiKhoan.getQuyen().equals("ADMIN")){
                                startActivity(new Intent(Dangnhap.this, MainActivity.class));
                                Toast.makeText(Dangnhap.this, " Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                            }
                            else if(Home.taiKhoan.getQuyen().equals("USER"))
                            {
                                startActivity(new Intent(Dangnhap.this, Home.class));
                                Toast.makeText(Dangnhap.this, " Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            }
                            else{ Toast.makeText(Dangnhap.this, " Sai thông tin đăng nhập ", Toast.LENGTH_SHORT).show(); }
                        }
                    }else { Toast.makeText(Dangnhap.this, " Sai thông tin đăng nhập ", Toast.LENGTH_SHORT).show(); }
                }

            }
        });

        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startActivity(new Intent(Dangnhap.this, DangKy.class)); }
        });
    }


    private void AnhXa() {
        dao = new DAO(Dangnhap.this);
        edtSdt = (EditText) findViewById(R.id.edt_taikhoan_dn);
        edtMatkhau = (EditText) findViewById(R.id.edt_matkhau_dn);
        btnLogin = (Button) findViewById(R.id.btn_dangnhap_dn);
        txtDangKy = (TextView) findViewById(R.id.txt_dangky_dk);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
    }
}
