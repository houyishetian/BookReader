package lin.com.bookreader.models

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url
import rx.Observable

interface BookReaderApiService {
    @GET("/modules/article/search.php")
    @Headers("User-Agent:Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
    //"http://www.biquge5200.com/modules/article/search.php?searchkey="+bookName;
    fun searchBooksFromBiQuGe1(@Query("searchkey", encoded = true) bookName: String): Observable<ResponseBody>

    @GET
    @Headers("User-Agent:Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
    fun getResponse(@Url url: String): Observable<ResponseBody>
}