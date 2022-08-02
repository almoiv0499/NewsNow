package com.application.newsnow.retrofit

import com.application.newsnow.model.ListNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("svc/topstories/v2/{category}.json?api-key=JXFqWu6xeReKqjCNS2GtlA6OEfwhyI9a")
    fun getNewsByCategory(@Path("category") category: String): Call<ListNews>

    @GET("svc/topstories/v2/arts.json?api-key=JXFqWu6xeReKqjCNS2GtlA6OEfwhyI9a")
    suspend fun getNewsForTopNewsScreen(): ListNews
}