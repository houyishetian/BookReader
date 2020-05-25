package lin.com.bookreader.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.OneToManyEndpoint

class BookViewAdapterTool {
    companion object {

        @JvmStatic
        @BindingAdapter(value = ["itemLayout", "onBindItem"], requireAll = false)
        fun <DB : ViewDataBinding> setItemLayoutAndOnBindItem(
            view: RecyclerView,
            resId: Int,
            binder: FunctionForBinder<DB, Any>
        ) {
            getAdapter(view).register(Any::class.java, BookItemViewBinder(resId, binder))
        }

        private fun getAdapter(view: RecyclerView): MultiTypeAdapter = when (view.adapter) {
            is MultiTypeAdapter -> view.adapter as MultiTypeAdapter
            else -> MultiTypeAdapter().apply {
                view.adapter = this
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["linkers", "onBindItem"], requireAll = false)
        fun <DB : ViewDataBinding, T> setLinkerAndOnBindItem(
            view: RecyclerView,
            linkers: List<Linker>,
            binder: FunctionForBinder<DB, Any>
        ) {
            getAdapter(view).let {
                val binders = linkers.map { it.layoutId }.map { BookItemViewBinder(it, binder) }
                    .toTypedArray()
                it.register(Any::class.java).let {
                    // the method "to" is as same as an extension method in Tuples which has a high priority, so here use reflect way to invoke.
                    // As same as "it.to(binders)"
                    it.javaClass.getMethod(
                        "to",
                        binders.javaClass
                    ).invoke(binders) as OneToManyEndpoint<*>
                }.withLinker {
                    linkers.map { it.matcher }.withIndex().firstOrNull { it.value(it) }?.index ?: 0
                }
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["items"], requireAll = false)
        fun <T> setItems(view: RecyclerView, items: List<T>) {
            getAdapter(view).run {
                this.items = items
                notifyDataSetChanged()
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["itemDecoration"], requireAll = false)
        fun addItemDecoration(
            view: RecyclerView,
            itemDecoration: RecyclerView.ItemDecoration
        ) {
            view.addItemDecoration(itemDecoration)
        }
    }
}

class Linker(val matcher: (Any) -> Boolean, @LayoutRes val layoutId: Int)