package lin.com.bookreader.dagger.viewmodels

import dagger.Component
import lin.com.bookreader.dagger.app.AppComponent
import lin.com.bookreader.dagger.scope.FragmentScope
import lin.com.bookreader.fragment.MainScanFragment
import lin.com.bookreader.fragment.MainSearchFragment

@FragmentScope
@Component(
    modules = [ViewModelModule::class],
    dependencies = [AppComponent::class]
)
interface ViewModelComponent {
    fun inject(fragment: MainScanFragment)
    fun inject(fragment: MainSearchFragment)
}