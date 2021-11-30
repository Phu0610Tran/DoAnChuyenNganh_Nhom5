package com.example.doanchuyennganh_nhom5.model;

public class Category {
    private String name;
    private String IDcategory;

    public Category(String name, String IDcategory) {
        this.name = name;
        this.IDcategory = IDcategory;
    }

    public String getIDcategory() {
        return IDcategory;
    }

    public void setIDcategory(String IDcategory) {
        this.IDcategory = IDcategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
