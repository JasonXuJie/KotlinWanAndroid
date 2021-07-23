package debug

import android.content.Intent
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.jason.common.base.BaseActivity
import com.jason.common.utils.MyLog
import com.jason.login.databinding.ActivityMainBinding
import com.jason.login.ui.activity.LoginActivity
import com.jason.router.Navigator
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginMainActivity : BaseActivity<ActivityMainBinding,Nothing>() {

    private val requestCode = 1

    override fun getBind(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getVM(): Nothing? = null

    override fun initViews() {
       binding.btnJump.setOnClickListener { ARouter.getInstance().build(Routes.LOGIN).navigation(this,requestCode) }
    }

    override fun initData() {

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        MyLog.e("${requestCode}<${resultCode}")
        if (requestCode == this.requestCode && resultCode == LoginActivity.RESULT_CODE){
            MyLog.e("进了if")
            MyLog.e("${data == null}")
            MyLog.e("${data?.extras == null}")
            val name = data?.getStringExtra("name")
            MyLog.e("${name == null}")
            name?.let {
                MyLog.e(it)
            }
        }
    }
}