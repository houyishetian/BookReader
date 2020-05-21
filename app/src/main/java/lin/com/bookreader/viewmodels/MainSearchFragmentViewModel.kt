package lin.com.bookreader.viewmodels

import androidx.lifecycle.ViewModel
import lin.com.bookreader.dagger.network.retrofit.BaseUrlMap
import lin.com.bookreader.dagger.network.retrofit.DaggerRetrofitComponent
import lin.com.bookreader.dagger.network.retrofit.RetrofitModule
import lin.com.bookreader.extensions.readLinesOfHtml
import lin.com.bookreader.models.BookReaderApiService
import okhttp3.ResponseBody
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainSearchFragmentViewModel : ViewModel() {

    @Inject
    lateinit var bookReaderApiService: BookReaderApiService

    init {
        DaggerRetrofitComponent.builder()
            .retrofitModule(RetrofitModule(BaseUrlMap.getBaseUrl(BaseUrlMap.WEB_Test)))
            .build().inject(this)
    }

    fun onClick() {
        bookReaderApiService.getResponse("/130_130510/").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
            object : Observer<ResponseBody> {
                override fun onError(e: Throwable?) {
                    Timber.e("onError")
                    e?.printStackTrace()
                }

                override fun onNext(t: ResponseBody?) {
                    Timber.e("onNext")
                    Timber.e("msg:${t?.readLinesOfHtml().toString()}")
                }

                override fun onCompleted() {
                    Timber.e("onCompleted")
                }
            }
        )
    }
}