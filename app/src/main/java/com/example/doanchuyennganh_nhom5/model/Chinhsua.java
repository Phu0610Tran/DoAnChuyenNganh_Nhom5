package com.example.doanchuyennganh_nhom5.model;

import java.io.Serializable;

public class Chinhsua implements Serializable {
    private int IDTK;
    private String Mail;
    private String Sdt;
    private String hovaten;
    private String ngaythangnamsinh;
    private String gioitinh;

    public Chinhsua(int IDTK, String mail, String sdt, String hovaten, String ngaythangnamsinh, String gioitinh) {
        this.IDTK = IDTK;
        this.Mail = mail;
        this.Sdt = sdt;
        this.hovaten = hovaten;
        this.ngaythangnamsinh = ngaythangnamsinh;
        this.gioitinh = gioitinh;
    }

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        this.Mail = mail;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        this.Sdt = sdt;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getNgaythangnamsinh() {
        return ngaythangnamsinh;
    }

    public void setNgaythangnamsinh(String ngaythangnamsinh) {
        this.ngaythangnamsinh = ngaythangnamsinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
}

