package debug

import android.view.LayoutInflater
import com.jason.common.base.BaseActivity
import com.jason.square.R
import com.jason.square.databinding.ActivityMainBinding
import com.jason.square.ui.fragment.SquareFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SquareMainActivity : BaseActivity<ActivityMainBinding,Nothing>() {

    override fun getBind(): ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))

    override fun getVM(): Nothing? = null

    override fun initViews() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fg_container, SquareFragment())
            .commit()
    }

    override fun initData() {

    }
}