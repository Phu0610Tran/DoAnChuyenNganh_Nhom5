package com.example.doanchuyennganh_nhom5.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.example.doanchuyennganh_nhom5.model.DatePickerFragment;
import com.example.doanchuyennganh_nhom5.model.TaiKhoan;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
// import com.example.doanchuyennganh_nhom5.d.Account;

public class CapNhat extends AppCompatActivity implements View.OnFocusChangeListener{

    EditText edthoten, edtngaythangnamsinh, edtgioitinh, edtsodienthoai, edtmail;
    Button btncapnhat;
    CircleImageView img_hinhanh;
    DAO dao;
    ImageView quaylai_cn;
    //    private Account account;
    private boolean isEnabled;
    Button btndoimatkhau;
    ImageButton ibtn_Camera,ibtn_Folder;
    final int REQUEST_CODE_CAMERA=123;
    final int REQUEST_CODE_FOLDER=456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhat);

        AnhXa();

        Getdata();

        btndoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
        quaylai_cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        edtngaythangnamsinh.setOnFocusChangeListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void Getdata() {
        int id = Home.taiKhoan.getIDTK();
        TaiKhoan taiKhoan = dao.Load(id);

        edthoten.setText(taiKhoan.getHovaTen());
        edtngaythangnamsinh.setText(taiKhoan.getNgaySinh());
        edtmail.setText(taiKhoan.getMaill());
        edtsodienthoai.setText(taiKhoan.getSDT());

        edtsodienthoai.setEnabled(false);
        if (taiKhoan.getHinhTK() == null){
            img_hinhanh.setImageResource(R.drawable.user);
        }else
        {
            byte[] hinhAnh = Home.taiKhoan.getHinhTK();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_hinhanh.setImageBitmap(bitmap);
        }
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
        quaylai_cn = findViewById(R.id.quaylai_cn);
        edthoten = (EditText) findViewById(R.id.edt_hovaten_cn);
        edtngaythangnamsinh = (EditText) findViewById(R.id.edt_ngaythangnamsinh_cn);
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
//                     chuyen data image view -> mang byte[]
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) img_hinhanh.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArray);
                    byte[] hinhAnh = byteArray.toByteArray();

                    dao.CapNhatTaiKhoan(Home.taiKhoan.getIDTK(),edthoten.getText().toString(),
                            edtngaythangnamsinh.getText().toString(), edtmail.getText().toString(),hinhAnh);
                    Getdata();
                    Toast.makeText(CapNhat.this, "Đã lưu", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CapNhat.this,Dangnhap.class));
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
                    Toast.makeText(CapNhat.this," Bạn không cho phép mở camera", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(CapNhat.this," Bạn không cho phép mở folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.edt_ngaythangnamsinh_cn:
                if(b){
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getFragmentManager(), "Ngày sinh");
                }
                break;
        }
    }
}
