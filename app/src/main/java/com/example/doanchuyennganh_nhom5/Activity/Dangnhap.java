package com.example.doanchuyennganh_nhom5.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh_nhom5.Admin.MainActivity;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.TaiKhoan;

public class Dangnhap extends AppCompatActivity {

    public static final String TAG = Dangnhap.class.getSimpleName();
    private EditText edtSdt;
    private EditText edtMatkhau;
    private Button btnLogin;
    private TextView txtDangKy,txt_quenmatkhau_dangnhap;
    DAO dao;

    private ProgressDialog pDialog;
    public static final String URL_LOGIN = "https://doanchuyennghanh.000webhostapp.com/login.php";
    public static final String KEY_SDT = "Sdt";
    public static final String KEY_MATKHAU = "Matkhau";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        addControl();
        addEvent();

    }
    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get value input
                String Sdt = edtSdt.getText().toString().trim();
                String Matkhau = edtMatkhau.getText().toString().trim();
                // Call method
                //loginAccount(Sdt, Matkhau);
                if(edtSdt.getText().length() != 0 && edtMatkhau.getText().length() != 0){
                    if(dao.isTonTaiTK(Sdt)){
                        Home.taiKhoan = dao.DangNhap(Sdt,Matkhau);
                        if(Home.taiKhoan.getIDTK() != -1){
                            if(Home.taiKhoan.getQuyen().equals("ADMIN")){
                                Intent intent = new Intent(Dangnhap.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(Dangnhap.this, " Đăng nhập thành công ", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Intent intent = new Intent(Dangnhap.this, Home.class);
                                startActivity(intent);
                                Toast.makeText(Dangnhap.this, " Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }else
                        {
                            Toast.makeText(Dangnhap.this, " Sai thông tin đăng nhập ", Toast.LENGTH_SHORT).show();
                        }
                }

            }
        });
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentdk = new Intent(Dangnhap.this, DangKy.class);
                startActivity(intentdk);
            }
        });
        txt_quenmatkhau_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });


    }


    private void addControl() {
        txt_quenmatkhau_dangnhap = (TextView) findViewById(R.id.txt_quenmatkhau_dangnhap);
        dao = new DAO(Dangnhap.this);
        edtSdt = (EditText) findViewById(R.id.edt_taikhoan_dn);
        edtMatkhau = (EditText) findViewById(R.id.edt_matkhau_dn);
        btnLogin = (Button) findViewById(R.id.btn_dangnhap_dn);
        txtDangKy = (TextView) findViewById(R.id.txt_dangky_dk);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
    }
    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_quenmatkhau,null);
        final EditText edt_tk_qmk = view.findViewById(R.id.edt_tk_qmk);
        final EditText edtmatkhaumoi = view.findViewById(R.id.edt_matkhaucu_qmk);
        final EditText edtmatkhaucu= view.findViewById(R.id.edt_matkhaumoi_qmk);
        final EditText editnlmatkhaumoi = view.findViewById(R.id.edt_nlmatkhaumoi_qmk);
        edtmatkhaumoi.setEnabled(false);
        edtmatkhaucu.setEnabled(false);
        editnlmatkhaumoi.setEnabled(false);
        builder.setView(view);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Dangnhap.this,"ssssss",Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Dangnhap.this,"ssssss",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }
}
