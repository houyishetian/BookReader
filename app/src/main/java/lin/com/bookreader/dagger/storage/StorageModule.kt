package lin.com.bookreader.dagger.storage

import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(sharedPrefsStorage: SharedPrefsStorage): Storage
}