package com.jason.home.api

import com.jason.home.model.ArtData
import com.jason.home.model.BannerData
import com.jason.home.model.TopData
import com.jason.net.bean.BaseResult
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {


    //首页文章列表
    @GET("article/list/{page}/json")
    suspend fun getArtList(@Path("page") page:Int):BaseResult<ArtData>

    //首页Banner
    @GET("banner/json")
    suspend fun getBanner():BaseResult<List<BannerData>>

    //置顶文章
    @GET("article/top/json")
    suspend fun getTopList():BaseResult<List<TopData>>

}