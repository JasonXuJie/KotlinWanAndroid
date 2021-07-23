package com.jason.square.model

import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jason.square.api.SquareService
import java.lang.Exception

class SquareListDataSource(private val service:SquareService) : PagingSource<Int,SquareItem>() {


    override fun getRefreshKey(state: PagingState<Int, SquareItem>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SquareItem> {
        return try {
            val pageNum = params.key ?: 0
            val response = service.getSquareList(pageNum)
            val preKey = if (pageNum > 1) pageNum -1 else null
            LoadResult.Page(response.data.datas,prevKey = preKey,nextKey = pageNum + 1)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}