package com.example.doanchuyennganh_nhom5.Test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.d.Account;

public class ResultLogin extends AppCompatActivity {
    private TextView txtUserName, txtQuyen, txtLoaiTK;
    private Account account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_login);
        Intent intent = getIntent();
        account = new Account();
        account = (Account) intent.getSerializableExtra("login");
        addControl();
    }


    private void addControl() {
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        txtQuyen = (TextView) findViewById(R.id.txtQuyen);
        txtLoaiTK = (TextView) findViewById(R.id.txtLoaiTK);
        txtUserName.setText(String.valueOf(account.getSdt()));
        txtQuyen.setText(String.valueOf(account.getQuyen()));
        txtLoaiTK.setText(String.valueOf(account.getLoaiTK()));
    }
}
