package lin.com.bookreader.fragment

import lin.com.bookreader.R
import lin.com.bookreader.databinding.FragmentMainSearchBinding
import lin.com.bookreader.extensions.buildComponent
import lin.com.bookreader.viewmodels.MainSearchFragmentViewModel

class MainSearchFragment : BaseFragment<FragmentMainSearchBinding,MainSearchFragmentViewModel>(R.layout.fragment_main_search) {
    override fun initDaggerInjector() {
        buildComponent().inject(this)
    }

    override fun setDatabindingVaribles() {
        databinding.viewModel = viewModel
    }


}