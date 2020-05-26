package lin.com.bookreader.viewmodels

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lin.com.bookreader.adapter.BookDividerItemDecoration
import lin.com.bookreader.adapter.FunctionForBinder
import lin.com.bookreader.dagger.network.retrofit.BaseUrlMap
import lin.com.bookreader.databinding.ItemDialogSelectBinding
import lin.com.bookreader.databinding.ItemMainSearchResultBinding
import lin.com.bookreader.entity.Book
import lin.com.bookreader.entity.DialogSelectBean
import lin.com.bookreader.entity.DialogSelectItemBean
import timber.log.Timber

class MainSearchFragmentViewModel : ViewModel() {

    val list = MutableLiveData<List<Book>>()

    val webTypeList = BaseUrlMap.map.map { DialogSelectItemBean(it.key, it.value.first) }

    val itemDecoration = BookDividerItemDecoration()

    val dialogSelectBean = DialogSelectBean(
        webTypeList,
        null,
        null,
        BookDividerItemDecoration(),
        object : FunctionForBinder {
            override fun bind(databinding: ViewDataBinding, itemData: Any, position: Int) {
                (databinding as ItemDialogSelectBinding).itemDialogName.setOnClickListener {
                    Timber.e("onClick:${(itemData as? DialogSelectItemBean)?.name}")
                }
            }
        }
    )

    val webType = MutableLiveData<String>(webTypeList.first().name)

    fun bind(binding: ViewDataBinding, data: Any, positon: Int) {
        (binding as ItemMainSearchResultBinding).bookName.setOnClickListener {
            Timber.e("name:${(data as Book).bookName}")
        }
    }
}