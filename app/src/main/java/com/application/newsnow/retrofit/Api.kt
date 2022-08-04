package com.application.newsnow.retrofit

import com.application.newsnow.model.ListNews
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("svc/topstories/v2/{category}.json")
    fun getNewsByCategory(
        @Path("category") category: String,
        @Query("api-key") apiKey: String
    ): Call<ListNews>

    @GET("svc/topstories/v2/arts.json")
    suspend fun getNewsForTopNewsScreen(@Query("api-key") apiKey: String): ListNews

    @GET("svc/topstories/v2/arts.json")
    fun getNewsForSearchScreen(@Query("api-key") apiKey: String): Observable<ListNews>

}