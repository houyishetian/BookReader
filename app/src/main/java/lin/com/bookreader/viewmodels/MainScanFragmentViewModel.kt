package lin.com.bookreader.viewmodels

import androidx.lifecycle.ViewModel
import lin.com.bookreader.dagger.network.retrofit.BaseUrlMap
import lin.com.bookreader.dagger.network.retrofit.DaggerRetrofitComponent
import lin.com.bookreader.dagger.network.retrofit.RetrofitModule
import lin.com.bookreader.models.TestInterface
import okhttp3.ResponseBody
import retrofit2.Retrofit
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainScanFragmentViewModel:ViewModel() {

    @Inject
    lateinit var retrofit:Retrofit

    init {
        DaggerRetrofitComponent.builder().retrofitModule(RetrofitModule(BaseUrlMap.getBaseUrl(BaseUrlMap.WEB_Test)))
            .build().inject(this)
    }

    fun onClick(){
        retrofit.create(TestInterface::class.java).testUrl().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            object : Observer<ResponseBody> {
                override fun onError(e: Throwable?) {
                    Timber.e("onError")
                    e?.printStackTrace()
                }

                override fun onNext(t: ResponseBody?) {
                    Timber.e("onNext")
                    Timber.e("msg:${t.toString()}")
                }

                override fun onCompleted() {
                    Timber.e("onCompleted")
                }
            }
        )
    }
}