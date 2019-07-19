package com.gan.tetsing.REST;

import com.gan.tetsing.Models.CRUDMessage;
import com.gan.tetsing.Models.Parsing.GetAllBerita;
import com.gan.tetsing.Models.Parsing.GetBerita;
import com.gan.tetsing.Models.Parsing.GetLogin;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {
    @GET("_getLogin.php")
    Call<GetLogin> getLogin(
            @QueryMap Map<String, String> params
    );

    @GET("_getAllBerita.php")
    Call<GetAllBerita> getAllBerita();

    @FormUrlEncoded
    @POST("_getBerita.php")
    Call<GetBerita> getBerita(
            @Field("id_berita") String idBerita
    );

    @FormUrlEncoded
    @POST("_insertBerita.php")
    Call<CRUDMessage> insertBerita(
            @Field("judul_berita") String judulBerita,
            @Field("isi_berita") String isiBerita
    );

    @FormUrlEncoded
    @POST("_updateBerita.php")
    Call<CRUDMessage> editBerita(
            @Field("id_berita") String idBerita,
            @Field("judul_berita") String judulBerita,
            @Field("isi_berita") String isiBerita
    );

    @FormUrlEncoded
    @POST("_deleteBerita.php")
    Call<CRUDMessage> deleteBerita(
            @Field("id_berita") String idBerita
    );
}
