package com.jason.qa.api

import com.jason.net.bean.BaseResult
import com.jason.qa.model.QaData
import retrofit2.http.GET
import retrofit2.http.Path

interface QaService {


    @GET("wenda/list/{page}/json ")
    suspend fun loadList(@Path("page") page:Int):BaseResult<QaData>

}