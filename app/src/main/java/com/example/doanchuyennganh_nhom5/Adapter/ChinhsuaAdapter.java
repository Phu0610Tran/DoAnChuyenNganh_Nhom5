package com.example.doanchuyennganh_nhom5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.doanchuyennganh_nhom5.Activity.Capnhat;
import com.example.doanchuyennganh_nhom5.R;
import com.example.doanchuyennganh_nhom5.model.Chinhsua;

import java.util.List;

public class ChinhsuaAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Chinhsua> chinhSuaActivityList;

    public ChinhsuaAdapter(Context context, int layout, List<Chinhsua> chinhSuaActivityList) {
        this.context = context;
        this.layout = layout;
        this.chinhSuaActivityList = chinhSuaActivityList;
    }

    @Override
    public int getCount() {
        return chinhSuaActivityList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        EditText txthovaten, txtngaythangnamsinh, txtgioitinh, txtsodienthoai, txtmail;
        ImageButton imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txthovaten = (EditText) view.findViewById(R.id.edt_hovaten_cn);
            holder.txtngaythangnamsinh = (EditText) view.findViewById(R.id.edt_ngaythangnamsinh_cn);
//            holder.txtgioitinh = (EditText) view.findViewById(R.id.edt_gioitinh_cn);
            holder.txtsodienthoai = (EditText) view.findViewById(R.id.edt_sodienthoai_cn);
            holder.txtmail = (EditText) view.findViewById(R.id.edt_mail_cn);
            //holder.imgEdit = (EditText) view.findViewById(R.id.imgb_Suathongtin);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        Chinhsua chinhSua = chinhSuaActivityList.get(i);
        holder.txthovaten.setText(String.valueOf(chinhSua.getHovaten()));
        holder.txtngaythangnamsinh.setText("Năm sinh: " + chinhSua.getNgaythangnamsinh());
        holder.txtgioitinh.setText(String.valueOf(chinhSua.getGioitinh()));
        holder.txtsodienthoai.setText(String.valueOf(chinhSua.getSdt()));
        holder.txtmail.setText(String.valueOf(chinhSua.getMail()));

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Capnhat.class);
                intent.putExtra("data", chinhSua);
                context.startActivity(intent);
            }
        });
        return view;
    }
}

