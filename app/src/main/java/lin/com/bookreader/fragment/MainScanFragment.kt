package lin.com.bookreader.fragment

import lin.com.bookreader.R
import lin.com.bookreader.databinding.FragmentMainScanBinding
import lin.com.bookreader.extensions.buildComponent
import lin.com.bookreader.viewmodels.MainScanFragmentViewModel

class MainScanFragment : BaseFragment<FragmentMainScanBinding, MainScanFragmentViewModel>(R.layout.fragment_main_scan) {
    override fun initDaggerInjector() {
        buildComponent().inject(this)
    }

    override fun setDatabindingVaribles() {
        databinding.viewModel = viewModel
    }
}