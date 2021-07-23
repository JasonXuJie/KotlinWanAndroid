package com.jason.square.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jason.square.api.SquareService
import java.lang.Exception

class ShareOneDataSource(private val id:Int,private val service:SquareService):PagingSource<Int,ShareArticle>() {

    var coinInfo: CoinInfo? = null

    override fun getRefreshKey(state: PagingState<Int, ShareArticle>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShareArticle> {
        return try {
            val pageNum = params.key ?: 1
            val response = service.getShareOneList(id,pageNum)
            coinInfo = response.data.coinInfo
            val prevKey = if (pageNum > 1) pageNum -1 else null
            LoadResult.Page(response.data.shareArticles.datas,prevKey = prevKey,nextKey = pageNum + 1)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }


}