package com.jason.square.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jason.common.base.BaseViewModel
import com.jason.common.utils.MyLog
import com.jason.net.bean.ResState
import com.jason.square.model.CoinInfo
import com.jason.square.model.ShareArticle
import com.jason.square.model.SquareRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ShareOneViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var repo:SquareRepo


    val coinInfo = MutableLiveData<CoinInfo>()
    val errMsg = MutableLiveData<String>()


    fun loadList(id:Int):Flow<PagingData<ShareArticle>>{
        return repo.loadShareOneList(id).cachedIn(viewModelScope)
    }


    fun loadInfo(id:Int){
        launch({
            val state = repo.loadShareOneList(id,1)
            if (state is ResState.Success){
                val data = state.data
                coinInfo.postValue(data.coinInfo)
            }else{
                errMsg.postValue((state as ResState.Fail).errorMsg)
            }
        })
    }

}