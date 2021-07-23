package com.jason.project.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.jason.common.base.BaseFragment
import com.jason.common.config.BundleKey
import com.jason.common.widgets.FooterAdapter
import com.jason.project.databinding.FragmentProjectListBinding
import com.jason.project.ui.adapter.ProjectListAdapter
import com.jason.project.vm.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProjectListFragment : BaseFragment<FragmentProjectListBinding, ProjectViewModel>() {

    private val proAdapter = ProjectListAdapter()

    companion object {
        fun newInstance(cid: Int): ProjectListFragment {
            val bundle = Bundle()
            bundle.putInt(BundleKey.CID, cid)
            val projectListFragment = ProjectListFragment()
            projectListFragment.arguments = bundle
            return projectListFragment
        }
    }

    private val projectViewModel: ProjectViewModel by viewModels()

    override fun getBind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProjectListBinding {
        return FragmentProjectListBinding.inflate(inflater, container, false)
    }

    override fun getVM(): ProjectViewModel = projectViewModel

    override fun initViews() {
        binding.rvProList.apply {
            setHasFixedSize(true)
            adapter = proAdapter.withLoadStateFooter(FooterAdapter{proAdapter.retry()})
        }
        binding.rlProject.setOnRefreshListener {
            proAdapter.refresh()
            binding.rlProject.finishRefresh()
        }
        proAdapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.NotLoading ->{
                    if (binding.rlProject.isRefreshing){
                        binding.rlProject.finishRefresh()
                    }
                    binding.layoutLoading.pbLoading.visibility = View.INVISIBLE
                }
                is LoadState.Loading ->{
                    binding.layoutLoading.pbLoading.visibility = View.VISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    binding.layoutLoading.pbLoading.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun initData() {
       arguments?.let {
           lifecycleScope.launch {
               viewModel?.requestList(it.getInt(BundleKey.CID))?.collectLatest {
                       data -> proAdapter.submitData(data)
               }
           }

       }

    }


}