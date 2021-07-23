package debug

import android.app.Application
import com.jason.common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp
class MainApp : BaseApplication() {

    override fun isDebug(): Boolean = true


}