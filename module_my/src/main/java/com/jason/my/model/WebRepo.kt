package com.jason.my.model

import com.jason.common.base.BaseRepository
import com.jason.my.api.MyService
import com.jason.net.RetrofitManager
import com.jason.net.bean.ResState
import javax.inject.Inject

class WebRepo @Inject constructor(): BaseRepository() {

    suspend fun loadWebData():ResState<List<WebData>>{
        return execute(RetrofitManager.retrofit.create(MyService::class.java).getUsedWeb())
    }
}