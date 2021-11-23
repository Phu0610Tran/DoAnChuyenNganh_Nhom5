package com.example.doanchuyennganh_nhom5.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.d.Account;

public class CapNhat extends AppCompatActivity {

    EditText edthoten, edtngaythangnamsinh, edtgioitinh, edtsodienthoai, edtmail;
    Button btncapnhat;
    private Account account;
    private boolean isEnabled;
    Button btndoimatkhau;

    public static final String url = "https://doanchuyennghanh.000webhostapp.com/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhat);

        Intent intent = getIntent();
        account = new Account();
        account = (Account) intent.getSerializableExtra("login");

        AnhXa();
        btndoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }

    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau,null);
        final EditText edtmatkhaumoi = view.findViewById(R.id.edt_matkhaucu_dmk);
        final EditText edtmatkhaucu= view.findViewById(R.id.edt_matkhaumoi_dmk);
        final EditText editnlmatkhaumoi = view.findViewById(R.id.edt_nlmatkhaumoi_dmk);
        builder.setView(view);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(CapNhat.this,"ssssss",Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(CapNhat.this,"ssssss",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    private void enableControl(){
        edtmail.setEnabled(isEnabled);
        edthoten.setEnabled(isEnabled);
        edtngaythangnamsinh.setEnabled(isEnabled);
//        edtgioitinh.setEnabled(isEnabled);
        edtsodienthoai.setEnabled(isEnabled);
        edtmail.setEnabled(isEnabled);
    }

    private void AnhXa() {
        edthoten = (EditText) findViewById(R.id.edt_hovaten_cn);
        edtngaythangnamsinh = (EditText) findViewById(R.id.edt_ngaythangnamsinh_cn);
//        edtgioitinh = (EditText) findViewById(R.id.edt_gioitinh_cn);
        edtsodienthoai = (EditText) findViewById(R.id.edt_sodienthoai_cn);
        edtmail = (EditText) findViewById(R.id.edt_mail_cn);
        btncapnhat = (Button) findViewById(R.id.btn_capnhat_cn);
        btndoimatkhau = (Button) findViewById(R.id.btn_doimatkhau_cn);


        edthoten.setText(account.getHovaten());
        edtsodienthoai.setText(account.getSdt());
        edtmail.setText(account.getGmail());
        enableControl();
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEnabled = !isEnabled;
                enableControl();
                if (isEnabled){
                    btncapnhat.setText("Lưu");
                }
                else{
                    btncapnhat.setText("Cập nhật");
                    //CapNhatTaiKhoan(url);
//
//                    String hoten = edthoten.getText().toString().trim();
//                    String ngaythangnamsinh = edtngaythangnamsinh.getText().toString().trim();
//                      String gioitinh = edtgioitinh.getText().toString().trim();
//                    String sodienthoai = edtsodienthoai.getText().toString().trim();
//                    String mail = edtmail.getText().toString().trim();
                }


            }
        });
    }

//    private void CapNhatTaiKhoan(final String url){
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        if (response.trim().equals("success")){
//                            // GetData(url);
//                            Toast.makeText(CapNhat.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
//
//                        }
//                        else{
//                            Toast.makeText(CapNhat.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(CapNhat.this, "Vui lòng thử lại", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("IDTK",account.getIdtk());
//                params.put("hovaten", edthoten.getText().toString().trim());
//                params.put("ngaythangnamsinh", edtngaythangnamsinh.getText().toString().trim());
////                params.put("gioitinh", edtgioitinh.getText().toString().trim());
//                params.put("Sdt", edtsodienthoai.getText().toString().trim());
//                params.put("Mail", edtmail.getText().toString().trim());
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }

}
