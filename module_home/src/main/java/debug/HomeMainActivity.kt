package debug

import android.view.LayoutInflater
import com.jason.common.base.BaseActivity
import com.jason.home.R
import com.jason.home.databinding.ActivityMainBinding
import com.jason.home.ui.fragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeMainActivity : BaseActivity<ActivityMainBinding,Nothing>() {
    override fun getBind(): ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))

    override fun getVM(): Nothing? = null

    override fun initViews() {
        supportFragmentManager.beginTransaction().add(R.id.fg_container,HomeFragment()).commit()
    }

    override fun initData() {

    }
}