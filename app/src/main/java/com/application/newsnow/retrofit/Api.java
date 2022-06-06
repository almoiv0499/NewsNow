package com.application.newsnow.retrofit;

import com.application.newsnow.model.ListNews;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("svc/topstories/v2/arts.json?api-key=JXFqWu6xeReKqjCNS2GtlA6OEfwhyI9a")
    Call<ListNews> getAllNews();

}
