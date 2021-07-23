package com.jason.common.base

import com.jason.net.bean.BaseResult
import com.jason.net.bean.ResState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

open class BaseRepository {

    suspend fun <T:Any> execute(resp:BaseResult<T>,
                                successBlock:(suspend CoroutineScope.() -> Unit)? = null,
                                failBlock:(suspend CoroutineScope.() -> Unit)? = null): ResState<T> {
        return coroutineScope {
            if (resp.errorCode == 0){
                successBlock?.let { it() }
                ResState.Success(resp.data!!)
            }else{
                failBlock?.let { it() }
                ResState.Fail(resp.errorMsg)
            }
        }

    }
}