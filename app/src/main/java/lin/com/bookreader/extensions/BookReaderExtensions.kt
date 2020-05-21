package lin.com.bookreader.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lin.com.bookreader.dagger.app.AppComponentHolder
import lin.com.bookreader.dagger.viewmodels.DaggerViewModelComponent
import lin.com.bookreader.dagger.viewmodels.ViewModelComponent
import lin.com.bookreader.dagger.viewmodels.ViewModelModule

fun <T : ViewModel> FragmentActivity.createViewModel(viewModelClass: Class<T>): T =
    ViewModelProvider(this).get(viewModelClass)

fun Fragment.buildComponent(): ViewModelComponent =
    DaggerViewModelComponent.builder()
        .appComponent(AppComponentHolder.getAppComponent(requireContext()))
        .viewModelModule(ViewModelModule(requireActivity()))
        .build()
