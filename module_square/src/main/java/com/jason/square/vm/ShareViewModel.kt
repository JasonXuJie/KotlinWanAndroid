package com.jason.square.vm

import androidx.lifecycle.MutableLiveData
import com.jason.common.base.BaseViewModel
import com.jason.net.bean.ResState
import com.jason.square.model.SquareRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ShareViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var repo:SquareRepo

    val errMsg = MutableLiveData<String>()

    val success = MutableLiveData<Nothing>()

    fun share(title:String,link:String){
        if (title.isEmpty()){
            errMsg.postValue("请填写标题")
            return
        }
        if (link.isEmpty() || (!link.contains("http") || !link.contains("https"))){
            errMsg.postValue("请填写正确的链接地址")
            return
        }
        launch({
           val state =  repo.share(title, link)
           if (state is ResState.Success){
               success.postValue(null)
           }else{
               errMsg.postValue((state as ResState.Fail).errorMsg)
           }
        })
    }
}