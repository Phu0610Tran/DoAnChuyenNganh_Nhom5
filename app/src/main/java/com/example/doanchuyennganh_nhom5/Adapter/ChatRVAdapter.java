package com.example.doanchuyennganh_nhom5.Adapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh_nhom5.Activity.Home;
import com.example.doanchuyennganh_nhom5.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatRVAdapter extends RecyclerView.Adapter {
    private ArrayList<ChatsModel> chatsModelArrayList;

    public ChatRVAdapter(ArrayList<ChatsModel> chatsModelArrayList) {
        this.chatsModelArrayList = chatsModelArrayList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg, parent, false);
                return new UserViewHolder(view);

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatsModel chatsModel = chatsModelArrayList.get(position);
        switch (chatsModel.getSender()){
            case "user":
                ((UserViewHolder)holder).userTV.setText(chatsModel.getMessage());

                break;

            case "bot":
                ((BotViewHolder)holder).botMsgTV.setText(chatsModel.getMessage());
                break;
        }
    }
    @Override
    public int getItemViewType(int position) {
        switch (chatsModelArrayList.get(position).getSender()){
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }
    @Override
    public int getItemCount() {
        return chatsModelArrayList.size();
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userTV;
        CircleImageView user_chat;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userTV = itemView.findViewById(R.id.idTVUser);
            user_chat = itemView.findViewById(R.id.user_chat);
            if(Home.taiKhoan.getIDTK() != -1){
                Bitmap bitmap = BitmapFactory.decodeByteArray(Home.taiKhoan.getHinhTK(),0,Home.taiKhoan.getHinhTK().length);
                user_chat.setImageBitmap(bitmap);
            }

        }
    }
    public static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView botMsgTV;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botMsgTV = itemView.findViewById(R.id.idTVBot);
        }
    }
}
