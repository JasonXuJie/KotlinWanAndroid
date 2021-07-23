package com.jason.qa.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jason.qa.api.QaService
import java.lang.Exception

class QaDataSource(private val service:QaService): PagingSource<Int,Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val pageNum = params.key ?: 1
            val data = service.loadList(pageNum)
            val preKey = if (pageNum > 1) pageNum -1 else null
            LoadResult.Page(data.data.datas,prevKey = preKey, nextKey = pageNum + 1)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}