package com.jason.home.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jason.home.api.HomeService

class ArtDataSource(val service: HomeService):PagingSource<Int,Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val pageNum = params.key ?: 0
            val response = service.getArtList(pageNum)
            val preKey = if (pageNum > 1) pageNum -1 else null
            LoadResult.Page(response.data.datas,prevKey = preKey,nextKey = pageNum + 1)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}