package com.jason.project.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseFragment
import com.jason.common.utils.MyLog
import com.jason.project.databinding.FragmentProjectBinding
import com.jason.project.ui.adapter.ProjectAdapter
import com.jason.project.vm.ProjectViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.PROJECT)
class ProjectFragment : BaseFragment<FragmentProjectBinding,ProjectViewModel>(){

    private val projectViewModel  : ProjectViewModel by viewModels()

    override fun getBind(inflater: LayoutInflater, container: ViewGroup?): FragmentProjectBinding {
        return FragmentProjectBinding.inflate(inflater,container,false)
    }

    override fun getVM(): ProjectViewModel = projectViewModel

    override fun initViews() {
        viewModel?.tabList?.observe(viewLifecycleOwner, Observer {
             for (tab in it){
                 val tabItem = binding.tlProject.newTab()
                 tabItem.text = tab.name
                 binding.tlProject.addTab(tabItem)

             }
            binding.vpProject.adapter = ProjectAdapter(it,this)
            TabLayoutMediator(binding.tlProject,binding.vpProject
            ) {
                    tab, position -> tab.text = it[position].name
            }.attach()
        })
        viewModel?.errMsg?.observe(viewLifecycleOwner, Observer {
            ToastUtils.show(it)
        })
        viewModel?.loadingEvent?.observe(viewLifecycleOwner,{
            if (it){
                showLoading()
            }else{
                hideLoading()
            }
        })
    }

    override fun initData() {
        viewModel?.requestTab()
    }
}