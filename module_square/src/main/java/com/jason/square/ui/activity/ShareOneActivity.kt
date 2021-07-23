package com.jason.square.ui.activity

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.AppBarLayout
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseActivity
import com.jason.common.config.BundleKey
import com.jason.common.utils.MyLog
import com.jason.router.Routes
import com.jason.square.databinding.ActivityShareOneBinding
import com.jason.square.ui.adapter.ShareOneAdapter
import com.jason.square.vm.ShareOneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.abs


@AndroidEntryPoint
@Route(path = Routes.SHARE_ONE)
class ShareOneActivity : BaseActivity<ActivityShareOneBinding,ShareOneViewModel>(){

    private val shareOneViewModel : ShareOneViewModel by viewModels()

    private val shareOneAdapter = ShareOneAdapter()

    override fun getBind(): ActivityShareOneBinding = ActivityShareOneBinding.inflate(layoutInflater)

    override fun getVM(): ShareOneViewModel = shareOneViewModel

    override fun initViews() {
       binding.ivBack.setOnClickListener { finish() }
       binding.rvShareOne.apply {
           layoutManager = LinearLayoutManager(context)
           setHasFixedSize(true)
           adapter = shareOneAdapter
       }
       binding.ablShare.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
           if(verticalOffset == 0){
               //完全展开状态
           }else if(abs(verticalOffset) >= appBarLayout.totalScrollRange){
               //折叠状态
               if (binding.tvTitle.visibility == View.GONE){
                   binding.tvTitle.visibility = View.VISIBLE
               }
               if (binding.llInfo.visibility == View.VISIBLE){
                   binding.llInfo.visibility = View.INVISIBLE
               }
           }else{
               if (binding.tvTitle.visibility == View.VISIBLE){
                   binding.tvTitle.visibility = View.GONE
               }
               if (binding.llInfo.visibility == View.INVISIBLE){
                   binding.llInfo.visibility = View.VISIBLE
               }
           }
       })
       viewModel?.loadingEvent?.observe(this,{
           if (it){
               showLoading()
           }else{
               hideLoading()
           }
       })
       viewModel?.errMsg?.observe(this,{
           ToastUtils.show(it)
       })
       viewModel?.coinInfo?.observe(this,{
            binding.tvTitle.text = it.nickname
            binding.tvAuthor.text = it.nickname
            binding.tvId.text = "ID:${it.userId}"
            binding.tvIntegral.text = "积分:${it.coinCount}"
            binding.tvRanking.text = "排名:${it.rank}"
       })
    }

    override fun initData() {
        intent.extras?.let {
            lifecycleScope.launch{
               viewModel?.loadInfo(it.getInt(BundleKey.USER_ID))
                viewModel?.loadList(it.getInt(BundleKey.USER_ID))?.collectLatest {
                    shareOneAdapter.submitData(it)
                }
            }
        }
    }
}