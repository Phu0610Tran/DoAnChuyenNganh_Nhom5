package com.example.doanchuyennganh_nhom5.model;

import java.util.Date;

public class BinhLuan {
    Integer TaiKhoanBL;
    byte[] HinhBL;
    String NoidungBL;
    Date ThoiGianBL;

    public BinhLuan() {
    }

    public BinhLuan(Integer taiKhoanBL, byte[] hinhBL, String noidungBL, Date thoiGianBL) {
        TaiKhoanBL = taiKhoanBL;
        HinhBL = hinhBL;
        NoidungBL = noidungBL;
        ThoiGianBL = thoiGianBL;
    }

    public Integer getTaiKhoanBL() {
        return TaiKhoanBL;
    }

    public void setTaiKhoanBL(Integer taiKhoanBL) {
        TaiKhoanBL = taiKhoanBL;
    }

    public byte[] getHinhBL() {
        return HinhBL;
    }

    public void setHinhBL(byte[] hinhBL) {
        HinhBL = hinhBL;
    }

    public String getNoidungBL() {
        return NoidungBL;
    }

    public void setNoidungBL(String noidungBL) {
        NoidungBL = noidungBL;
    }

    public Date getThoiGianBL() {
        return ThoiGianBL;
    }

    public void setThoiGianBL(Date thoiGianBL) {
        ThoiGianBL = thoiGianBL;
    }
}
