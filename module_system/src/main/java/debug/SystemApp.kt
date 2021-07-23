package debug

import com.jason.common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp


//@HiltAndroidApp
class SystemApp : BaseApplication() {

    override fun isDebug(): Boolean = true
}