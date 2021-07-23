package debug

import com.jason.common.base.BaseActivity
import com.jason.common.utils.MyLog
import com.jason.project.R
import com.jason.project.databinding.ActivityMainBinding
import com.jason.project.ui.fragment.ProjectFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectMainActivity : BaseActivity<ActivityMainBinding,Nothing>() {

    override fun getBind(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getVM(): Nothing? = null

    override fun initViews() {
        MyLog.e("initViews")
        supportFragmentManager.beginTransaction()
            .add(R.id.container,ProjectFragment())
            .commit()
    }

    override fun initData() {

    }

}