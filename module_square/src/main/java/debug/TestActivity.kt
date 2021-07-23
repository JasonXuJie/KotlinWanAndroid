package debug

import android.content.Intent
import android.view.LayoutInflater
import com.jason.common.base.BaseActivity
import com.jason.square.databinding.ActivityTestBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TestActivity : BaseActivity<ActivityTestBinding,Nothing>() {

    override fun getBind(): ActivityTestBinding = ActivityTestBinding.inflate(LayoutInflater.from(this))

    override fun getVM(): Nothing? = null

    override fun initViews() {
        binding.btnJump.setOnClickListener { startActivity(Intent(this,SquareMainActivity::class.java)) }

    }

    override fun initData() {

    }
}