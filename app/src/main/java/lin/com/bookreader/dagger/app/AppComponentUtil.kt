package lin.com.bookreader.dagger.app

import android.content.Context

class AppComponentUtil {
    companion object {
        fun createAppComponent(context: Context): AppComponent =
            DaggerAppComponent.builder().appModule(AppModule(context)).build()
    }
}