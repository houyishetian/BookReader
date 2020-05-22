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