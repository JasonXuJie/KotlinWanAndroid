package com.jason.main.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseActivity
import com.jason.main.databinding.ActivityScanBinding
import com.jason.main.vm.ScanViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.SCAN)
class ScanActivity:BaseActivity<ActivityScanBinding,ScanViewModel>() {

    private val scanViewModel : ScanViewModel by viewModels()

    override fun getBind(): ActivityScanBinding = ActivityScanBinding.inflate(layoutInflater)

    override fun getVM(): ScanViewModel = scanViewModel

    override fun initViews() {

    }

    override fun initData() {

    }
}