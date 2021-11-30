package com.example.doanchuyennganh_nhom5.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
// import com.example.doanchuyennganh_nhom5.d.Account;

public class CapNhat extends AppCompatActivity {

    EditText edthoten, edtngaythangnamsinh, edtgioitinh, edtsodienthoai, edtmail;
    Button btncapnhat;
    CircleImageView img_hinhanh;
    DAO dao;
    //    private Account account;
    private boolean isEnabled;
    Button btndoimatkhau;
    ImageButton ibtn_Camera,ibtn_Folder;
    int REQUEST_CODE_CAMERA=123;
    int REQUEST_CODE_FOLDER=456;

    //    public static final String url = "https://doanchuyennghanh.000webhostapp.com/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhat);

//        Intent intent = getIntent();
//        account = new Account();
//        account = (Account) intent.getSerializableExtra("login");

        AnhXa();
        edthoten.setText(Home.taiKhoan.getHovaTen());
        edtngaythangnamsinh.setText(Home.taiKhoan.getNgaySinh());
        edtmail.setText(Home.taiKhoan.getMaill());
        edtsodienthoai.setText(Home.taiKhoan.getSDT());
        edtsodienthoai.setEnabled(false);

        if(Home.taiKhoan.getHinhTK() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(Home.taiKhoan.getHinhTK(),0, Home.taiKhoan.getHinhTK().length);
            img_hinhanh.setImageBitmap(bitmap);
        }

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
        final EditText edtmatkhaucu = view.findViewById(R.id.edt_matkhaucu_dmk);
        final EditText edtmatkhaumoi = view.findViewById(R.id.edt_matkhaumoi_dmk);
        final EditText editnlmatkhaumoi = view.findViewById(R.id.edt_nlmatkhaumoi_dmk);
        builder.setView(view);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(edtmatkhaumoi.getText().length() !=0 && edtmatkhaucu.getText().length() != 0 && editnlmatkhaumoi.getText().length() != 0){
                    if(dao.isMatKhau(Home.taiKhoan.getIDTK(), edtmatkhaucu.getText().toString())){
                        if(edtmatkhaumoi.getText().toString().equals(editnlmatkhaumoi.getText().toString())){
                            dao.CapNhatMatKhau(Home.taiKhoan.getIDTK(),edtmatkhaumoi.getText().toString());
                            Toast.makeText(CapNhat.this,"Đổi mật khẩu thành công !",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(CapNhat.this, Home.class));
                            onBackPressed();
                        } else {
                            Toast.makeText(CapNhat.this, "Mật khẩu mới không trùng khớp !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CapNhat.this, "Nhập mật khẩu cũ không đúng !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CapNhat.this, "Nhập dữ liệu chưa đủ !", Toast.LENGTH_SHORT).show();
                }

            }
        }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(CapNhat.this,"Đã hủy đổi mật khẩu !",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    private void enableControl(){
        edtmail.setEnabled(isEnabled);
        edthoten.setEnabled(isEnabled);
        edtngaythangnamsinh.setEnabled(isEnabled);
//        edtgioitinh.setEnabled(isEnabled);
        edtmail.setEnabled(isEnabled);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_capnhat, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iCamera:
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera,REQUEST_CODE_CAMERA);
                return true;
            case R.id.iFolder:
                Intent iFolder = new Intent(Intent.ACTION_PICK);
                iFolder.setType("image/*");
                startActivityForResult(iFolder,REQUEST_CODE_FOLDER);
                return true;
            default:
                return super.onContextItemSelected(item);


        }
    }

    private void AnhXa() {
        dao = new DAO(CapNhat.this);
        edthoten = (EditText) findViewById(R.id.edt_hovaten_cn);
        edtngaythangnamsinh = (EditText) findViewById(R.id.edt_ngaythangnamsinh_cn);
//        edtgioitinh = (EditText) findViewById(R.id.edt_gioitinh_cn);
        edtsodienthoai = (EditText) findViewById(R.id.edt_sodienthoai_cn);
        edtmail = (EditText) findViewById(R.id.edt_mail_cn);
        img_hinhanh = findViewById(R.id.img_user_cn);
        btncapnhat = (Button) findViewById(R.id.btn_capnhat_cn);
        btndoimatkhau = (Button) findViewById(R.id.btn_doimatkhau_cn);


        edthoten.setText(Home.taiKhoan.getHovaTen());
        edtsodienthoai.setText(Home.taiKhoan.getSDT());
        edtmail.setText(Home.taiKhoan.getMaill());
        enableControl();

        registerForContextMenu(img_hinhanh);
        img_hinhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_hinhanh.showContextMenu();
            }
        });


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
                    // chuyen data image view -> mang byte[]
//                    BitmapDrawable bitmapDrawable = (BitmapDrawable) img_hinhanh.getDrawable();
//                    Bitmap bitmap = bitmapDrawable.getBitmap();
//                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArray);
//                    byte[] hinhAnh = byteArray.toByteArray();
                    dao.CapNhatTaiKhoan(Home.taiKhoan.getIDTK(),edthoten.getText().toString(),
                            edtngaythangnamsinh.getText().toString(), edtmail.getText().toString());

                    Toast.makeText(CapNhat.this, "Đã lưu", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    //dao
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_hinhanh.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_hinhanh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
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
