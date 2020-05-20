package lin.com.bookreader.models

import okhttp3.ResponseBody
import retrofit2.http.GET
import rx.Observable

interface TestInterface {

    @GET("/1533649.html")
    fun testUrl(): Observable<ResponseBody>
}