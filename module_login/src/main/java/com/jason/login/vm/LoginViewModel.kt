package com.jason.login.vm

import androidx.lifecycle.MutableLiveData
import com.jason.common.base.BaseViewModel
import com.jason.login.model.LoginData
import com.jason.login.model.LoginRepo
import com.jason.net.bean.ResState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var repo:LoginRepo

    val errMsg = MutableLiveData<String>()
    val loginData = MutableLiveData<LoginData>()


    fun login(usrName:String,pwd:String){
        if (usrName.isNullOrBlank()||pwd.isNullOrBlank()){
            errMsg.postValue("请填写用户名或密码")
            return
        }
        launch({
            val state = repo.login(usrName,pwd)
            if (state is ResState.Success){
                loginData.postValue(state.data)
            }else{
                errMsg.postValue((state as ResState.Fail).errorMsg)
            }
        })
    }
}