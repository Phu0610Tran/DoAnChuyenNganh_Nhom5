package com.example.doanchuyennganh_nhom5.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Account;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Dangnhap extends AppCompatActivity {

    public static final String TAG = Dangnhap.class.getSimpleName();
    private EditText edtSdt;
    private EditText edtMatkhau;
    private Button btnLogin;
    private TextView txtDangKy;


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
                loginAccount(Sdt, Matkhau);

            }
        });
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentdk = new Intent(Dangnhap.this, DangKy.class);
                startActivity(intentdk);
            }
        });


    }


    private void addControl() {
        edtSdt = (EditText) findViewById(R.id.edt_taikhoan_dn);
        edtMatkhau = (EditText) findViewById(R.id.edt_matkhau_dn);
        btnLogin = (Button) findViewById(R.id.btn_dangnhap_dn);
        txtDangKy = (TextView) findViewById(R.id.txt_dangky_dk);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    public void loginAccount(final String Sdt, final String Matkhau) {

        if (checkEditText(edtSdt) && checkEditText(edtMatkhau)) {
            pDialog.show();
            StringRequest requestLogin = new StringRequest(Request.Method.POST, URL_LOGIN,
                    new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("success") == 1) {

                                    Account account = new Account();
                                    account.setIdtk(jsonObject.getString("IDTK"));
                                    account.setSdt(jsonObject.getString("Sdt"));
                                    account.setHinhdaidien(jsonObject.getString("Hinhdaidien"));
                                    account.setGmail(jsonObject.getString("Mail"));
                                    account.setHovaten(jsonObject.getString("hovaten"));
                                    account.setQuyen(jsonObject.getInt("Quyen"));
                                    account.setLoaiTK(jsonObject.getString("LoaiTK"));
                                    //account.setNgaysinh(jsonObject.getLong("Ngaysinh"));
                                    message = jsonObject.getString("message");

                                    Toast.makeText(Dangnhap.this, message, Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Dangnhap.this, CapNhat.class);
                                    intent.putExtra("login", account);
                                    startActivity(intent);

                                } else {
                                    message = jsonObject.getString("message");
                                    Toast.makeText(Dangnhap.this, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            pDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                            pDialog.dismiss();
                        }
                    }) {
                /**
                 * set paramater
                 * */
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put(KEY_SDT, Sdt);
                    params.put(KEY_MATKHAU, Matkhau);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(requestLogin);
        }
    }

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
