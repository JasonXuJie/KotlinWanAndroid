package com.jason.login.api

import com.jason.login.model.LoginData
import com.jason.login.model.RegisterData
import com.jason.net.bean.BaseResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {


    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username : String,@Field("password") password:String):BaseResult<LoginData>


    @POST("user/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username:String,
        @Field("password") password:String ,
        @Field("repassword") repassword:String):BaseResult<RegisterData>

    @GET("user/logout/json")
    suspend fun logout():BaseResult<Any>


}