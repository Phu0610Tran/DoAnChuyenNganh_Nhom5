package com.example.doanchuyennganh_nhom5.Adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.doanchuyennganh_nhom5.DataBase.DAO;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.DanhMuc;
import com.example.doanchuyennganh_nhom5.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.DanhMucHolder> {
    ArrayList<DanhMuc> ListCategory;
    Context context;
    public DanhMucAdapter(Context context, ArrayList<DanhMuc> listCategory) {
        ListCategory = listCategory;
        this.context = context;
    }
    @NonNull
    @Override
    public DanhMucHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_dsvideo, null);
        return new DanhMucHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull DanhMucHolder holder, int position) {
        String tieude = ListCategory.get(position).getTen_DanhMuc();

        DAO dao = new DAO(context);
        ArrayList<Video> listVideo = dao.GetlistvideoDM(ListCategory.get(position).getKEY_LISTVIDEO());

        VideoAdapter videoAdapter = new VideoAdapter(context,listVideo);
        videoAdapter.setContext(context);
//        Log.e("Errỏ",Home.URL_GETJSON + ListCategory.get(position).getKEY_LISTVIDEO() + Home.KEY_API);
        GetJsonYouTube(Home.URL_GETJSON + ListCategory.get(position).getKEY_LISTVIDEO() + Home.KEY_API,videoAdapter);

        Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
        holder.txt_TieuDeDM.setText(tieude);
        holder.rec_DSVideo.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.rec_DSVideo.setAdapter(videoAdapter);
    }
    @Override
    public int getItemCount() {
        if(ListCategory.size() != 0 ){
            return ListCategory.size();
        }
        return 0;
    }
    private void GetJsonYouTube(String url, VideoAdapter videoAdapter){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("d","d");
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
                        Log.e("T", title);
                        videoYouTubeArrayList.add( new Video(idVideo, url, title, description, videoOwnerChannelTitle ));
                    }
                    videoAdapter.setListVideo(videoYouTubeArrayList);
                    videoAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "ssssssssss", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }
    public class DanhMucHolder extends RecyclerView.ViewHolder{
        TextView txt_TieuDeDM,txt_XemThem;
        RecyclerView rec_DSVideo;
        public DanhMucHolder(@NonNull View itemView) {
            super(itemView);
            txt_TieuDeDM = itemView.findViewById(R.id.txt_TieuDeDM);
            txt_XemThem = itemView.findViewById(R.id.txt_XemThem);
            rec_DSVideo = itemView.findViewById(R.id.rec_DSVideo);
        }
    }
}
