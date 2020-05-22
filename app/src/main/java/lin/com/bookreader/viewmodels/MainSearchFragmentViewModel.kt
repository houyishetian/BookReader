package lin.com.bookreader.viewmodels

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lin.com.bookreader.entity.Book

class MainSearchFragmentViewModel : ViewModel() {

    val list = MutableLiveData<List<Book>>(
        listOf(
            Book("起点", "仙逆", "耳根", "200", "8.0", 3000, "第2088章 蓦然回首（大结局）", "2016-01-30", "", ""),
            Book("起点", "仙逆", "耳根", "200", "8.0", 3000, "第2088章 蓦然回首（大结局）", "2016-01-30", "", ""),
            Book("起点", "仙逆", "耳根", "200", "8.0", 3000, "第2088章 蓦然回首（大结局）", "2016-01-30", "", "")
        )
    )

    fun bind(binding: ViewDataBinding, data: Any, positon: Int) {

    }
}