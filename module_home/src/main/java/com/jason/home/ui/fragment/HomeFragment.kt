package com.jason.home.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseFragment
import com.jason.home.databinding.FragmentHomeBinding
import com.jason.home.ui.adapter.*
import com.jason.home.vm.HomeViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
@Route(path = Routes.HOME)
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(){


    private val homeViewModel : HomeViewModel by viewModels()

    val homeAdapter = HomeAdapter(listOf(), listOf())

    override fun getBind(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun getVM(): HomeViewModel = homeViewModel

    override fun initViews() {
        binding.slHome.setOnRefreshListener {  }
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter

        }
    }

    override fun initData() {
        lifecycleScope.launch {
            viewModel?.loadBanner()
            viewModel?.loadTopList()
            viewModel?.loadArtList()?.collectLatest {
                data->homeAdapter.submit(data)
            }
        }
        viewModel?.bannerList?.observe(viewLifecycleOwner,{
            homeAdapter.submitBannerList(it)
        })
        viewModel?.topList?.observe(viewLifecycleOwner,{
            homeAdapter.submitTopList(it)

        })
        viewModel?.bannerErrMsg?.observe(viewLifecycleOwner,{
            ToastUtils.show(it)
        })
        viewModel?.topErrMsg?.observe(viewLifecycleOwner,{
            ToastUtils.show(it)
        })

    }
}