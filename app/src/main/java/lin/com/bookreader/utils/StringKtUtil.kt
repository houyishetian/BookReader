package lin.com.bookreader.utils

import android.text.format.DateFormat
import java.util.*
import java.util.regex.Pattern

class StringKtUtil {
    companion object {
        /**
         * remove the unuseful chars from chapter
         * remove the unuseful infos from chapter:1212 测试章节(第五更) -> 1212 测试章节
         */
        fun removeUnusefulCharsFromChapter(chapterName: String): String =
            replaceDataOfContentByRegex(
                chapterName,
                "[\\(\\[（【第]+[0-9一二三四五六七八九十零终末上中下]+[\\)\\]）】更]+"
            )

        /**
         * get the related data from content by regex
         * @param content the content
         * @param regex the regex
         * @param groups the groups
         * @param useMatch true-match the total contant; false-find from the total contant
         * @return null-not find
         */
        @JvmOverloads
        fun getDataFromContentByRegex(
            content: String,
            regex: String,
            groups: List<Int>,
            useMatch: Boolean = false
        ): List<String>? {
            Pattern.compile(regex).matcher(content).let { matcher ->
                if (if (useMatch) matcher.matches() else matcher.find()) {
                    return mutableListOf<String>().apply {
                        groups.forEach {
                            matcher.group(it)?.let { add(it) }
                        }
                    }
                }
            }
            return null
        }

        /**
         * get the related data list from content by regex, such as get chapter list from content
         * @param content the content
         * @param regex the regex
         * @param groups the groups
         * @return if didn't find, will return null
         */
        fun getDataListFromContentByRegex(
            content: String,
            regex: String,
            groups: List<Int>
        ): List<List<String>>? {
            Pattern.compile(regex).matcher(content).let { matcher ->
                var result: MutableList<List<String>>? = null
                while (matcher.find()) {
                    if (result == null) result = mutableListOf()
                    result.add(mutableListOf<String>().apply {
                        groups.forEach {
                            matcher.group(it)?.let { add(it) }
                        }
                    })
                }
                return result
            }
        }

        /**
         * replace the regex part with new value
         * @param content the content
         * @param regex the regex
         * @param newValue the new value
         * @return the new data after replace
         */
        @JvmOverloads
        fun replaceDataOfContentByRegex(
            content: String,
            regex: String,
            newValue: String = ""
        ): String = content.replace(regex.toRegex(), newValue)

        fun formatTime(time: Long): String =
            DateFormat.format("yy-MM-dd HH:mm", Date(time)).toString()

        fun removeSeconds(data: String): String =
            Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}):\\d{2}").matcher(data).takeIf { it.matches() }?.group(1) ?: data
    }
}