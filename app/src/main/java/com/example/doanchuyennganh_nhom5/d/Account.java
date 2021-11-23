package com.example.doanchuyennganh_nhom5.d;

import java.io.Serializable;
import java.sql.Date;

public class Account implements Serializable {
    private int Idtk;
    private String Sdt, Hinhdaidien, Gmail, Hovaten;
    private int Quyen;
    private String LoaiTK;
    private Date Ngaysinh;

    public Account(int idtk, String sdt, String hinhdaidien, String gmail, String hovaten, int quyen, String loaiTK, Date ngaysinh) {
        Idtk = idtk;
        Sdt = sdt;
        Hinhdaidien = hinhdaidien;
        Gmail = gmail;
        Hovaten = hovaten;
        Quyen = quyen;
        LoaiTK = loaiTK;
        Ngaysinh = ngaysinh;
    }

    public Account() {
    }

    public int getIdtk() {
        return Idtk;
    }

    public void setIdtk(int idtk) {
        Idtk = idtk;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getHinhdaidien() {
        return Hinhdaidien;
    }

    public void setHinhdaidien(String hinhdaidien) {
        Hinhdaidien = hinhdaidien;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getHovaten() {
        return Hovaten;
    }

    public void setHovaten(String hovaten) {
        Hovaten = hovaten;
    }

    public int getQuyen() {
        return Quyen;
    }

    public void setQuyen(int quyen) {
        Quyen = quyen;
    }

    public String getLoaiTK() {
        return LoaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        LoaiTK = loaiTK;
    }

    public Date getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        Ngaysinh = ngaysinh;
    }
}
