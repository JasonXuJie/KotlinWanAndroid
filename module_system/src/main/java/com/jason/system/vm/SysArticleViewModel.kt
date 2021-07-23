package com.jason.system.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jason.common.base.BaseViewModel
import com.jason.system.model.SysArticle
import com.jason.system.model.SysRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class SysArticleViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var repo: SysRepo



    fun loadArtList(cid:Int): Flow<PagingData<SysArticle>> {
        return repo.loadArtList(cid).cachedIn(viewModelScope)
    }
}