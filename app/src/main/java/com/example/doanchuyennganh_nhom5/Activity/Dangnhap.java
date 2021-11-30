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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
                        if(Home.taiKhoan !=null){
                            Intent intent = new Intent(Dangnhap.this, Home.class);
                            startActivity(intent);
                            Toast.makeText(Dangnhap.this, " Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        }
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

//    public void loginAccount(final String Sdt, final String Matkhau) {
//
//        if (checkEditText(edtSdt) && checkEditText(edtMatkhau)) {
//            pDialog.show();
//            StringRequest requestLogin = new StringRequest(Request.Method.POST, URL_LOGIN,
//                    new Response.Listener<String>(){
//                        @Override
//                        public void onResponse(String response) {
//                            Log.d(TAG, response);
//                            String message = "";
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                if (jsonObject.getInt("success") == 1) {
//
//                                    Account account = new Account();
//                                    account.setIdtk(jsonObject.getString("IDTK"));
//                                    account.setSdt(jsonObject.getString("Sdt"));
//                                    account.setHinhdaidien(jsonObject.getString("Hinhdaidien"));
//                                    account.setGmail(jsonObject.getString("Mail"));
//                                    account.setHovaten(jsonObject.getString("hovaten"));
//                                    account.setQuyen(jsonObject.getInt("Quyen"));
//                                    account.setLoaiTK(jsonObject.getString("LoaiTK"));
//                                    //account.setNgaysinh(jsonObject.getLong("Ngaysinh"));
//                                    message = jsonObject.getString("message");
//
//                                    Toast.makeText(Dangnhap.this, message, Toast.LENGTH_SHORT).show();
//
//                                    Intent intent = new Intent(Dangnhap.this, CapNhat.class);
//                                    intent.putExtra("login", account);
//                                    startActivity(intent);
//
//                                } else {
//                                    message = jsonObject.getString("message");
//                                    Toast.makeText(Dangnhap.this, message, Toast.LENGTH_LONG).show();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            pDialog.dismiss();
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            VolleyLog.d(TAG, "Error: " + error.getMessage());
//                            pDialog.dismiss();
//                        }
//                    }) {
//                /**
//                 * set paramater
//                 * */
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<>();
//                    params.put(KEY_SDT, Sdt);
//                    params.put(KEY_MATKHAU, Matkhau);
//                    return params;
//                }
//            };
//            RequestQueue queue = Volley.newRequestQueue(this);
//            queue.add(requestLogin);
//        }
//    }

    /**
     * Check input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }
}
