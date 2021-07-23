package com.jason.qa.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseFragment
import com.jason.qa.databinding.FragmentQaBinding
import com.jason.qa.ui.adapter.QaAdapter
import com.jason.qa.vm.QaViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
@Route(path = Routes.QA)
class QaFragment : BaseFragment<FragmentQaBinding,QaViewModel>() {

    private val qaViewModel : QaViewModel by viewModels()

    private val qaAdapter  = QaAdapter()

    override fun getBind(inflater: LayoutInflater, container: ViewGroup?): FragmentQaBinding {
        return FragmentQaBinding.inflate(inflater,container,false)
    }

    override fun getVM(): QaViewModel = qaViewModel

    override fun initViews() {
       binding.rvQa.apply {
           layoutManager = LinearLayoutManager(context)
           setHasFixedSize(true)
           adapter = qaAdapter
       }
       binding.rlQa.setOnRefreshListener {
           qaAdapter.refresh()
           binding.rlQa.finishRefresh()
       }
       qaAdapter.addLoadStateListener {
           when(it.refresh){
               is LoadState.NotLoading->{
                   if (binding.rlQa.isRefreshing){
                       binding.rlQa.finishRefresh()
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
            viewModel?.also {
                it.loadList().collectLatest {
                        data -> qaAdapter.submitData(data)
                }
            }
        }
    }
}