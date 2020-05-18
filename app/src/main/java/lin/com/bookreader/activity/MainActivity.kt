package lin.com.bookreader.activity

import lin.com.bookreader.R
import lin.com.bookreader.dagger.activity.ActivityModule
import lin.com.bookreader.dagger.activity.DaggerActivityComponent
import lin.com.bookreader.dagger.app.AppComponentUtil
import lin.com.bookreader.databinding.ActivityMainBinding
import lin.com.bookreader.viewmodels.MainActivityViewModel

class MainActivity :
    BaseActivity<ActivityMainBinding, MainActivityViewModel>(R.layout.activity_main) {

    override fun setDatabindingVaribles() {
        databinding.viewModel = viewModel
    }

    override fun initDaggerInjector() {
        val component = DaggerActivityComponent.builder().activityModule(ActivityModule(this))
            .appComponent(AppComponentUtil.createAppComponent(this)).build()
        component.inject(this)
        component.inject(viewModel)
    }
}
