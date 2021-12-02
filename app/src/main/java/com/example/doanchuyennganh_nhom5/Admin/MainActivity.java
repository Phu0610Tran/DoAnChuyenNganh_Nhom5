package com.example.doanchuyennganh_nhom5.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.doanchuyennganh_nhom5.Adapter.GroupAdminAdapter;
import com.example.doanchuyennganh_nhom5.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int Key_QLTK = 0;
    public static final int Key_QLVIDEO = 1;
    public static final int Key_LOGOUT = 2;
    RecyclerView recV_GroupAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recV_GroupAdmin = findViewById(R.id.recV_GroupAdmin);
        ArrayList<Integer> listKey = new ArrayList<>();

        listKey.add(Key_QLTK);
        listKey.add(Key_QLVIDEO);
        listKey.add(Key_LOGOUT);


        GroupAdminAdapter adapter = new GroupAdminAdapter(MainActivity.this,listKey);
        recV_GroupAdmin.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recV_GroupAdmin.setAdapter(adapter);
    }
}