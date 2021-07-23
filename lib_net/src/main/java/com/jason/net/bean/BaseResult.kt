package com.jason.net.bean

data class BaseResult<T>(val errorCode:Int,val errorMsg:String,val data:T)
