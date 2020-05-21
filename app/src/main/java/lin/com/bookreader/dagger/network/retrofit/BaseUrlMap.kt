package lin.com.bookreader.dagger.network.retrofit

import java.lang.Exception

class BaseUrlMap {
    companion object {
        const val WEB_Test = "WEB_Test"
        const val WEB_BIQUGE1 = "WEB_BIQUGE1"
        const val WEB_BIQUGE2 = "WEB_BIQUGE2"
        const val WEB_DINGDIAN = "WEB_DINGDIAN"
        const val WEB_AISHU = "WEB_AISHU"
        const val WEB_QINGKAN = "WEB_QINGKAN"
        const val WEB_DPCQ = "WEB_DPCQ"
        val map: Map<String, Pair<String, String>> = mapOf(
            WEB_Test to ("WEB_Test" to "https://www.it1352.com/"),
            WEB_BIQUGE1 to ("笔趣1" to "https://www.biquge5200.com/"),
            WEB_BIQUGE2 to ("笔趣2" to "https://www.xbiquge6.com/"),
            WEB_DINGDIAN to ("顶点" to "https://www.23wxc.com/"),
            WEB_AISHU to ("爱书" to "http://www.22ff.org/"),
            WEB_QINGKAN to ("请看" to "https://www.qk6.org/"),
            WEB_DPCQ to ("WEB_DPCQ" to "https://dpcq1.net/")
        )

        fun getBaseUrl(webKey: String): String =
            map[webKey]?.second ?: throw Exception("cannot get base url! The key is not found!")
    }
}