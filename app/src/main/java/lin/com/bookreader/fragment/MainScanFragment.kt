package lin.com.bookreader.fragment

import lin.com.bookreader.R
import lin.com.bookreader.dagger.app.AppComponentHolder
import lin.com.bookreader.dagger.viewmodels.DaggerViewModelComponent
import lin.com.bookreader.dagger.viewmodels.ViewModelModule
import lin.com.bookreader.databinding.FragmentMainScanBinding
import lin.com.bookreader.viewmodels.MainScanFragmentViewModel

class MainScanFragment : BaseFragment<FragmentMainScanBinding, MainScanFragmentViewModel>(R.layout.fragment_main_scan) {
    override fun initDaggerInjector() {
        DaggerViewModelComponent.builder()
            .appComponent(AppComponentHolder.getAppComponent(requireContext()))
            .viewModelModule(ViewModelModule(requireActivity()))
            .build()
            .inject(this)
    }

    override fun setDatabindingVaribles() {
        databinding.viewModel = viewModel
    }

}