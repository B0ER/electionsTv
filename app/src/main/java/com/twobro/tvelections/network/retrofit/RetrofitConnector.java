package com.twobro.tvelections.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnector {
    private static final String sBaseUrl = "http://192.168.1.13:2025";
    private static final String TAG = RetrofitConnector.class.getSimpleName();
    private static final String URL = "http://31.202.52.184:2025/";
    private static volatile Retrofit retrofit;
    private static volatile ServerApi requests;
    private static Gson gson;

    static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitConnector.class) {

                gson = new GsonBuilder()
                        .setLenient()
                        .create();
                retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
        }
        return retrofit;
    }

    public static ServerApi getInstance() {
        if (requests == null) {
            synchronized (RetrofitConnector.class) {
                requests = getRetrofit().create(ServerApi.class);
            }
        }
        return requests;
    }
}