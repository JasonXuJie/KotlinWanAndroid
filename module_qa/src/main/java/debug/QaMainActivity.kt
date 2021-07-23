package debug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jason.qa.R
import com.jason.qa.ui.fragment.QaFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QaMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fg_container, QaFragment())
            .commit()
    }
}