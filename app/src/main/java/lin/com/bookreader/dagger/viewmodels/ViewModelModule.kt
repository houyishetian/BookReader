package lin.com.bookreader.dagger.viewmodels

import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import lin.com.bookreader.dagger.scope.FragmentScope
import lin.com.bookreader.extensions.createViewModel
import lin.com.bookreader.viewmodels.MainScanFragmentViewModel
import lin.com.bookreader.viewmodels.MainSearchFragmentViewModel

@Module
class ViewModelModule(val activity: FragmentActivity) {

    @FragmentScope
    @Provides
    fun provideMainScanFragmentViewModel(): MainScanFragmentViewModel =
        activity.createViewModel(MainScanFragmentViewModel::class.java)

    @FragmentScope
    @Provides
    fun provideMainSearchFragmentViewModel(): MainSearchFragmentViewModel =
        activity.createViewModel(MainSearchFragmentViewModel::class.java)
}