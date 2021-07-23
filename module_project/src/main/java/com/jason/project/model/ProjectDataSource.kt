package com.jason.project.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jason.common.utils.MyLog
import com.jason.project.api.ProjectService
import com.jason.project.model.Project

class ProjectDataSource(private val service: ProjectService,cid:Int):PagingSource<Int, Project>() {

    private val mId: Int = cid

    override fun getRefreshKey(state: PagingState<Int, Project>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Project> {
        return try {
            val pageNum = params.key ?: 1
            val data = service.loadProjectList(pageNum,mId)
            val preKey = if (pageNum > 1) pageNum - 1 else null
            LoadResult.Page(data.data.datas,prevKey = preKey,nextKey = pageNum +1)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}