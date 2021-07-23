package com.jason.system.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jason.common.base.BaseFragment
import com.jason.common.config.BundleKey
import com.jason.common.utils.MyLog
import com.jason.system.databinding.FragmentSysArtBinding
import com.jason.system.ui.adapter.SysArtAdapter
import com.jason.system.vm.SysArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SysArtFragment : BaseFragment<FragmentSysArtBinding,SysArticleViewModel>() {


    private val sysArtViewModel:SysArticleViewModel by viewModels()

    private val artAdapter = SysArtAdapter()

    companion object{
        fun newInstance(cid:Int):SysArtFragment{
            val bundle = Bundle()
            bundle.putInt(BundleKey.CID,cid)
            val fragment = SysArtFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getBind(inflater: LayoutInflater, container: ViewGroup?): FragmentSysArtBinding {
       return FragmentSysArtBinding.inflate(inflater,container,false)
    }

    override fun getVM(): SysArticleViewModel = sysArtViewModel

    override fun initViews() {
        binding.srlSysArt.setOnRefreshListener {  }
        binding.rvSysArt.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = artAdapter
        }
    }

    override fun initData() {
        arguments?.let {
            val id = it.getInt(BundleKey.CID)
            lifecycleScope.launch {
                viewModel?.loadArtList(id)?.collectLatest {
                        data -> artAdapter.submitData(data)
                }
            }
        }
    }
}