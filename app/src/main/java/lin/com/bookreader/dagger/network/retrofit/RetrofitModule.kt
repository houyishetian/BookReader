package lin.com.bookreader.dagger.network.retrofit

import dagger.Module
import dagger.Provides
import lin.com.bookreader.models.BookReaderApiModel
import lin.com.bookreader.models.BookReaderApiModelIml
import lin.com.bookreader.models.BookReaderApiService
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
class RetrofitModule {
    companion object {
        private const val connTimeoutKey = "connTimeout"
        private const val readTimeoutKey = "readTimeout"
        private const val writeTimeoutKey = "writeTimeout"
    }

    @Named(connTimeoutKey)
    @Provides
    fun provideConnTimeout(): Long = 10

    @Named(readTimeoutKey)
    @Provides
    fun provideReadTimeout(): Long = 10

    @Named(writeTimeoutKey)
    @Provides
    fun provideWriteTimeout(): Long = 10

    @Provides
    fun provideTimeUnit(): TimeUnit = TimeUnit.SECONDS

    @Provides
    fun provideOkHttpClient(
        @Named(connTimeoutKey) connTimeout: Long,
        @Named(readTimeoutKey) readTimeout: Long,
        @Named(writeTimeoutKey) writeTimeout: Long,
        timeUnit: TimeUnit,
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
        okhttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okhttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJavaCallAdapterFactory.create()

    @Provides
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun provideBookReaderApiService(retrofit: Retrofit): BookReaderApiService =
        retrofit.create(BookReaderApiService::class.java)

    @Provides
    fun provideBookReaderApiModel(bookReaderApiService: BookReaderApiService): BookReaderApiModel =
        BookReaderApiModelIml(bookReaderApiService)
}