package com.gan.tetsing.Models.Parsing;

import com.gan.tetsing.Models.Berita;
import com.google.gson.annotations.SerializedName;

public class GetBerita {
    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("berita")
    private Berita berita;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Berita getBerita() {
        return berita;
    }

    public void setBerita(Berita berita) {
        this.berita = berita;
    }
}
