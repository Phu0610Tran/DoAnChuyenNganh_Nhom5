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
//    public static final String REGISTER_URL = "https://doanchuyennghanh.000webhostapp.com/register.php";

    public static final String KEY_LOAITK = "LoaiTK";
    public static final String KEY_QUYEN = "Quyen";
    public static final String KEY_MATKHAU = "Matkhau";
    public static final String KEY_SDT = "Sdt";
    public static final String KEY_MAIL = "Mail";

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
                //Get data input

                String mail= edtmail.getText().toString().trim();
                String mk = edtPassWord.getText().toString().trim();
                String sdt = edtPhone.getText().toString().trim();
                String nlmatkhau = edtNhaplaipassword.getText().toString().trim();

                if (edtPhone.getText().length() != 0 && edtPassWord.getText().length() != 0
                        && edtNhaplaipassword.getText().length() != 0 && edtmail.getText().length() != 0);
                if(dao.isTonTaiTK(sdt)){
                    if(mk.equals(nlmatkhau)){
                        dao.TaoTK(
                                sdt,mail,mk,"USER","THUONG"
                        );
                    }
                }
                //Call method register
                //registerUser(mail, sdt, mk,0,"0");
            }
        });
        imgquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this,Dangnhap.class);
                startActivity(intent);
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

//    private void registerUser(final String mail, final String sdt, final String mk, final int quyen,final String loaitk) {
//
//        if (checkEditText(edtmail) && checkEditText(edtPassWord) && checkEditText(edtPhone)) {
//            pDialog.show();
//            StringRequest registerRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            Log.d(TAG, response);
//                            String message = "";
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                if (jsonObject.getInt("success") == 1) {
//                                    com.example.doanchuyennganh_nhom5.model.DangKy dangKy = new com.example.doanchuyennganh_nhom5.model.DangKy();
//                                    dangKy.setSdt(jsonObject.getString("Sdt"));
//                                    message = jsonObject.getString("message");
//
//                                    Toast.makeText(DangKy.this, message, Toast.LENGTH_LONG).show();
//                                    //Start LoginActivity
//                                    Intent intent = new Intent(DangKy.this, Dangnhap.class);
//                                    startActivity(intent);
//                                } else {
//                                    message = jsonObject.getString("message");
//
//                                    Toast.makeText(DangKy.this, message, Toast.LENGTH_LONG).show();
//                                }
//                            } catch (JSONException error) {
//                                VolleyLog.d(TAG, "Error: " + error.getMessage());
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
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<>();
//                    params.put(KEY_MAIL, mail);
//
//                    params.put(KEY_SDT, sdt);
//                    params.put(KEY_MATKHAU, mk);
//                    params.put(KEY_QUYEN, String.valueOf(quyen));
//                    params.put(KEY_LOAITK, loaitk);
//                    return params;
//                }
//
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            requestQueue.add(registerRequest);
//        }
//    }

    /**
     * Check Input
     */
//    private boolean checkEditText(EditText editText) {
//        if (editText.getText().toString().trim().length() > 0)
//            return true;
//        else {
//            editText.setError("Vui lòng nhập dữ liệu!");
//        }
//        return false;
//    }
}
