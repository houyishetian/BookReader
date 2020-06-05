package lin.com.bookreader.entity

data class Book(
    val web: String,
    val bookName: String,
    val authorName: String,
    val wordsNum: String,
    val score: String,
    val scoreCount: Int,
    val lastChapter: String,
    val lastUpdate: String,
    val bookLink: String,
    val lastChapterLink: String
)

data class DialogSelectBean(
    val list: List<DialogSelectItemBean>,
    val leftBtnText: String? = null,
    val rightBtnText: String? = null
)

data class DialogSelectItemBean(val flag: String, val name: String)