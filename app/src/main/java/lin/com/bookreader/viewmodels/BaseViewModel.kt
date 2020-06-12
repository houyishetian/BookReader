package lin.com.bookreader.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    val commonError: MutableLiveData<Exception> by lazy { MutableLiveData<Exception>() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}