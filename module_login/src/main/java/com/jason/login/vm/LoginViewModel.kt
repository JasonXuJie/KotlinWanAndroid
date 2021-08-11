package com.jason.login.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.jason.common.base.BaseViewModel
import com.jason.common.utils.MyLog
import com.jason.login.model.LoginData
import com.jason.login.model.LoginRepo
import com.jason.net.bean.ResState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(): BaseViewModel() {

    //@Inject
    val repo:LoginRepo by lazy { LoginRepo() }

    val errMsg = MutableLiveData<String>()
    val loginData = MutableLiveData<LoginData>()
    var showLoading by mutableStateOf(false)


    fun login(usrName:String,pwd:String){
        if (usrName.isNullOrBlank()||pwd.isNullOrBlank()){
            errMsg.postValue("请填写用户名或密码")
            return
        }
        launch({
            showLoading = true
            val state = repo.login(usrName,pwd)
            if (state is ResState.Success){
                loginData.postValue(state.data)
                showLoading = false
            }else{
                errMsg.postValue((state as ResState.Fail).errorMsg)
                showLoading = false
            }
        })
    }
}