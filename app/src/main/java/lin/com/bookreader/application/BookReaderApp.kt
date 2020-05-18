package lin.com.bookreader.application

import android.app.Application
import lin.com.bookreader.dagger.app.AppComponentUtil
import timber.log.Timber

class BookReaderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        injectAppComponent()
        initTimberTree()
    }

    private fun injectAppComponent() {
        AppComponentUtil.createAppComponent(this)
    }

    private fun initTimberTree() {
        Timber.plant(Timber.DebugTree())
    }
}