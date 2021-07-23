package com.jason.system.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jason.common.base.BaseRepository
import com.jason.net.RetrofitManager
import com.jason.net.bean.ResState
import com.jason.system.api.SystemService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SysRepo @Inject constructor(): BaseRepository() {

    private val pageSize = 20

    suspend fun loadSysTree():ResState<List<SysTree>>{
        return execute(RetrofitManager.retrofit.create(SystemService::class.java).getSysTree())
    }


    fun loadArtList(cid:Int):Flow<PagingData<SysArticle>>{
        val config = PagingConfig(
            pageSize = pageSize
        )
        return Pager(
         config = config,
         pagingSourceFactory = {SysArtDataSource(cid,RetrofitManager.retrofit.create(SystemService::class.java))}
        ).flow
    }


}