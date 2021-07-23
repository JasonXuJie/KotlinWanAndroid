package com.jason.square.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseFragment
import com.jason.router.Routes
import com.jason.square.databinding.FragmentSquareBinding
import com.jason.square.ui.adapter.SquareAdapter
import com.jason.square.vm.SquareViewModel
import com.scwang.smart.refresh.header.ClassicsHeader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
@Route(path = Routes.SQUARE)
class SquareFragment : BaseFragment<FragmentSquareBinding,SquareViewModel>(){

    private val squareViewModel:SquareViewModel by viewModels()

    private val squareAdapter = SquareAdapter()


    override fun getBind(inflater: LayoutInflater, container: ViewGroup?): FragmentSquareBinding {
        return FragmentSquareBinding.inflate(inflater,container,false)
    }

    override fun getVM(): SquareViewModel = squareViewModel

    override fun initViews() {
        binding.ivAdd.setOnClickListener { push(Routes.SHARE) }
        binding.rvSquare.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = squareAdapter
        }
        binding.rlSquare.setOnRefreshListener {
            squareAdapter.refresh()
            binding.rlSquare.finishRefresh()
        }
        squareAdapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.NotLoading->{
                    if (binding.rlSquare.isRefreshing){
                        binding.rlSquare.finishRefresh()
                    }
                    binding.layoutLoading.pbLoading.visibility = View.INVISIBLE
                }
                is LoadState.Loading->{
                    binding.layoutLoading.pbLoading.visibility = View.VISIBLE
                }
                is LoadState.Error->{
                    binding.layoutLoading.pbLoading.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun initData() {
        lifecycleScope.launch {
            viewModel?.loadSquareList()?.collectLatest {
                data->squareAdapter.submitData(data)
            }
        }
    }
}