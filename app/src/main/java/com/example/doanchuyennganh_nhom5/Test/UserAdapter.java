package com.example.doanchuyennganh_nhom5.Test;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.Activity.PlayvideoActivity;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHoder> implements Filterable {
    public static List<Video> mListUsers;
    private List<Video> mListUsersOld;
    private Context mContext;
    public UserAdapter(Context context, List<Video> mListUsers){
        this.mContext = context;
        this.mListUsers = mListUsers;
        this.mListUsersOld = mListUsers;
    }
    @NonNull
    @Override
    public UserViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itemtimkiem, parent, false);
        return new UserViewHoder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHoder holder, int position) {
        Video video = mListUsers.get(position);
        if(video == null){
            return;
        }
        holder.tvName.setText(video.getTieuDeVD());
        holder.tvAddress.setText(video.getTacGia());
        Picasso.with(mContext).load(video.getHinhVD())
                .placeholder(R.drawable.logodt)
                .error(R.drawable.logodt).into(holder.imgUser);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(video);
            }
        });
    }
    private void onClickGoToDetail(Video video){
        Intent intent = new Intent(mContext, PlayvideoActivity.class);
        intent.putExtra("ID_VIDEO",video.getIDVD());
        mContext.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(mListUsers != null){
            return mListUsers.size();
        }
        return 0;
    }
    public class UserViewHoder extends RecyclerView.ViewHolder{
        private CircleImageView imgUser;
        private TextView tvName;
        private TextView tvAddress;
        private RelativeLayout layoutItem;
        public UserViewHoder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mListUsers = mListUsersOld;
                }else {
                    List<Video> list = new ArrayList<>();
                    for(Video video : mListUsersOld){
                        if(video.getTieuDeVD().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(video);
                        }
                    }
                    mListUsers = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListUsers;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListUsers = (List<Video>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
