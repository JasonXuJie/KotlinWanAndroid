package com.jason.qa.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jason.net.RetrofitManager
import com.jason.qa.api.QaService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QaRepo @Inject constructor() {

    val pageSize = 15

    fun loadList():Flow<PagingData<Data>>{
        val config = PagingConfig(
            pageSize = pageSize
        )
        return Pager(
            config  = config,
            pagingSourceFactory = { QaDataSource(RetrofitManager.retrofit.create(QaService::class.java)) }
        ).flow
    }
}