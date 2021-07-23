package com.jason.login.model

import com.jason.common.base.BaseRepository
import com.jason.login.api.LoginService
import com.jason.net.RetrofitManager
import com.jason.net.bean.ResState
import javax.inject.Inject

class LoginRepo @Inject constructor(): BaseRepository() {

    suspend fun login(userName:String,pwd:String):ResState<LoginData>{
        return execute(RetrofitManager.retrofit.create(LoginService::class.java).login(userName,pwd))
    }
}