package com.vrushabh.quotehive.data.api

import com.vrushabh.quotehive.data.models.QuoteListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface QuotesAPI {

    @GET("/v3/b/679b6a50acd3cb34a8d56bf6?meta=false")
    suspend fun getQuotes(@Header("X-JSON-Path") category: String): Response<List<QuoteListItem>>

    @GET("/v3/b/679b6a50acd3cb34a8d56bf6?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories(): Response<List<String>>
}