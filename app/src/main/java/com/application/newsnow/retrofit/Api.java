package com.application.newsnow.retrofit;

import com.application.newsnow.model.ListNews;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("v2/everything?q=bitcoin&apiKey=a3248496b7db4d28bf856fce02fc7872")
    Call<ListNews> getAllNews();

}
