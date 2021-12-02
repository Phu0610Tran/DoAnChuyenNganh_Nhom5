package com.example.doanchuyennganh_nhom5.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.doanchuyennganh_nhom5.Adapter.TimKiemAdapter;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Video;

import java.util.ArrayList;
import java.util.Locale;

public class TimKiem extends AppCompatActivity {
    private RecyclerView rcv_TK;
    EditText edt_tk;
    ImageButton img_search_Tk,img_voice_Tk;
    ArrayList<Video> videoArrayList;
    DAO dao;
    TimKiemAdapter adapter;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        Anhxa();

        videoArrayList = new ArrayList<>();
        adapter = new TimKiemAdapter(TimKiem.this, videoArrayList);
        rcv_TK.setAdapter(adapter);
        rcv_TK.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        SuKien();
    }

    private void SuKien() {
        edt_tk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                GetDataALL();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                GetData(edt_tk.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        img_voice_Tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }

    @Override
    protected void onStart() {
        GetDataALL();
        super.onStart();
    }

    private void GetDataALL() {
        Cursor cursor = dao.GetdataALL();
        videoArrayList.clear();
        while (cursor.moveToNext())
        {
            videoArrayList.add(new Video(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            ));
        }
        adapter.notifyDataSetChanged();
    }

    private void GetData(String ten) {
        Cursor cursor = dao.Getdata(ten);
        videoArrayList.clear();
        while (cursor.moveToNext())
        {
            videoArrayList.add(new Video(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            ));
        }
        adapter.notifyDataSetChanged();
    }

    private MenuItem speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if (resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edt_tk.setText(result.get(0));
                }
                break;
            }
        }
    }

    private void Anhxa() {
        dao = new DAO(this);
        rcv_TK = findViewById(R.id.rcv_TK);
        edt_tk = findViewById(R.id.edt_tk);
        img_search_Tk = findViewById(R.id.img_search_Tk);
        img_voice_Tk = findViewById(R.id.img_voice_Tk);
    }
}