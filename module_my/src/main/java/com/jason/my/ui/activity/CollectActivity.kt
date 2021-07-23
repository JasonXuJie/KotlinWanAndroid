package com.jason.my.ui.activity

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseActivity
import com.jason.my.databinding.ActivityCollectBinding
import com.jason.my.vm.CollectViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.COLLECT)
class CollectActivity :BaseActivity<ActivityCollectBinding,CollectViewModel>(){

    private val collectViewModel : CollectViewModel by viewModels()

    override fun getBind(): ActivityCollectBinding = ActivityCollectBinding.inflate(layoutInflater)

    override fun getVM(): CollectViewModel =  collectViewModel

    override fun initViews() {
        setToolBarTitle("收藏")
    }

    override fun initData() {

    }
}