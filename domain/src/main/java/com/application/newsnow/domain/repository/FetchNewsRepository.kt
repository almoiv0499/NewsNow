package com.application.newsnow.domain.repository

import com.application.newsnow.domain.model.ListNews

interface FetchNewsRepository {

    suspend fun fetchNews(): ListNews

}