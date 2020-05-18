package lin.com.bookreader.viewmodels

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import lin.com.bookreader.dagger.storage.SharedPrefsStorage
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {
    @Inject
    lateinit var sharedPrefsStorage: SharedPrefsStorage

    val savedValue = MediatorLiveData<String>()

    fun savedValueClicked(view: View) {
        sharedPrefsStorage.setString("Test", sharedPrefsStorage.getString("Test") + "x")
    }

    fun getValueClicked(view: View) {
        val value = sharedPrefsStorage.getString("Test")
        Timber.e("value:$value")
    }
}