package com.example.doanchuyennganh_nhom5.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doanchuyennganh_nhom5.d.Account;
import com.example.doanchuyennganh_nhom5.model.TaiKhoan;
import com.example.doanchuyennganh_nhom5.model.Video;

import java.util.ArrayList;
import java.util.Date;

public class DAO {
    SQLiteDatabase database;
    Context icontext;
    DB db;

    public DAO(Context context) {
        db = new DB(context);
        database = db.open();
        icontext = context;
    }

    //region Video
    public void TaoVideo(String IDVIDEO,String BACKGROUND,String THELOAI, String TIEUDE, String NOIDUNG, String TACGIA){
        db.Query("INSERT INTO VIDEO(IDVIDEO, BACKGROUND, THELOAI, TIEUDE, NOIDUNG, TACGIA) VALUES (" +
                IDVIDEO + " , " + BACKGROUND + " , " +  THELOAI + " , " + TIEUDE + " , " +  NOIDUNG
                + " , " + TACGIA + " )");
    }


    public ArrayList<Video> VideoTheoDanhMuc(String DanhMuc){
        ArrayList<Video> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM VIDEO WHERE THELOAI");
        while(tro.moveToNext()){
            list.add(new Video(
                    tro.getString(0),
                    tro.getString(0),
                    tro.getString(1)
            ));
        }
        return list;
    }
    // endregion

    // region Tài Khoản
    public boolean isTonTaiTK(String IDTK){
        Cursor tro = db.Get("SELECT * FROM TAIKHOAN WHERE IDTK = '" + IDTK + "'");
        while (tro.moveToNext()){
            return true;
        }
        return false;
    }

    public TaiKhoan DangNhap(String SDT, String MATKHAU){

        Cursor tro = db.Get("SELECT * FROM TAIKHOAN WHERE SDT = '" + SDT + "' AND MATKHAU = '" + MATKHAU + "'");
        while (tro.moveToNext()){
            return new TaiKhoan(tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getString(6),
                    tro.getString(7),
                    tro.getString(8),
                    tro.getString(9)
                    );
        }
        return null;
    }

    public void TaoTK(String MAIL, String SDT,String MATKHAU,String QUYEN,String LOAITK){
        db.Query("INSERT INTO TAIKHOAN(IDTK,)" );
    }

    // endregion
}
