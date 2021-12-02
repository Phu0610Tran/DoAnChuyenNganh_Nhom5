package com.example.doanchuyennganh_nhom5.model;

import java.io.Serializable;

public class Video implements Serializable {
    String IDVD,HinhVD,ThoiGianLoad, THELOAI,TieuDeVD,NoiDungVD,TacGia;
    int LuotXem, Thich, KhongThich;

    public Video() {
    }

    public Video(String IDVD, String hinhVD,String tieuDeVD, String noiDungVD, String tacGia){
        this.IDVD = IDVD;
        HinhVD = hinhVD;
        TieuDeVD = tieuDeVD;
        NoiDungVD = noiDungVD;
        TacGia = tacGia;
    }

    public Video(String IDVD, String hinhVD, String thoiGianLoad, String THELOAI, String tieuDeVD, String noiDungVD, String tacGia, int luotXem, int thich, int khongThich) {
        this.IDVD = IDVD;
        HinhVD = hinhVD;
        ThoiGianLoad = thoiGianLoad;
        this.THELOAI = THELOAI;
        TieuDeVD = tieuDeVD;
        NoiDungVD = noiDungVD;
        TacGia = tacGia;
        LuotXem = luotXem;
        Thich = thich;
        KhongThich = khongThich;
    }
    public Video(String IDVD, String hinhVD, String thoiGianLoad, String THELOAI, String tieuDeVD, String noiDungVD, String tacGia) {
        this.IDVD = IDVD;
        HinhVD = hinhVD;
        ThoiGianLoad = thoiGianLoad;
        this.THELOAI = THELOAI;
        TieuDeVD = tieuDeVD;
        NoiDungVD = noiDungVD;
        TacGia = tacGia;
    }

    public String getIDVD() {
        return IDVD;
    }

    public void setIDVD(String IDVD) {
        this.IDVD = IDVD;
    }

    public String getHinhVD() {
        return HinhVD;
    }

    public void setHinhVD(String hinhVD) {
        HinhVD = hinhVD;
    }

    public String getThoiGianLoad() {
        return ThoiGianLoad;
    }

    public void setThoiGianLoad(String thoiGianLoad) {
        ThoiGianLoad = thoiGianLoad;
    }

    public String getTHELOAI() {
        return THELOAI;
    }

    public void setTHELOAI(String THELOAI) {
        this.THELOAI = THELOAI;
    }

    public String getTieuDeVD() {
        return TieuDeVD;
    }

    public void setTieuDeVD(String tieuDeVD) {
        TieuDeVD = tieuDeVD;
    }

    public String getNoiDungVD() {
        return NoiDungVD;
    }

    public void setNoiDungVD(String noiDungVD) {
        NoiDungVD = noiDungVD;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public int getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(int luotXem) {
        LuotXem = luotXem;
    }

    public int getThich() {
        return Thich;
    }

    public void setThich(int thich) {
        Thich = thich;
    }

    public int getKhongThich() {
        return KhongThich;
    }

    public void setKhongThich(int khongThich) {
        KhongThich = khongThich;
    }
}

