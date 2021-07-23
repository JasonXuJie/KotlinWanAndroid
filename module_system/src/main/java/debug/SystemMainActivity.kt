package debug

import android.view.LayoutInflater
import com.jason.common.base.BaseActivity
import com.jason.common.utils.MyLog
import com.jason.system.R
import com.jason.system.databinding.ActivityMainBinding
import com.jason.system.ui.fragment.SysIndexFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SystemMainActivity : BaseActivity<ActivityMainBinding,Nothing>() {

    override fun getBind(): ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))

    override fun getVM(): Nothing? = null

    override fun initViews() {
        MyLog.e("initViews")
        supportFragmentManager.beginTransaction().add(R.id.fg_container,SysIndexFragment()).commit()
    }

    override fun initData() {

    }
}