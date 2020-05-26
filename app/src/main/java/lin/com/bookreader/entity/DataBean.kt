package lin.com.bookreader.entity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import lin.com.bookreader.adapter.FunctionForBinder

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
    val leftBtn: DialogBottomButtonBean?,
    val rightBtn: DialogBottomButtonBean?,
    val itemDecoration: RecyclerView.ItemDecoration?,
    val binder: FunctionForBinder? = null
)

data class DialogBottomButtonBean(
    val visible: Boolean = false,
    private val clickListener: ((view: View) -> Unit)? = null
) {
    fun onClick(view: View) {
        clickListener?.invoke(view)
    }
}

data class DialogSelectItemBean(val flag: String, val name: String)