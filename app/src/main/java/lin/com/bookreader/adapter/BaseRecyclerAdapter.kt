package lin.com.bookreader.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerAdapter<DB : ViewDataBinding, T>(
    private val context: Context,
    private val itemsData: List<T>,
    @LayoutRes private val layoutId: Int,
    private val binder: ((DB, T, Int) -> Unit)? = null
) :
    RecyclerView.Adapter<BaseRecyclerViewHolder<DB>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<DB> =
        BaseRecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                layoutId,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemsData.size

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<DB>, position: Int) {
        binder?.invoke(holder.dataBinding, itemsData[position], position)
        holder.dataBinding.executePendingBindings()
    }
}

class BaseRecyclerViewHolder<DB : ViewDataBinding>(val dataBinding: DB) :
    RecyclerView.ViewHolder(dataBinding.root)