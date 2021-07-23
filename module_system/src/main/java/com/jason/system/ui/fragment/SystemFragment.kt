package com.jason.system.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseFragment
import com.jason.system.databinding.FragmentSystemBinding
import com.jason.system.ui.adapter.SysAdapter
import com.jason.system.vm.SystemViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SystemFragment:BaseFragment<FragmentSystemBinding,SystemViewModel>() {

    private val systemViewModel : SystemViewModel by viewModels()

    override fun getBind(inflater: LayoutInflater, container: ViewGroup?): FragmentSystemBinding {
        return FragmentSystemBinding.inflate(inflater,container,false)
    }

    override fun getVM(): SystemViewModel? = systemViewModel

    override fun initViews() {
        binding.rvSys.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    override fun initData() {
        viewModel?.loadSysTree()
        viewModel?.errMsg?.observe(viewLifecycleOwner,{
            ToastUtils.show(it)
        })
        viewModel?.sysTree?.observe(viewLifecycleOwner,{
            val sysAdapter = SysAdapter(it)
            binding.rvSys.adapter = sysAdapter
        })
    }
}