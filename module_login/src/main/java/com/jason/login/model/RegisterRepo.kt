package com.jason.login.model

import com.jason.common.base.BaseRepository
import com.jason.login.api.LoginService
import com.jason.net.RetrofitManager
import com.jason.net.bean.ResState
import javax.inject.Inject

class RegisterRepo @Inject constructor(): BaseRepository() {


    suspend fun register(usrName:String,pwd:String,rePwd:String):ResState<RegisterData>{
        return execute(RetrofitManager.retrofit.create(LoginService::class.java).register(usrName,pwd,rePwd))
    }

}