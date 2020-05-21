package lin.com.bookreader.dagger.app

import android.content.Context
import java.lang.IllegalArgumentException

interface AppComponentProvider {
    fun provideAppComponent(): AppComponent
}

object AppComponentHolder {
    fun getAppComponent(context: Context): AppComponent {
        return context.applicationContext.takeIf { it is AppComponentProvider }?.let {
            (it as AppComponentProvider).provideAppComponent()
        }
            ?: throw IllegalArgumentException("illegal argument, context is not AppComponentProvider!")
    }
}