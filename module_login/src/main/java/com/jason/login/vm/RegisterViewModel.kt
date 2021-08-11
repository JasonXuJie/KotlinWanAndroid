package com.jason.login.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.jason.common.base.BaseViewModel
import com.jason.login.model.RegisterData
import com.jason.login.model.RegisterRepo
import com.jason.net.bean.ResState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(): BaseViewModel() {

    //@Inject
    val repo:RegisterRepo by lazy { RegisterRepo() }

    var showLoading by mutableStateOf(false)
    val registerData = MutableLiveData<RegisterData>()
    val errMsg = MutableLiveData<String>()


    fun register(userName:String,pwd:String,rePwd:String){
        launch({
            showLoading = true
            val state =  repo.register(userName,pwd, rePwd)
            if (state is ResState.Success){
                registerData.postValue(state.data)
                showLoading = false
            }else{
                errMsg.postValue((state as ResState.Fail).errorMsg)
                showLoading = false
            }
        })
    }
}