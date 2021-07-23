package com.jason.system.api

import com.jason.net.bean.BaseResult
import com.jason.system.model.NavData
import com.jason.system.model.SysArticleData
import com.jason.system.model.SysTree
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SystemService {


    @GET("navi/json")
    suspend fun getNavList():BaseResult<List<NavData>>


    @GET("tree/json")
    suspend fun getSysTree():BaseResult<List<SysTree>>

    @GET("article/list/{page}/json")
    suspend fun getArtList(@Path("page") page:Int,@Query("cid") cid:Int):BaseResult<SysArticleData>
}