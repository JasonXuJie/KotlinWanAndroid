package com.jason.square.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jason.common.base.BaseViewModel
import com.jason.square.model.SquareItem
import com.jason.square.model.SquareRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class SquareViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var repo:SquareRepo


    fun loadSquareList():Flow<PagingData<SquareItem>>{
        return repo.loadSquareList().cachedIn(viewModelScope)
    }

}