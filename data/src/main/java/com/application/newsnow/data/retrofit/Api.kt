package com.application.newsnow.data.retrofit

import com.application.newsnow.data.model.ListNews
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("svc/topstories/v2/{category}.json?api-key=JXFqWu6xeReKqjCNS2GtlA6OEfwhyI9a")
    suspend fun getNewsByCategory(@Path("category") category: String): ListNews

    @GET("svc/topstories/v2/arts.json?api-key=JXFqWu6xeReKqjCNS2GtlA6OEfwhyI9a")
    suspend fun getNewsForTopNewsScreen(): ListNews

    @GET("svc/topstories/v2/arts.json?api-key=JXFqWu6xeReKqjCNS2GtlA6OEfwhyI9a")
    fun getNewsForSearchScreen(): Observable<ListNews>

}