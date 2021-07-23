package com.jason.web.ui.activity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.jason.common.R
import com.jason.common.base.BaseActivity
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.common.utils.MyLog
import com.jason.router.Routes
import com.jason.web.databinding.ActivityWebBinding
import com.jason.web.utils.ChromeClient
import com.jason.web.utils.WebClient
import com.jason.web.vm.WebViewModel


@Route(path = Routes.WEB)
class WebActivity : BaseActivity<ActivityWebBinding,WebViewModel>() {

    lateinit var webView:WebView

    override fun getBind(): ActivityWebBinding = ActivityWebBinding.inflate(layoutInflater)

    override fun getVM(): WebViewModel  = WebViewModel()

    override fun initViews() {
       binding.fabBack.setOnClickListener {
           if (webView.canGoBack()){
               webView.goBack()
           }else{
               finish()
           }
       }
       initWeb()
       binding.ivShare.setOnClickListener {  }
       binding.ivRefresh.setOnClickListener { webView.reload() }
       intent.extras?.also {
           val content = it.getParcelable<WebContent>(BundleKey.WEB_CONTENT)
           content?.apply {
               binding.tvAuthor.visibility = if (this.author.isNullOrBlank()) View.GONE else View.VISIBLE
               binding.tvAuthor.text = this.author ?: ""
               webView.loadUrl(this.url!!)
           }
       }
       ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true).
       barAlpha(0.3f).fitsSystemWindows(true).init()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWeb(){
        webView = WebView(applicationContext)
        binding.flWebContainer.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
        binding.flWebContainer.addView(webView)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true // 开启 DOM storage API 功能
        settings.databaseEnabled = true   //开启 database storage API 功能
        settings.loadWithOverviewMode = true // 缩放至屏幕的大小
        settings.useWideViewPort = true  //将图片调整到适合webview的大小
        settings.defaultTextEncodingName = "utf-8" //设置编码格式
        settings.loadsImagesAutomatically = true  //支持自动加载图片
        webView.webChromeClient = ChromeClient()
        webView.webViewClient = WebClient()


    }

    override fun initData() {

    }


    override fun onResume() {
        super.onResume()
        webView.onResume()
    }


    override fun onPause() {
        super.onPause()
        webView.onPause()
    }




    override fun onDestroy() {
        destroyWeb()
        super.onDestroy()
    }


    private fun destroyWeb(){
        webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        webView.clearHistory()
        binding.flWebContainer.removeView(webView)
        webView.destroy()
    }

}