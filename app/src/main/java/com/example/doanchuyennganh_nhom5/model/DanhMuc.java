package com.example.doanchuyennganh_nhom5.model;

import java.util.ArrayList;


public class DanhMuc {
    private String Ten_DanhMuc;
    private String KEY_LISTVIDEO;

    public DanhMuc(String ten_DanhMuc, String KEY_LISTVIDEO) {
        this.Ten_DanhMuc = ten_DanhMuc;
        this.KEY_LISTVIDEO = KEY_LISTVIDEO;
    }

    public String getTen_DanhMuc() {
        return Ten_DanhMuc;
    }

    public void setTen_DanhMuc(String ten_DanhMuc) {
        Ten_DanhMuc = ten_DanhMuc;
    }

    public String getKEY_LISTVIDEO() {
        return KEY_LISTVIDEO;
    }

    public void setKEY_LISTVIDEO(String KEY_LISTVIDEO) {
        KEY_LISTVIDEO = KEY_LISTVIDEO;
    }
}
