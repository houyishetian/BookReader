package lin.com.bookreader.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseMultipleTypeRecyclerAdapter<DB : ViewDataBinding, T>(
    private val context: Context,
    private val itemsData: List<T>,
    private val mapItemViewType: (Int) -> Int,
    private val mapItemViewResLayoutId: (Int) -> Int,
    private val binder: ((Int, DB, T, Int) -> Unit)? = null
) :
    RecyclerView.Adapter<BaseMultipleTypeRecyclerViewHolder<DB>>() {

    override fun getItemViewType(position: Int): Int = mapItemViewType.invoke(position)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseMultipleTypeRecyclerViewHolder<DB> =
        BaseMultipleTypeRecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                mapItemViewResLayoutId.invoke(viewType),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemsData.size

    override fun onBindViewHolder(holder: BaseMultipleTypeRecyclerViewHolder<DB>, position: Int) {
        binder?.invoke(getItemViewType(position), holder.dataBinding, itemsData[position], position)
        holder.dataBinding.executePendingBindings()
    }
}

class BaseMultipleTypeRecyclerViewHolder<DB : ViewDataBinding>(val dataBinding: DB) :
    RecyclerView.ViewHolder(dataBinding.root)