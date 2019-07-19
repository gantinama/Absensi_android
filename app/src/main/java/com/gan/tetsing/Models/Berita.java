package com.gan.tetsing.Models;


import com.google.gson.annotations.SerializedName;

public class Berita {

    @SerializedName("id_berita")
    private Integer id_berita;
    @SerializedName("judul_berita")
    private String judul_berita;
    @SerializedName("isi_berita")
    private String isi_berita;
    @SerializedName("waktu_posted")
    private String waktu_posted;

    public Integer getId_berita() {
        return id_berita;
    }

    public void setId_berita(Integer id_berita) {
        this.id_berita = id_berita;
    }

    public String getJudul_berita() {
        return judul_berita;
    }

    public void setJudul_berita(String judul_berita) {
        this.judul_berita = judul_berita;
    }

    public String getIsi_berita() {
        return isi_berita;
    }

    public void setIsi_berita(String isi_berita) {
        this.isi_berita = isi_berita;
    }

    public String getWaktu_posted() {
        return waktu_posted;
    }

    public void setWaktu_posted(String waktu_posted) {
        this.waktu_posted = waktu_posted;
    }
}
