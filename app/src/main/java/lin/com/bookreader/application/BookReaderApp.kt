package lin.com.bookreader.application

import android.app.Application
import lin.com.bookreader.dagger.app.AppComponentProvider
import lin.com.bookreader.dagger.app.AppComponent
import lin.com.bookreader.dagger.app.AppModule
import lin.com.bookreader.dagger.app.DaggerAppComponent
import timber.log.Timber

class BookReaderApp : Application(), AppComponentProvider {

    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        injectAppComponent()
        initTimberTree()
    }

    private fun injectAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    private fun initTimberTree() {
        Timber.plant(Timber.DebugTree())
    }

    override fun provideAppComponent(): AppComponent = appComponent

}