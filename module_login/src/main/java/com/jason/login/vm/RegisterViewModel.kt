package com.jason.login.vm

import androidx.lifecycle.MutableLiveData
import com.jason.common.base.BaseViewModel
import com.jason.login.model.RegisterData
import com.jason.login.model.RegisterRepo
import com.jason.net.bean.ResState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var repo:RegisterRepo


    val registerData = MutableLiveData<RegisterData>()
    val errMsg = MutableLiveData<String>()


    fun register(userName:String,pwd:String,rePwd:String){
        launch({
            val state =  repo.register(userName,pwd, rePwd)
            if (state is ResState.Success){
                registerData.postValue(state.data)
            }else{
                errMsg.postValue((state as ResState.Fail).errorMsg)
            }
        })
    }
}