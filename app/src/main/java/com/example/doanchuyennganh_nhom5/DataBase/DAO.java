package com.example.doanchuyennganh_nhom5.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.doanchuyennganh_nhom5.model.BinhLuan;
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
    public Cursor GetdataALL(){
        Cursor tro = db.Get(" SELECT * FROM VIDEO ");
        return tro;
    }
    public Cursor Getdata(String ten){
        Cursor tro = db.Get("SELECT * FROM VIDEO WHERE TIEUDE LIKE '%" + ten +"%'" );
        return tro;
    }


    public ArrayList<Video> GetlistvideoDM(String KEYDM){
        ArrayList<Video> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM VIDEO WHERE THELOAI ='" + KEYDM + "' ORDER BY THOIGIANLOAD ");
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
    public Video thongtinvideoAdmin(String IDVIDEO){
        Cursor tro = db.Get("SELECT * FROM BANPHU WHERE IDVIDEO = '" + IDVIDEO + "'");
        while (tro.moveToNext()){
            return new Video(
                    tro.getString(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getString(6)
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

    public ArrayList<Video> ListLuuVideoTHELOAI(int IDTK, String THELOAI){
        ArrayList<Video> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM VIDEO A, LUUTRU B WHERE B.IDTK = " + IDTK + " AND A.IDVD = B.IDVD AND THELOAI = '" + THELOAI + "' ORDER BY THOIGIAN DESC");
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
    public ArrayList<Video> TIMKIEM(){
        ArrayList<Video> doAnArrayList = new ArrayList<>();
        String truyvan = "SELECT * FROM VIDEO ";
        Cursor tro = database.rawQuery(truyvan, null);
        if (tro.getCount() != 0) {
            while (tro.moveToNext()){
                doAnArrayList.add(new Video(
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
            return doAnArrayList;
        }
        return doAnArrayList;
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
    public ArrayList<Video> QuanLyVideo(String THELOAI){
        ArrayList<Video> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM VIDEO WHERE THELOAI = '" + THELOAI + "'");
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


    public void XoaVideo(String IDVD){
        db.Query("DELETE FROM VIDEO WHERE IDVD = '" + IDVD + "'");
    }

    public Video TT1Video(String IDVD){
        Cursor tro = db.Get("SELECT * FROM VIDEO WHERE IDVD = '" + IDVD + "'");
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

    public void SuaVideo(String IDVD,String BACKGROUND,String THELOAI, String TIEUDE, String NOIDUNG, String TACGIA){
        db.Query("UPDATE VIDEO SET BACKGROUND = '" + BACKGROUND + "' , THELOAI = '" + THELOAI
                + "' , TIEUDE ='" + TIEUDE + "', NOIDUNG = '" + NOIDUNG + "', TACGIA = '" + TACGIA + "'  WHERE IDVD = '" + IDVD + "'");
    }

    public TaiKhoan Load(int IDTK){

        Cursor tro = db.Get("SELECT * FROM TAIKHOAN WHERE IDTK = " + IDTK);

        while (tro.moveToNext()){

            return new TaiKhoan(tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getBlob(6),
                    tro.getString(7),
                    tro.getString(8)
            );
        }
        return null;
    }
    public int Soluotxem(String IDVD)
    {
        Cursor cursor = db.Get("SELECT LUOTXEM FROM VIDEO WHERE IDVD = '" + IDVD + "'");
        cursor.moveToNext();
        return cursor.getInt(0);

    }
    public int isDaLike(String IDVIDEO, int IDTK){
        Cursor cursor = db.Get("SELECT TT FROM TRANGTHAI WHERE IDVD = '" + IDVIDEO + "' AND IDTK = " + IDTK );
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return -1;
    }

    public int Solike(String IDVIDEO, int THICH){
        if(THICH == 1)
        {
            Cursor cursor = db.Get("SELECT THICH FROM VIDEO WHERE IDVD = '" + IDVIDEO + "'");
            cursor.moveToNext();
            return cursor.getInt(0);

        }else if(THICH == 2)
        {
            Cursor cursor = db.Get("SELECT KHONGTHICH FROM VIDEO WHERE IDVD = '" + IDVIDEO + "'");
            cursor.moveToNext();
            return cursor.getInt(0);
        }
        return -1;
    }

    public void luotxem(String IDVIDEO)
    {
        db.Query("UPDATE VIDEO SET LUOTXEM = LUOTXEM + " + 1 + " WHERE IDVD = '" + IDVIDEO + "'");

    }
    public void them(String IDVIDEO, int IDTK, int THICH){
        if(THICH == 1){
            db.Query("UPDATE VIDEO SET THICH = THICH + " + 1 + " WHERE IDVD = '" + IDVIDEO + "'");
        }
        else if(THICH == 2 )
        {
            db.Query("UPDATE VIDEO SET KHONGTHICH = KHONGTHICH + " + 1 + " WHERE IDVD = '" + IDVIDEO+ "'");
        }

        db.Query("INSERT INTO TRANGTHAI( IDVD, IDTK, TT) VALUES ('" +
                IDVIDEO + "'," + IDTK + " , " + THICH + ")");
    }
    public void xoathem(String IDVIDEO, int IDTK, int THICH){
        if(THICH == 1){
            db.Query("UPDATE VIDEO SET THICH = THICH - " + 1 + " WHERE IDVD = '" + IDVIDEO + "'");
        }
        else if(THICH == 2 )
        {
            db.Query("UPDATE VIDEO SET KHONGTHICH = KHONGTHICH - " + 1 + " WHERE IDVD = '" + IDVIDEO + "'");
        }

        db.Query("DELETE FROM TRANGTHAI WHERE IDVD = '" + IDVIDEO + "' AND IDTK = " + IDTK);
    }
//    public void notdislike(String IDVIDEO, int IDTK){
//        db.Query("INSERT INTO VIDEO( IDVD, KHONGTHICH) VALUES ('" +
//                IDVIDEO + "'," + 0 + ")");
//        db.Query("INSERT INTO TRANGTHAI( IDVD, IDTK, TT) VALUES ('" +
//                IDVIDEO + "'," + IDTK + " , " + 0 + ")");
//    }

    public void tanglike(String IDVIDEO, int IDTK,int Thich){
        db.Query("UPDATE TRANGTHAI SET THICH = THICH " + 1);
        db.Query("INSERT INTO TRANGTHAI( IDVD, IDTK, TT) VALUES ('" +
                IDVIDEO + "'," + IDTK + " , " + 1 + ")");
    }

    public boolean isTonTaiVideo(String IDVIDEO){
        Cursor tro = db.Get("SELECT * FROM VIDEO WHERE IDVD = '" + IDVIDEO + "'");
        while (tro.moveToNext()){
            return true;
        }
        return false;
    }
    public boolean isTonTaiVideoAdmin(String IDVIDEO){
        Cursor tro = db.Get("SELECT * FROM BANPHU WHERE IDVIDEO = '" + IDVIDEO + "'");
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
    public void TaoVideoAdmin(String IDVIDEO,String BACKGROUND,String THELOAI, String TIEUDE, String NOIDUNG, String TACGIA){

        db.Query("INSERT INTO BANPHU (IDVIDEO, BACKGROUP, THOIGIANLOAD, THELOAI, TIEUDE, NOIDUNG, TACGIA) VALUES ('" +
                IDVIDEO + "','" + BACKGROUND + "','" + Calendar.getInstance().getTime().toString() + "','" +  THELOAI + "','" + TIEUDE + "','" +  NOIDUNG
                + "','" + TACGIA + "' )");
    }
    public void XoaVideoAdmin(String IDVD){
        db.Query("DELETE FROM BANPHU WHERE IDVIDEO = '" + IDVD + "'");
    }
    public void XoaVideoALL(){
        db.Query("DELETE FROM BANPHU ");
    }
    public boolean isBP(){
        Cursor tro = db.Get("SELECT * FROM BANPHU ");
        while (tro.moveToNext()){
            return true;
        }
        return false;
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
    public void CapNhatMatKhau(int IDTK, String MatKhau){
        db.Query("UPDATE TAIKHOAN SET MATKHAU = '" + MatKhau + "' WHERE IDTK = " + IDTK);
    }

    public boolean isMatKhau(int IDTK, String MatKhau){
        Cursor tro = db.Get("SELECT * FROM TAIKHOAN WHERE IDTK = " + IDTK + " AND MATKHAU = '" + MatKhau + "'");
        while (tro.moveToNext()){
            return true;
        }
        return false;
    }

    public void CapNhatTaiKhoan(int IDTK, String HOVATEN,String NGAYSINH, String MAIL,byte[] Hinh){
        db.Query("UPDATE TAIKHOAN SET HOVATEN = '" + HOVATEN + "', NGAYSINH = '" + NGAYSINH +
                "', MAIL = '" + MAIL + "' WHERE IDTK = " + IDTK);

        String sql = "UPDATE TAIKHOAN SET HINHDAIDIEN = ? WHERE IDTK="+ IDTK ;
        SQLiteDatabase database = this.db.getWritableDatabase();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1,Hinh);
        statement.executeInsert();

    }

    public boolean isTonTaiTK(String SDT){
        Cursor tro = db.Get("SELECT * FROM TAIKHOAN WHERE SDT = '" + SDT + "'");
        while (tro.moveToNext()){
            return true;
        }
        return false;
    }

    public TaiKhoan DangNhap(String SDT, String MATKHAU){

        Cursor tro = db.Get("SELECT * FROM TAIKHOAN WHERE SDT = '" + SDT + "' AND MATKHAU = '" + MATKHAU + "'");

        while (tro.moveToNext()){
//            Log.e(" test ",
//                    tro.getString(1)+
//                            tro.getString(2)+
//                            tro.getString(3)+
//                            tro.getString(4)+
//                            tro.getString(5)+
//                            tro.getBlob(6)+
//                            tro.getString(7)+
//                            tro.getString(8));
            return new TaiKhoan(tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getBlob(6),
                    tro.getString(7),
                    tro.getString(8)
                    );
        }
        return null;
    }

    public void TaoTK(String SDT, String MAIL,String MATKHAU,String QUYEN,String LOAITK){
        db.Query("INSERT INTO TAIKHOAN (MAIL, SDT, MATKHAU, QUYEN, LOAITK) VALUES ('" + MAIL + "' , '" +
                SDT + "' , '" + MATKHAU + "' , '" + QUYEN + "' , '" + LOAITK + "')");
    }

    public ArrayList<TaiKhoan> QuanLyTaiKhoan(){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM TaiKhoan");
        while (tro.moveToNext()){
            list.add(new TaiKhoan(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getBlob(6),
                    tro.getString(7),
                    tro.getString(8)
            ));
        }
        return list;
    }

    public void XoaTK(int IDTK){
        db.Query("DELETE FROM TAIKHOAN WHERE IDTK = '" + IDTK + "'");
    }

    public TaiKhoan TT1TaiKhoan(int IDTK){
        Cursor tro = db.Get("SELECT * FROM TAIKHOAN WHERE IDTK = '" + IDTK + "'");
        while (tro.moveToNext()){
            return new TaiKhoan(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getBlob(6),
                    tro.getString(7),
                    tro.getString(8)
            );
        }
        return null;
    }

    public void CapNhatTaiKhoan_ADMIN(int IDTK, String MAIL, String SDT, String MATKHAU, String QUYEN, String LOAITK, byte[] BACKGROUND, String HOVATEN, String NGAYSINH){
        db.Query("UPDATE TAIKHOAN SET MAIL = '" + MAIL + "' , SDT ='" + SDT + "', MATKHAU = '" + MATKHAU
                + "', QUYEN = '" + QUYEN + "', LOAITK = '" + LOAITK + "', HOVATEN = '" + HOVATEN
                + "', NGAYSINH = '" + NGAYSINH + "'  WHERE IDTK = '" + IDTK + "'");

        String sql = "UPDATE TAIKHOAN SET HINHDAIDIEN = ? WHERE IDTK= " + IDTK ;
        SQLiteDatabase database = db.getWritableDatabase();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1,BACKGROUND);
        statement.executeInsert();
    }
    // endregion

    public void ThemBL(int IDTK, String IDVD,String NoiDung, String ThoiGian){
        Log.e("Tag","INSERT INTO BINHLUAN (IDTK,IDVD,NOIDUNG,THOIGIAN) VALUES (" +IDTK + ",'" +
                IDVD + "','" + NoiDung+"','" + ThoiGian + "')");
        db.Query("INSERT INTO BINHLUAN (IDTK,IDVD,NOIDUNG,THOIGIAN) VALUES (" +IDTK + ",'" +
                IDVD + "','" + NoiDung+"','" + ThoiGian + "')");
    }

    public ArrayList<BinhLuan> LayBinhLuan(String idvd){
        ArrayList<BinhLuan> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT A.IDTK,B.HINHDAIDIEN,A.NOIDUNG,A.THOIGIAN FROM binhluan A,TAIKHOAN B WHERE A.IDTK = B.IDTK AND A.IDVD ='" + idvd +"'");
        while (tro.moveToNext()){
            list.add(new BinhLuan(
                    tro.getInt(0),
                    tro.getBlob(1),
                    tro.getString(2),
                    tro.getString(3)
            ));
        }
        return list;
    }
}
