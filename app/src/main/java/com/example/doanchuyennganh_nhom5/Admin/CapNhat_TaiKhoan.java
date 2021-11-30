package com.example.doanchuyennganh_nhom5.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.TaiKhoan;
import com.example.doanchuyennganh_nhom5.model.Video;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class CapNhat_TaiKhoan extends AppCompatActivity {

    EditText edthoten_qltk, edtngaythangnamsinh_qltk, edtemail_qltk, edtsodienthoai_qltk,
            edtmatkhau_qltk, edtquyen_qltk, edtloaitk_qltk, edtidtk_qltk;
    Button btncapnhat_qltk;
    CircleImageView img_hinhanh_qltk;
    DAO dao;
    //    private Account account;
    private boolean isEnabled;
    final int REQUEST_CODE_CAMERA=123;
    final int REQUEST_CODE_FOLDER=456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_tai_khoan);

        AnhXa();
        Intent intent = getIntent();
        int IDTK = intent.getIntExtra("IDTK", 1);

        if (String.valueOf(IDTK) == null){
            return;
        }
        TaiKhoan taiKhoan = dao.TT1TaiKhoan(IDTK);

        edtidtk_qltk.setText(String.valueOf(IDTK));
        edtidtk_qltk.setEnabled(false);
        byte[] hinhAnh = taiKhoan.getHinhTK();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        img_hinhanh_qltk.setImageBitmap(bitmap);
        edtsodienthoai_qltk.setText(taiKhoan.getSDT());
        edtsodienthoai_qltk.setEnabled(false);
        edthoten_qltk.setText(taiKhoan.getHovaTen());
        edtemail_qltk.setText(taiKhoan.getMaill());
        edtmatkhau_qltk.setText(taiKhoan.getMatKhau());
        edtngaythangnamsinh_qltk.setText(taiKhoan.getNgaySinh());
        edtloaitk_qltk.setText(taiKhoan.getLoaiTK());
        edtquyen_qltk.setText(taiKhoan.getQuyen());

    }

    private void enableControl(){
        img_hinhanh_qltk.setEnabled(isEnabled);
        edthoten_qltk.setEnabled(isEnabled);
        edtemail_qltk.setEnabled(isEnabled);
        edtmatkhau_qltk.setEnabled(isEnabled);
        edtngaythangnamsinh_qltk.setEnabled(isEnabled);
        edtloaitk_qltk.setEnabled(isEnabled);
        edtquyen_qltk.setEnabled(isEnabled);
    }

    private void AnhXa() {
        dao = new DAO(this);
        img_hinhanh_qltk = findViewById(R.id.img_user_ql_taikhoan);
        edtidtk_qltk = findViewById(R.id.edt_idtk_ql_taikhoan);
        edtsodienthoai_qltk = findViewById(R.id.edt_sodienthoai_ql_taikhoan);
        edthoten_qltk = findViewById(R.id.edt_hovaten_ql_taikhoan);
        edtngaythangnamsinh_qltk = findViewById(R.id.edt_ngaythangnamsinh_ql_taikhoan);
        edtemail_qltk = findViewById(R.id.edt_mail_ql_taikhoan);
        edtmatkhau_qltk = findViewById(R.id.edt_matkhau_ql_taikhoan);
        edtquyen_qltk = findViewById(R.id.edt_quyen_ql_taikhoan);
        edtloaitk_qltk = findViewById(R.id.edt_loaitk_ql_taikhoan);
        enableControl();

        registerForContextMenu(img_hinhanh_qltk);
        img_hinhanh_qltk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_hinhanh_qltk.showContextMenu();
            }
        });

        btncapnhat_qltk = findViewById(R.id.btn_capnhat_ql_taikhoan);
        btncapnhat_qltk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnabled = !isEnabled;
                enableControl();
                if (isEnabled){
                    btncapnhat_qltk.setText("Lưu");
                }
                else{
                    btncapnhat_qltk.setText("Cập nhật");

                    BitmapDrawable bitmapDrawable = (BitmapDrawable) img_hinhanh_qltk.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                    byte[] hinhAnh = byteArray.toByteArray();

                    int idtk = Integer.parseInt(edtidtk_qltk.getText().toString());
                    String hovaten = edthoten_qltk.getText().toString();
                    String email = edtemail_qltk.getText().toString();
                    String sdt = edtsodienthoai_qltk.getText().toString();
                    String matkhau = edtmatkhau_qltk.getText().toString();
                    String quyen = edtquyen_qltk.getText().toString();
                    String loaitk = edtloaitk_qltk.getText().toString();
                    String ngaysinh = edtngaythangnamsinh_qltk.getText().toString();

                    if (edtsodienthoai_qltk.getText().length() != 0 && edthoten_qltk.getText().length() != 0 && edtngaythangnamsinh_qltk.getText().length() != 0
                            && edtemail_qltk.getText().length() != 0 && edtmatkhau_qltk.getText().length() !=0 && edtquyen_qltk.getText().length() !=0 && edtloaitk_qltk.getText().length() !=0) {
                        dao.CapNhatTaiKhoan_ADMIN(idtk, email, sdt, matkhau, quyen, loaitk, hinhAnh, hovaten, ngaysinh);
                        Toast.makeText(CapNhat_TaiKhoan.this, "Đã lưu tài khoản !", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(CapNhat_TaiKhoan.this, "Hãy nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CODE_CAMERA);
                }else
                {
                    Toast.makeText(CapNhat_TaiKhoan.this,"Không cho phép mở Camera", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_CODE_FOLDER:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
                }else
                {
                    Toast.makeText(CapNhat_TaiKhoan.this," Không cho phép mở folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_hinhanh_qltk.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_hinhanh_qltk.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
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
}