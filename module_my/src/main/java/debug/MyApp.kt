package debug

import com.jason.common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp
class MyApp:BaseApplication() {

    override fun isDebug(): Boolean = true

    override fun onCreate() {
        super.onCreate()
    }
}