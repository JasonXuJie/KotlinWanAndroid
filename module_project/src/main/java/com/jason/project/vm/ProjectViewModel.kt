package com.jason.project.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.jason.common.base.BaseViewModel
import com.jason.common.utils.MyLog
import com.jason.net.bean.ResState
import com.jason.project.api.ProjectTab
import com.jason.project.model.Project
import com.jason.project.model.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject  constructor(): BaseViewModel() {

    @Inject
    lateinit var repository: ProjectRepository

    val tabList = MutableLiveData<List<ProjectTab>>()
    val errMsg = MutableLiveData<String>()

    fun requestTab(){
        launch({
            val state = repository.loadTab()
            if (state is ResState.Success){
                tabList.postValue(state.data)
            }else if(state is ResState.Fail){
                errMsg.postValue(state.errorMsg)
            }
        })
    }

    fun requestList(cid: Int): Flow<PagingData<Project>> {
        return repository.loadList(cid).cachedIn(viewModelScope)
    }

}