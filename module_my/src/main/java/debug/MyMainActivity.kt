package debug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.jason.common.base.BaseActivity
import com.jason.common.utils.MyLog
import com.jason.my.R
import com.jason.my.databinding.ActivityMainBinding
import com.jason.my.ui.fragment.MyFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyMainActivity : BaseActivity<ActivityMainBinding,Nothing>() {

    @Inject
    lateinit var person: Person

    override fun getBind(): ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))

    override fun getVM(): Nothing? = null

    override fun initViews() {
        MyLog.e("initViews")
        supportFragmentManager.beginTransaction()
            .add(R.id.container,MyFragment())
            .commit()
    }

    override fun initData() {

    }

}