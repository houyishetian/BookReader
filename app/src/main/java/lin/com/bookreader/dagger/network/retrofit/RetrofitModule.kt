package lin.com.bookreader.dagger.network.retrofit

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class RetrofitModule(private val baseUrl:String) {

    @Provides
    fun provideBaseUrl(): String = baseUrl

    @Named("connTimeout")
    @Provides
    fun provideConnTimeout(): Long = 60

    @Named("readTimeout")
    @Provides
    fun provideReadTimeout(): Long = 60

    @Named("writeTimeout")
    @Provides
    fun provideWriteTimeout(): Long = 60

    @Provides
    fun provideTimeUnit():TimeUnit = TimeUnit.SECONDS

    @Provides
    fun provideOkHttpClient(
        @Named("connTimeout") connTimeout: Long,
        @Named("readTimeout") readTimeout: Long,
        @Named("writeTimeout") writeTimeout: Long,
        timeUnit:TimeUnit,
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(connTimeout, timeUnit)
            .readTimeout(readTimeout, timeUnit)
            .writeTimeout(writeTimeout, timeUnit)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideInterceptor(): Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.request().let {
                chain.proceed(it).run {
                    if (code == 301 || code == 302) {
                        val location = headers["Location"]
                        Timber.d("direct url:$location")
                        chain.proceed(it.newBuilder().url(location!!).build())
                    } else {
                        this
                    }
                }
            }
        }
    }

    @Provides
    fun provideRetrofit(
        baseUrl: String,
        okhttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJavaCallAdapterFactory.create()

    @Provides
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()
}