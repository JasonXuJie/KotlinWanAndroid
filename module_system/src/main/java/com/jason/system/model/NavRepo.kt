package com.jason.system.model

import com.jason.common.base.BaseRepository
import com.jason.net.RetrofitManager
import com.jason.net.bean.ResState
import com.jason.system.api.SystemService
import javax.inject.Inject

class NavRepo @Inject constructor(): BaseRepository() {

    suspend fun loadNavTree():ResState<List<NavData>>{
        return execute(RetrofitManager.retrofit.create(SystemService::class.java).getNavList())
    }
}