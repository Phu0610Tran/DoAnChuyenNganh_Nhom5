package com.example.doanchuyennganh_nhom5.Activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.Adapter.ChinhsuaAdapter;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Chinhsua;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChinhSuaActivity extends AppCompatActivity {

    String urlGetData = "https://doanchuyennghanh.000webhostapp.com/getdata.php";

    ListView lv_Chinhsua;
    ArrayList<Chinhsua> arrayList;
    ChinhsuaAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua);

        lv_Chinhsua = (ListView) findViewById(R.id.lv_chinhsua);
        arrayList = new ArrayList<>();
        adapter = new ChinhsuaAdapter(ChinhSuaActivity.this,R.layout.activity_chinh_sua, arrayList);
        lv_Chinhsua.setAdapter(adapter);

        GetData(urlGetData);
    }

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++)
                        {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayList.add(new Chinhsua(
                                        object.getInt("idtk"),
                                        object.getString("MAIL"),
                                        object.getString("SDT"),
                                        object.getString("HOVATEN"),
                                        object.getString("NGAYTHANGNAMSINH"),
                                        object.getString("GIOITINH")

                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChinhSuaActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}

