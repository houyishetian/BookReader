package lin.com.bookreader.activity

import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import lin.com.bookreader.R
import lin.com.bookreader.dagger.activity.ActivityModule
import lin.com.bookreader.dagger.activity.DaggerActivityComponent
import lin.com.bookreader.dagger.app.AppComponentUtil
import lin.com.bookreader.databinding.ActivityMainBinding
import lin.com.bookreader.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<ActivityMainBinding, MainActivityViewModel>(R.layout.activity_main) {

    override fun initDaggerInjector() {
        val component = DaggerActivityComponent.builder().activityModule(ActivityModule(this))
            .appComponent(AppComponentUtil.createAppComponent(this)).build()
        component.inject(this)
        component.inject(viewModel)
    }

    override fun setDatabindingVaribles() {
        databinding.viewModel = viewModel
        main_bottom_navitaion_view.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.main_fragment_container
            )
        )
    }
}
