package lin.com.bookreader.extensions

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <T : ViewModel> FragmentActivity.createViewModel(viewModelClass: Class<T>): T =
    ViewModelProvider(this).get(viewModelClass)