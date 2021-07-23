package com.jason.project.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jason.common.base.BaseRepository
import com.jason.net.RetrofitManager
import com.jason.net.bean.ResState
import com.jason.project.api.ProjectService
import com.jason.project.api.ProjectTab
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepository @Inject constructor(): BaseRepository() {

    private val pageSize = 20

    suspend fun loadTab():ResState<List<ProjectTab>>{
       return execute(RetrofitManager.retrofit.create(ProjectService::class.java).loadTab())
    }

    fun loadList(cid:Int):Flow<PagingData<Project>>{
        return Pager(config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = 5,
            initialLoadSize = 10,
            maxSize = pageSize * 3
        )){
            ProjectDataSource(RetrofitManager.retrofit.create(ProjectService::class.java),cid)
        }.flow
    }

}