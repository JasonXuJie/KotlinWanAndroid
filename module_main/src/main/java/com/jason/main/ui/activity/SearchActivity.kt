package com.jason.main.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseActivity
import com.jason.main.databinding.ActivitySearchBinding
import com.jason.main.vm.SearchViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.SEARCH)
class SearchActivity:BaseActivity<ActivitySearchBinding,SearchViewModel>() {

    private val searchViewModel : SearchViewModel by viewModels()

    override fun getBind(): ActivitySearchBinding = ActivitySearchBinding.inflate(layoutInflater)

    override fun getVM(): SearchViewModel = searchViewModel

    override fun initViews() {

    }

    override fun initData() {

    }
}