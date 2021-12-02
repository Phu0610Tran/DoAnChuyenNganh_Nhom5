package com.example.doanchuyennganh_nhom5.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.Adapter.ChatRVAdapter;
import com.example.doanchuyennganh_nhom5.Adapter.ChatsModel;
import com.example.doanchuyennganh_nhom5.Adapter.MsgModal;
import com.example.doanchuyennganh_nhom5.Adapter.RetrofitAPI;
import com.example.doanchuyennganh_nhom5.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class HoTroKhachHang extends AppCompatActivity {
    private RecyclerView chatsRV;
    private ImageButton sendMsgIB;
    private EditText userMsgEdt;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private RequestQueue mRequestQueue;
    private ArrayList<ChatsModel> chatsModelArrayList;
    private ChatRVAdapter chatRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_tro_khach_hang);
        AnhXa();

        mRequestQueue = Volley.newRequestQueue(HoTroKhachHang.this);
        mRequestQueue.getCache().clear();

        chatsModelArrayList = new ArrayList<>();
        chatRVAdapter = new ChatRVAdapter(chatsModelArrayList);
        chatsRV.setLayoutManager(new LinearLayoutManager(this));
        chatsRV.setAdapter(chatRVAdapter);

        SuKien();
    }

    private void SuKien() {
        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userMsgEdt.getText().toString().isEmpty()) {
                    Toast.makeText(HoTroKhachHang.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdt.getText().toString());
                userMsgEdt.setText("");
            }
        });
    }

    private void AnhXa() {
        chatsRV = findViewById(R.id.idRVChats);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);
    }

    private void getResponse(String message) {
        chatsModelArrayList.add(new ChatsModel(message, USER_KEY));
        chatRVAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=160564&key=DZDqBwRAsjpq8RI4&uid=[uid]&msg="+message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MsgModal> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if(response.isSuccessful()){
                    MsgModal modal = response.body();
                    chatsModelArrayList.add(new ChatsModel(modal.getCnt(), BOT_KEY));
                    chatRVAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(HoTroKhachHang.this, "LỖI RỒI", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {
                chatsModelArrayList.add(new ChatsModel(t.getMessage(), BOT_KEY));
                chatRVAdapter.notifyDataSetChanged();
            }
        });
    }
}