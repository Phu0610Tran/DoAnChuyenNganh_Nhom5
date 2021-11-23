package com.example.doanchuyennganh_nhom5.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doanchuyennganh_nhom5.model.DanhMuc;
import com.example.doanchuyennganh_nhom5.model.TaiKhoan;
import com.example.doanchuyennganh_nhom5.model.Video;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DAO {
    public  static SQLiteDatabase database;
    Context icontext;
    DB db;

    public DAO(Context context) {
        db = new DB(context);
        database = db.open();
        icontext = context;
    }


    public Video thongtinvideo(String IDVIDEO){
        Cursor tro = db.Get("SELECT * FROM VIDEO WHERE IDVD = '" + IDVIDEO + "'");
        while (tro.moveToNext()){
            return new Video(
                    tro.getString(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getString(6),
                    tro.getInt(7),
                    tro.getInt(8),
                    tro.getInt(9)
            );
        }
        return null;
    }

    // region Lưu video
    public boolean isTonTaiLuuVideo(int IDTK,String IDVD){
        Cursor tro = db.Get("SELECT * FROM LUUTRU WHERE IDVD = '" + IDVD + "' AND IDTK = " + IDTK );
        while (tro.moveToNext()){
            return true;
        }
        return false;
    }

    public ArrayList<Video> ListLuuVideo(int IDTK){
        ArrayList<Video> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM VIDEO A, LUUTRU B WHERE B.IDTK = " + IDTK + " AND A.IDVD = B.IDVD ORDER BY THOIGIAN DESC");
        while (tro.moveToNext()){
            list.add(new Video(
                    tro.getString(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getString(6),
                    tro.getInt(7),
                    tro.getInt(8),
                    tro.getInt(9)

            ));
        }
        return list;
    }

    public void Luuvideo(int IDTK,String IDVD)
    {
        db.Query("INSERT INTO LUUTRU(IDTK, IDVD, THOIGIAN) VALUES ('" +
                IDTK + "','" + IDVD + "','" + Calendar.getInstance().getTime().toString() + "' )");
    }

    public void XoaLuuvideo(int IDTK,String IDVD)
    {
        db.Query("DELETE  FROM LUUTRU WHERE IDTK = " + IDTK + " AND IDVD = '" + IDVD + "'");
    }
    // endregion

    //region Video
    public boolean isTonTaiVideo(String IDVIDEO){
        Cursor tro = db.Get("SELECT * FROM VIDEO WHERE IDVD = '" + IDVIDEO + "'");
        while (tro.moveToNext()){
            return true;
        }
        return false;
    }
    public void TaoVideo(String IDVIDEO,String BACKGROUND,String THELOAI, String TIEUDE, String NOIDUNG, String TACGIA){

        db.Query("INSERT INTO VIDEO(IDVD, BACKGROUND, THOIGIANLOAD, THELOAI, TIEUDE, NOIDUNG, TACGIA, LUOTXEM, THICH, KHONGTHICH) VALUES ('" +
                IDVIDEO + "','" + BACKGROUND + "','" + Calendar.getInstance().getTime().toString() + "','" +  THELOAI + "','" + TIEUDE + "','" +  NOIDUNG
                + "','" + TACGIA + "'," + 0 + "," + 0 + "," + 0 +  " )");
    }


    public ArrayList<Video> VideoTheoDanhMuc(String DanhMuc){
        ArrayList<Video> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM VIDEO WHERE THELOAI");
        while(tro.moveToNext()){
            list.add(new Video(
                    tro.getString(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getString(6),
                    tro.getInt(7),
                    tro.getInt(8),
                    tro.getInt(9)
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
                    tro.getBlob(6),
                    tro.getString(7),
                    tro.getString(8),
                    tro.getString(9)
                    );
        }
        return null;
    }

    public void TaoTK(String MAIL, String SDT,String MATKHAU,String QUYEN,String LOAITK){
        db.Query("INSERT INTO TAIKHOAN(MAIL, SDT, MATKHAU, QUYEN, LOAITK) VALUES ('" + MAIL + "' , '" +
                SDT + "' , '" + MATKHAU + "' , '" + QUYEN + "' , '" + LOAITK + "')");
    }
    // endregion
}
