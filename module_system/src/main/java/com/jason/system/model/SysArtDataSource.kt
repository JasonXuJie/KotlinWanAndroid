package com.jason.system.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jason.system.api.SystemService
import java.lang.Exception

class SysArtDataSource(private val cid:Int,private val service:SystemService):PagingSource<Int,SysArticle>()  {


    override fun getRefreshKey(state: PagingState<Int, SysArticle>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SysArticle> {
       return try {
           val pageNum = params.key ?: 0
           val response = service.getArtList(pageNum,cid)
           val prevKey = if (pageNum > 0) pageNum - 1 else null
           LoadResult.Page(response.data.datas,prevKey = prevKey,nextKey = pageNum + 1)
       }catch (e:Exception){
           LoadResult.Error(e)
       }
    }
}