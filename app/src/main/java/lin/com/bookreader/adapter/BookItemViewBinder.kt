package lin.com.bookreader.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import lin.com.bookreader.BR
import me.drakeet.multitype.ItemViewBinder

class BookItemViewBinder<DB : ViewDataBinding, T : Any>(
    private @LayoutRes val layoutId: Int,
    private val binder: FunctionForBinder?
) :
    ItemViewBinder<T, BookViewHolder<DB>>() {

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BookViewHolder<DB> =
        BookViewHolder(DataBindingUtil.inflate(inflater, layoutId, parent, false))

    override fun onBindViewHolder(viewHolder: BookViewHolder<DB>, t: T) {
        viewHolder.databindng.setVariable(BR.bean, t)
        binder?.bind(viewHolder.databindng, t, viewHolder.adapterPosition)
        viewHolder.databindng.executePendingBindings()
    }
}

class BookViewHolder<DB : ViewDataBinding>(val databindng: DB) :
    RecyclerView.ViewHolder(databindng.root)

interface FunctionForBinder {
    fun bind(databinding: ViewDataBinding, itemData: Any, position: Int)
}