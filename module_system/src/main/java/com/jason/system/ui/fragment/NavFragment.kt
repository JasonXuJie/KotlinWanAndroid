package com.jason.system.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseFragment
import com.jason.system.databinding.FragmentNavBinding
import com.jason.system.ui.adapter.NavAdapter
import com.jason.system.vm.NavViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NavFragment : BaseFragment<FragmentNavBinding,NavViewModel>(){

    private val navViewModel: NavViewModel by viewModels()

    override fun getBind(inflater: LayoutInflater, container: ViewGroup?): FragmentNavBinding {
        return FragmentNavBinding.inflate(inflater,container,false)
    }

    override fun getVM(): NavViewModel = navViewModel

    override fun initViews() {
        binding.rvNav.apply {
            layoutManager  = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    override fun initData() {
        viewModel?.loadNavList()
        viewModel?.errMsg?.observe(viewLifecycleOwner,{
            ToastUtils.show(it)
        })
        viewModel?.data?.observe(viewLifecycleOwner,{
            binding.rvNav.adapter = NavAdapter(it)
        })
    }
}