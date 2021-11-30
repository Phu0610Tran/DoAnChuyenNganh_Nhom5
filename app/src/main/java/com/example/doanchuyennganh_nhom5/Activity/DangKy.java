package com.example.doanchuyennganh_nhom5.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class DangKy extends AppCompatActivity {
    public static final String TAG = DangKy.class.getSimpleName();
    private EditText edtmail, edtPassWord, edtPhone, edtNhaplaipassword;
    private Button btnRegister;
    private ProgressDialog pDialog;
    private ImageView imgquaylai;
    DAO dao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        addControls();
        addEvents();

    }
    private void addEvents() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= edtmail.getText().toString().trim();
                String mk = edtPassWord.getText().toString().trim();
                String sdt = edtPhone.getText().toString().trim();
                String nlmatkhau = edtNhaplaipassword.getText().toString().trim();
                if (edtPhone.getText().length() != 0 && edtPassWord.getText().length() != 0
                        && edtNhaplaipassword.getText().length() != 0 && edtmail.getText().length() != 0){
                    if(dao.isTonTaiTK(sdt) == false){
                        if(mk.equals(nlmatkhau)){
                            dao.TaoTK(
                                    sdt,mail,mk,"USER","THUONG"
                            );
                            if(dao.isTonTaiTK(sdt)){
                                Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DangKy.this, Dangnhap.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(DangKy.this, "Đăng ký thất bại. Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            edtNhaplaipassword.requestFocus();
                            Toast.makeText(DangKy.this, "Nhập lại mật khẩu phải trùng với mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DangKy.this, "Số điện thoại đã được sử dụng. Vui lòng đăng ký số điện thoại khác", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DangKy.this, "Vui lòng điền đầy đủ các trường", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void addControls() {
        dao =  new DAO(DangKy.this);
        edtmail = (EditText) findViewById(R.id.edt_email_dk);
        edtPassWord = (EditText) findViewById(R.id.edt_matkhau_dk);
        btnRegister = (Button) findViewById(R.id.btn_xacnhan_dk);
        imgquaylai = (ImageView) findViewById(R.id.imgb_quaylai_dk);
        edtPhone = (EditText) findViewById(R.id.edt_sodienthoai_dk);
        edtNhaplaipassword = findViewById(R.id.edt_nlmatkhau_dk);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng ký...");
        pDialog.setCanceledOnTouchOutside(false);
    }
}
