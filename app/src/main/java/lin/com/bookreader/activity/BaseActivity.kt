package lin.com.bookreader.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseActivity<DB : ViewDataBinding, VM : ViewModel>(private val layoutId: Int) :
    AppCompatActivity() {
    protected lateinit var databinding: DB
    @Inject
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, layoutId)
        databinding.lifecycleOwner = this
        initDaggerInjector()
        setDatabindingVaribles()
    }

    abstract fun initDaggerInjector()

    abstract fun setDatabindingVaribles()
}