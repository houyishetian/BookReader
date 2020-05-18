package lin.com.bookreader.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <T : ViewModel> AppCompatActivity.createViewModel(viewModelClass: Class<T>): T =
    ViewModelProvider(this).get(viewModelClass)