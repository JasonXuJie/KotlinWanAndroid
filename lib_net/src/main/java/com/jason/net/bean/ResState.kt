package com.jason.net.bean

import java.lang.Exception

sealed class ResState<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResState<T>()
    data class Fail(val errorMsg: String) : ResState<Nothing>()
}