package lin.com.bookreader.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel>(private val layoutId: Int) :
    Fragment() {
    protected lateinit var databinding: DB
    @Inject
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDaggerInjector()
        databinding = DataBindingUtil.inflate<DB>(inflater, layoutId, container, false)
        databinding.lifecycleOwner = this
        setDatabindingVaribles()
        return databinding.root
    }

    abstract fun initDaggerInjector()

    abstract fun setDatabindingVaribles()
}