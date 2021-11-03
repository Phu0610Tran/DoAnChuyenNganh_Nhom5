package com.example.doanchuyennganh_nhom5.model;

public class Loaisp {
    public int Id;
    public String Tenloaisp;


    public Loaisp(int id, String tenloaisp) {
        Id = id;
        Tenloaisp = tenloaisp;

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenloaisp() {
        return Tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        Tenloaisp = tenloaisp;
    }




}
