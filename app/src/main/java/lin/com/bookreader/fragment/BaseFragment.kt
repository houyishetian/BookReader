package lin.com.bookreader.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import lin.com.bookreader.extensions.hideLoading
import lin.com.bookreader.extensions.showLoading
import lin.com.bookreader.viewmodels.BaseViewModel
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel>(private val layoutId: Int) :
    Fragment() {
    protected val databinding: DB
        get() = realBinding
            ?: throw IllegalStateException("cannot get ViewDataBinding, please check the lifecycle!")

    private var realBinding: DB? = null

    @Inject
    protected lateinit var viewModel: VM

    protected val loadingObserver = Observer<Boolean> {
        if (it) showLoading() else hideLoading()
    }

    protected val commonErrorObserver = Observer<Exception> {
        showCommonError()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDaggerInjector()
        DataBindingUtil.inflate<DB>(inflater, layoutId, container, false).run {
            realBinding = this
            lifecycleOwner = viewLifecycleOwner
        }
        setDatabindingVaribles()
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loading.observe(viewLifecycleOwner, loadingObserver)
        viewModel.commonError.observe(viewLifecycleOwner, commonErrorObserver)
    }

    abstract fun initDaggerInjector()

    abstract fun setDatabindingVaribles()

    override fun onDestroyView() {
        super.onDestroyView()
        realBinding?.unbind()
        realBinding = null
    }

    fun showLoading() {
        requireActivity().showLoading()
    }

    fun hideLoading() {
        requireActivity().hideLoading()
    }

    fun showCommonError() {

    }
}