package lin.com.bookreader.dagger.activity

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import lin.com.bookreader.extensions.createViewModel
import lin.com.bookreader.viewmodels.MainActivityViewModel

@Module
class ActivityModule(val activity: AppCompatActivity) {
    @ActivityScope
    @Provides
    fun provideMainActivityViewModel(): MainActivityViewModel =
        activity.createViewModel(MainActivityViewModel::class.java)
}