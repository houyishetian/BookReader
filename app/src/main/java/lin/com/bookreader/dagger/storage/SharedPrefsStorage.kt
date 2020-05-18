package lin.com.bookreader.dagger.storage

import android.content.Context
import javax.inject.Inject

class SharedPrefsStorage @Inject constructor(context: Context) : Storage {
    private val sharedPrefers = context.getSharedPreferences("BookReader", Context.MODE_PRIVATE)
    override fun setString(key: String, value: String) = with(sharedPrefers) {
        edit().putString(key, value).apply()
    }

    override fun getString(key: String): String = sharedPrefers.getString(key, "") ?: ""

    override fun setBoolean(key: String, value: Boolean) = with(sharedPrefers) {
        edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean = sharedPrefers.getBoolean(key, false)
}