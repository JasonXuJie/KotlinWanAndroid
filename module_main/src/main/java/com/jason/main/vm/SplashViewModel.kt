package com.jason.main.vm

import androidx.lifecycle.viewModelScope
import com.jason.common.base.BaseViewModel
import com.jason.common.config.SpKey
import com.jason.common.utils.SpUtil
import com.jason.router.Navigator
import com.jason.router.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class SplashViewModel @Inject constructor():BaseViewModel() {


    fun openToGuide(){
        viewModelScope.launch {
            delay(2500L)
            if (SpUtil.get(SpKey.IS_FIRST,true)as Boolean) Navigator.push(Routes.GUIDE) else  Navigator.push(Routes.MAIN)

        }

    }
}