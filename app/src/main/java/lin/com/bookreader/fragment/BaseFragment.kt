package lin.com.bookreader.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import java.lang.IllegalStateException
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel>(private val layoutId: Int) :
    Fragment() {
    protected val databinding: DB
        get() = realBinding
            ?: throw IllegalStateException("cannot get ViewDataBinding, please check the lifecycle!")

    private var realBinding: DB? = null

    @Inject
    protected lateinit var viewModel: VM

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

    abstract fun initDaggerInjector()

    abstract fun setDatabindingVaribles()

    override fun onDestroyView() {
        super.onDestroyView()
        realBinding?.unbind()
        realBinding = null
    }
}