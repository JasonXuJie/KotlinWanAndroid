package com.jason.square.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jason.common.base.BaseRepository
import com.jason.net.RetrofitManager
import com.jason.net.bean.ResState
import com.jason.square.api.SquareService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SquareRepo @Inject constructor(): BaseRepository() {

    private val pageSize = 15

    var coinInfo: CoinInfo? = null

    //请求列表
    fun loadSquareList():Flow<PagingData<SquareItem>>{
        val config = PagingConfig(
            pageSize = pageSize
        )
        return Pager(
            config = config,
            pagingSourceFactory = { SquareListDataSource(RetrofitManager.retrofit.create(SquareService::class.java))}
        ).flow
    }


    //分享
    suspend fun share(title:String,link:String):ResState<Any>{
        return execute(RetrofitManager.retrofit.create(SquareService::class.java).share(title, link))
    }


    //分享人列表
    fun loadShareOneList(id:Int):Flow<PagingData<ShareArticle>>{
        val config = PagingConfig(
            pageSize = pageSize
        )
        val factory = ShareOneDataSource(id,RetrofitManager.retrofit.create(SquareService::class.java))
        coinInfo = factory.coinInfo
        return Pager(
            config = config,
            pagingSourceFactory = {factory}
        ).flow
    }


    suspend fun loadShareOneList(id:Int,page:Int):ResState<ShareListData>{
        return execute(RetrofitManager.retrofit.create(SquareService::class.java).getShareOneList(id,page))
    }

}