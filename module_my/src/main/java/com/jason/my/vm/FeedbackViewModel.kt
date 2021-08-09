package com.jason.my.vm

import com.jason.common.base.BaseViewModel
import com.jason.common.utils.MyLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor():BaseViewModel() {


    fun submit(content:String){
        MyLog.e(content)
    }

}