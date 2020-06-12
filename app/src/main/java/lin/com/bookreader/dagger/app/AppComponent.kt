package lin.com.bookreader.dagger.app

import android.content.Context
import dagger.Component
import lin.com.bookreader.dagger.network.retrofit.RetrofitModule
import lin.com.bookreader.dagger.storage.StorageModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, StorageModule::class, RetrofitModule::class])
interface AppComponent {
    fun getContext(): Context
}