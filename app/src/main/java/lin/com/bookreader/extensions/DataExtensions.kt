package lin.com.bookreader.extensions

import android.util.Log
import lin.com.bookreader.utils.StringKtUtil
import okhttp3.ResponseBody
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

fun ResponseBody.readLinesOfHtml(): List<String> {
    val byteOutPutStream = ByteArrayOutputStream()
    byteStream().copyTo(byteOutPutStream)
    val inputStream0 = ByteArrayInputStream(byteOutPutStream.toByteArray())
    val inputStream1 = ByteArrayInputStream(byteOutPutStream.toByteArray())
    val charset = inputStream0.bufferedReader().readLines().firstOrNull { it.toLowerCase().contains("charset=\"?[^\"^\n^;]+\"?".toRegex()) }?.let {
        StringKtUtil.getDataFromContentByRegex(it.toLowerCase(), "charset=\"?([^\"^\n^;]+)\"?", listOf(1))?.get(0)
    } ?: return mutableListOf(inputStream1.bufferedReader().readText())
    Log.d("charset", charset)
    return inputStream1.bufferedReader(Charset.forName(charset)).readLines().apply {
        inputStream0.close()
        inputStream1.close()
        byteOutPutStream.close()
    }
}