package com.application.newsnow.retrofit;

import com.application.newsnow.enums.ApiEnum;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static RetrofitInstance instance;
    private Api api;

    private RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiEnum.BASE_URL.getValue())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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
