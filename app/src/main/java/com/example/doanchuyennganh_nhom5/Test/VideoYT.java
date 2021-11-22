package com.example.doanchuyennganh_nhom5.Test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoYT {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("snipper")
    @Expose
    private SnipperYT snipper;

    public VideoYT(){

    }

    public VideoYT(String id,SnipperYT snipper)
    {
        this.id = id;
        this.snipper = snipper;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SnipperYT getSnipper() {
        return snipper;
    }

    public void setSnipper(SnipperYT snipper) {
        this.snipper = snipper;
    }
}
