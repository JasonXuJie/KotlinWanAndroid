package com.jason.my.api

import com.jason.my.model.WebData
import com.jason.net.bean.BaseResult
import retrofit2.http.GET

interface MyService {


    @GET("friend/json")
    suspend fun getUsedWeb():BaseResult<List<WebData>>
}