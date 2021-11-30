package com.example.doanchuyennganh_nhom5.model;

import java.io.Serializable;
import java.util.Date;

public class TaiKhoan implements Serializable {
    private int IDTK;
    private String Maill, SDT, MatKhau, Quyen, LoaiTK, HovaTen,GioiTinh;
    private byte[] HinhTK;
    private String NgaySinh;

    public TaiKhoan() {
        IDTK = -1;
    }

    public TaiKhoan(int IDTK, String maill, String SDT, String matKhau, String quyen, String loaiTK, byte[] hinhTK, String hovaTen,String ngaySinh) {
        this.IDTK = IDTK;
        this.Maill = maill;
        this.SDT = SDT;
        this.MatKhau = matKhau;
        this.Quyen = quyen;
        this.LoaiTK = loaiTK;
        this.HinhTK = hinhTK;
        this.HovaTen = hovaTen;
        this.NgaySinh = ngaySinh;
    }

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    public String getMaill() {
        return Maill;
    }

    public void setMaill(String maill) {
        Maill = maill;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getQuyen() {
        return Quyen;
    }

    public void setQuyen(String quyen) {
        Quyen = quyen;
    }

    public String getLoaiTK() {
        return LoaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        LoaiTK = loaiTK;
    }

    public byte[] getHinhTK() {
        return HinhTK;
    }

    public void setHinhTK(byte[]hinhTK) {
        HinhTK = hinhTK;
    }

    public String getHovaTen() {
        return HovaTen;
    }

    public void setHovaTen(String hovaTen) {
        HovaTen = hovaTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }
}
