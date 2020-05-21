package lin.com.bookreader.dagger.network.retrofit

import dagger.Component
import lin.com.bookreader.viewmodels.MainSearchFragmentViewModel

@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {
    fun inject(viewModel: MainSearchFragmentViewModel)
}