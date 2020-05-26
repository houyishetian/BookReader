package lin.com.bookreader.models

import okhttp3.ResponseBody
import retrofit2.http.*
import rx.Observable

interface BookReaderApiService {
    @GET("/modules/article/search.php")
    @Headers("User-Agent:Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
    //"http://www.biquge5200.com/modules/article/search.php?searchkey="+bookName;
    fun searchBooksFromBiQuGe1(@Url url: String, @Query("searchkey", encoded = true) bookName: String): Observable<ResponseBody>

    @GET("/search.php")
    @Headers("User-Agent:Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
    //https://www.xbiquge6.com/search.php?keyword=仙逆
    fun searchFromBIQUGE2(@Url url: String, @Query("keyword", encoded = true) bookName: String): Observable<ResponseBody>

    @GET("/modules/article/search.php")
    @Headers("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    //"https://www.x23us.com/modules/article/search.php?searchtype=keywords&searchkey=" + URLEncoder.encode(params[0], "gbk");
    fun searchFromDINGDIAN(@Url url: String, @QueryMap(encoded = true) params: HashMap<String, String>): Observable<ResponseBody>

    @GET("/s_{last}")
    @Headers("User-Agent:Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
    //"http://www.22ff.org/s_"+bookName;
    fun searchFromAISHUWANG(@Url url: String, @Path("last") bookName: String): Observable<ResponseBody>

    @GET("/novel.php")
    @Headers("User-Agent:Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
    //"https://www.qk6.org/novel.php?action=search&searchtype=novelname&searchkey=" + searchKey + "&input="
    fun searchFromQINGKAN(@Url url: String, @QueryMap(encoded = true) params: HashMap<String, String>): Observable<ResponseBody>

    @GET("/search.html")
    @Headers("User-Agent:Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
    //https://dpcq1.net/search.html?searchtype=novelname&searchkey=%E8%9B%8A%E7%9C%9F%E4%BA%BA
    fun searchFromDPCQ(@Url url: String, @QueryMap(encoded = true) params: HashMap<String, String>): Observable<ResponseBody>

    @GET
    @Headers("User-Agent:Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
    fun getResponse(@Url url: String): Observable<ResponseBody>
}