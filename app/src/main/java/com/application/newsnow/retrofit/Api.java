package com.application.newsnow.retrofit;

import com.application.newsnow.model.ListNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("svc/topstories/v2/{category}.json?api-key=JXFqWu6xeReKqjCNS2GtlA6OEfwhyI9a")
    Call<ListNews> getAllNews(@Path("category") String category);

}
