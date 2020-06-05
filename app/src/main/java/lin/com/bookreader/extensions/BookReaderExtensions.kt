package lin.com.bookreader.extensions

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import lin.com.bookreader.R
import lin.com.bookreader.adapter.BaseRecyclerAdapter
import lin.com.bookreader.adapter.BookDividerItemDecoration
import lin.com.bookreader.dagger.app.AppComponentHolder
import lin.com.bookreader.dagger.viewmodels.DaggerViewModelComponent
import lin.com.bookreader.dagger.viewmodels.ViewModelComponent
import lin.com.bookreader.dagger.viewmodels.ViewModelModule
import lin.com.bookreader.databinding.DialogSelectFromRecyclerViewBinding
import lin.com.bookreader.databinding.ItemDialogSelectBinding
import lin.com.bookreader.entity.DialogSelectBean
import lin.com.bookreader.entity.DialogSelectItemBean


fun <T : ViewModel> FragmentActivity.createViewModel(viewModelClass: Class<T>): T =
    ViewModelProvider(this).get(viewModelClass)

fun Fragment.buildComponent(): ViewModelComponent =
    DaggerViewModelComponent.builder()
        .appComponent(AppComponentHolder.getAppComponent(requireContext()))
        .viewModelModule(ViewModelModule(requireActivity()))
        .build()

fun Context.showRecyclerViewDialog(
    dialogSelectBean: DialogSelectBean,
    itemClickListener: ((DialogSelectItemBean, Int) -> Unit)? = null,
    previousBtnClickListener: ((View) -> Unit)? = null,
    nextBtnClickListener: ((View) -> Unit)? = null
) {
    val databinding = DataBindingUtil.inflate<DialogSelectFromRecyclerViewBinding>(
        LayoutInflater.from(this),
        R.layout.dialog_select_from_recycler_view,
        null,
        false
    )
    val dialog = Dialog(this, R.style.MainDialog)
    dialog.setContentView(databinding.root)
    dialog.window?.run {
        val (width, height) = windowManager.defaultDisplay.let {
            val point = Point()
            it.getSize(point)
            Pair((point.x * 0.8).toInt(), (point.y * 0.4).toInt())
        }
        this.setLayout(width, height)
        setGravity(Gravity.CENTER)
    }
    dialog.show()
    databinding.dialogBean = dialogSelectBean
    databinding.dialogSelectRcv.run {
        layoutManager = LinearLayoutManager(this@showRecyclerViewDialog)
        addItemDecoration(BookDividerItemDecoration())
        adapter = BaseRecyclerAdapter<ItemDialogSelectBinding, DialogSelectItemBean>(
            this@showRecyclerViewDialog,
            dialogSelectBean.list,
            R.layout.item_dialog_select
        ) { itemDataBinding, itemData, position ->
            itemDataBinding.bean = itemData
            itemDataBinding.itemDialogName.setOnClickListener {
                itemClickListener?.invoke(
                    itemData,
                    position
                )
                dialog.dismiss()
            }
        }
    }
    databinding.dialogSelectPreviousBtn.setOnClickListener(previousBtnClickListener)
    databinding.dialogSelectNextBtn.setOnClickListener(nextBtnClickListener)
}

fun Activity.hideSoftInput() {
    window.peekDecorView()?.run {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            windowToken,
            0
        )
    }
}
