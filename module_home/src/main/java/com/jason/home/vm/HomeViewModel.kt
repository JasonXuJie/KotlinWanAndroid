package com.jason.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jason.common.base.BaseViewModel
import com.jason.home.model.Article
import com.jason.home.model.BannerData
import com.jason.home.model.HomeRepo
import com.jason.home.model.TopData
import com.jason.net.bean.ResState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var repo:HomeRepo


    val bannerList = MutableLiveData<List<BannerData>>()

    val topList = MutableLiveData<List<TopData>>()

    val bannerErrMsg = MutableLiveData<String>()

    val topErrMsg = MutableLiveData<String>()

    fun loadBanner(){
        launch({
            val state = repo.loadBanner()
            if (state is ResState.Success){
                bannerList.postValue(state.data)
            }else if (state is ResState.Fail){
                bannerErrMsg.postValue(state.errorMsg)
            }
        })
    }


    fun loadTopList(){
        launch({
            val state = repo.loadTopList()
            if (state is ResState.Success){
                topList.postValue(state.data)
            }else{
                topErrMsg.postValue((state as ResState.Fail).errorMsg)
            }
        })
    }


    fun loadArtList():Flow<PagingData<Article>>{
        return repo.loadArtList().cachedIn(viewModelScope)
    }

    fun loadArtList1():LiveData<PagingData<Article>>{
        return repo.loadArtList().cachedIn(viewModelScope).asLiveData()
    }

}