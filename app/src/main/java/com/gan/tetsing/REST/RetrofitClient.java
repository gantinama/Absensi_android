package com.gan.tetsing.REST;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://3if1kelompok6.000webhostapp.com/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            OkHttpClient client = UnSafeHTTPClient.getUnsafeOkHttpClient();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
