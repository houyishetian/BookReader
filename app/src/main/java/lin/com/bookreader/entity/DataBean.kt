package lin.com.bookreader.entity

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

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
    val title: String,
    val leftBtn: DialogBottomButtonBean,
    val rightBtn: DialogBottomButtonBean,
    val list: List<DialogSelectItemBean>,
    val itemDecoration: RecyclerView.ItemDecoration?,
    private var binder: ((dataBinding: ViewDataBinding, data: Any, position: Int) -> Unit)? = null
) {
    fun bind(binding: ViewDataBinding, data: Any, positon: Int) {
        binder?.invoke(binding, data, positon)
    }
}

data class DialogBottomButtonBean(
    val text: String,
    val visible: Boolean,
    private val clickListener: ((view: View) -> Unit)? = null
) {
    fun onClick(view: View) {
        clickListener?.invoke(view)
    }
}

data class DialogSelectItemBean(val flag: String, val name: String)