package com.jason.square.api

import com.jason.net.bean.BaseResult
import com.jason.square.model.ShareListData
import com.jason.square.model.SquareData
import retrofit2.http.*

interface SquareService {


    @GET("user_article/list/{page}/json")
    suspend fun getSquareList(@Path("page") page:Int):BaseResult<SquareData>


    @POST("lg/user_article/add/json")
    @FormUrlEncoded
    suspend fun share(@Field("title") title:String,@Field("link") link:String):BaseResult<Any>

    @GET("user/{id}/share_articles/{page}/json")
    suspend fun getShareOneList(@Path("id") id:Int,@Path("page") page:Int):BaseResult<ShareListData>
}