package com.jason.qa.vm

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jason.common.base.BaseViewModel
import com.jason.qa.model.Data
import com.jason.qa.model.QaRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class QaViewModel @Inject constructor(): BaseViewModel() {



    @Inject
    lateinit var repo:QaRepo

    fun loadList():Flow<PagingData<Data>>{
        return repo.loadList().cachedIn(viewModelScope)
    }


}