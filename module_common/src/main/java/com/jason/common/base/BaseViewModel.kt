package com.jason.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjq.toast.ToastUtils
import com.jason.common.utils.MyLog
import com.jason.net.exception.ExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

open class BaseViewModel:ViewModel() {

    val loadingEvent  = MutableLiveData<Boolean>()


    fun launch(
        block:suspend ()->Unit,
        errorBlock:(suspend CoroutineScope.(Throwable)->Unit)?= null
    ){
        viewModelScope.launch(Dispatchers.IO){
            try {
                loadingEvent.postValue(true)
                block()
            }catch (e:Exception){
                e.printStackTrace()
                val errMsg = ExceptionHandler.handleException(e)
                ToastUtils.show(errMsg)
                errorBlock?.let { it(e) }
            }finally {
                loadingEvent.postValue(false)
            }
        }
    }

    private fun showLoading(){
        loadingEvent.value = true
    }

    private fun hideLoading(){
        loadingEvent.value = false
    }

//    override fun onCleared() {
//        super.onCleared()
//        viewModelScope.cancel()
//    }
}