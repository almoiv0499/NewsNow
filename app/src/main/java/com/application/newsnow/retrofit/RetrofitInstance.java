package com.application.newsnow.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "https://api.nytimes.com/";
    private static RetrofitInstance instance;
    private Api api;

    private RetrofitInstance() {
        RxJava3CallAdapterFactory adapterFactory = RxJava3CallAdapterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(adapterFactory)
                .build();

        api = retrofit.create(Api.class);
    }

    public static RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }

    public Api getApi() {
        return api;
    }
}
