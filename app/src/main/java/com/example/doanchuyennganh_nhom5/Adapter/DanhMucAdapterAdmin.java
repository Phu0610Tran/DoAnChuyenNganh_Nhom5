package com.example.doanchuyennganh_nhom5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanchuyennganh_nhom5.Activity.Home;
import com.example.doanchuyennganh_nhom5.Admin.QL_Video;
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.DanhMuc;
import com.example.doanchuyennganh_nhom5.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanhMucAdapterAdmin extends RecyclerView.Adapter<DanhMucAdapterAdmin.DanhMucHolder>{
    ArrayList<DanhMuc> ListCategory, ListCategoryOLD;
    Context context;
    DAO dao;

    public DanhMucAdapterAdmin(Context context, ArrayList<DanhMuc> listCategory) {
        ListCategory = listCategory;
        ListCategoryOLD= listCategory;
        this.context = context;
    }

    @NonNull
    @Override
    public DanhMucAdapterAdmin.DanhMucHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_listvideo, null);
        return new DanhMucAdapterAdmin.DanhMucHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucAdapterAdmin.DanhMucHolder holder, int position) {
        String tieude = ListCategory.get(position).getTen_DanhMuc();
        VideoAdminAdapter videoAdminAdapter = new VideoAdminAdapter();
        videoAdminAdapter.setContext(context);

        GetJsonYouTube(Home.URL_GETJSON + ListCategory.get(position).getKEY_LISTVIDEO() + Home.KEY_API,videoAdminAdapter);
        Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
        holder.txt_TieuDeDM.setText(tieude);
        holder.rec_DSVideo.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.rec_DSVideo.setAdapter(videoAdminAdapter);

        holder.btn_listvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i < videoAdminAdapter.getListVideo().size();i++){
                    Video vo = videoAdminAdapter.getListVideo().get(i);
                    if(dao.isTonTaiVideo(vo.getIDVD()) == false){
                        dao.TaoVideo(vo.getIDVD(), vo.getHinhVD(), "CHUAXETDUYET", vo.getTieuDeVD().replaceAll("'","''"), vo.getNoiDungVD().replaceAll("'","''"), vo.getTacGia());
                        dao.XoaVideoALL();
                        Toast.makeText(context, "Thêm tất cả thành công ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, QL_Video.class));
                    }
                }
            }
        });
        if (dao.isBP()){
            holder.huy_themlistvideo.setText(" HỦY");
        }
        else
        {
            holder.huy_themlistvideo.setText("LƯU");
        }
        holder.huy_themlistvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dao.isBP()){

                    dao.XoaVideoALL();
                    context.startActivity(new Intent(context, QL_Video.class));
                }else
                {

                    context.startActivity(new Intent(context, QL_Video.class));
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        if(ListCategory.size() != 0 ){
            return ListCategory.size();
        }
        return 0;
    }

    private void GetJsonYouTube(String url, VideoAdminAdapter videoAdminAdapter){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonItems = response.getJSONArray("items");
                    String title = ""; String url = ""; String idVideo=""; String description="";String videoOwnerChannelTitle="";
                    ArrayList<Video> videoYouTubeArrayList = new ArrayList<>();
                    for (int i = 0; i < jsonItems.length(); i++)
                    {
                        JSONObject jsonItem = jsonItems.getJSONObject(i);
                        JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");

                        title = jsonSnippet.getString("title");
                        description = jsonSnippet.getString("description");
                        videoOwnerChannelTitle = jsonSnippet.getString("videoOwnerChannelTitle");
                        JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");

                        JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                        url = jsonMedium.getString("url");

                        JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");
                        idVideo = jsonResourceID.getString("videoId");

                        if (dao.isTonTaiVideo(idVideo)==false)
                        {
                            videoYouTubeArrayList.add( new Video(idVideo, url, title, description, videoOwnerChannelTitle ));
                        }
                    }

                    videoAdminAdapter.setListVideo(videoYouTubeArrayList);
                    videoAdminAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }


    public class DanhMucHolder extends RecyclerView.ViewHolder{
        TextView txt_TieuDeDM;
        RecyclerView rec_DSVideo;
        Button btn_listvideo,huy_themlistvideo;

        public DanhMucHolder(@NonNull View itemView) {
            super(itemView);
            txt_TieuDeDM = itemView.findViewById(R.id.txt_TieuDeDM);
            rec_DSVideo = itemView.findViewById(R.id.rec_DSVideo);
            dao = new DAO(context);
            btn_listvideo = itemView.findViewById(R.id.btn_listvideo);
            huy_themlistvideo=itemView.findViewById(R.id.huy_themlistvideo);
        }
    }
}

