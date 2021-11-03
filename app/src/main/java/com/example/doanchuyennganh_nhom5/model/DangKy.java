package com.example.doanchuyennganh_nhom5.model;

import java.io.Serializable;

public class DangKy implements Serializable {
    private String mail;
    private String sdt;
    private int quyen;
    private String loaitk;

    public DangKy(String mail, String sdt, int quyen, String loaitk) {
        this.mail = mail;
        this.sdt = sdt;
        this.quyen = quyen;
        this.loaitk = loaitk;
    }

    public DangKy() {

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

    public String getLoaitk() {
        return loaitk;
    }

    public void setLoaitk(String loaitk) {
        this.loaitk = loaitk;
    }
}
