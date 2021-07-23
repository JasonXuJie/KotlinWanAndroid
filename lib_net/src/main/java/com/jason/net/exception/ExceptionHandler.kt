package com.jason.net.exception

import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

object ExceptionHandler {

    fun handleException(e:Throwable):String{
        val msg:String
        if (e is SocketTimeoutException){
            msg = "网络超时"
        }else if (e is HttpException){
            msg = "网络错误"
        }else if (e is ConnectException){
            msg = "连接失败"
        }else if (e is JsonParseException
            || e is JSONException
            || e is ParseException || e is MalformedJsonException
        ){
            msg = "解析错误"
        }else if (e is ApiException){
            msg = e.errorMsg
        }else{
            msg = "未知错误"
        }
        return msg
    }
}