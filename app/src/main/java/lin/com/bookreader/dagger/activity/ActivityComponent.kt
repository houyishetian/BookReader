package lin.com.bookreader.dagger.activity

import dagger.Component
import lin.com.bookreader.activity.MainActivity
import lin.com.bookreader.dagger.app.AppComponent
import lin.com.bookreader.viewmodels.MainActivityViewModel

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModel: MainActivityViewModel)
}