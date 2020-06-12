package lin.com.bookreader.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import lin.com.bookreader.R
import lin.com.bookreader.adapter.BaseRecyclerAdapter
import lin.com.bookreader.adapter.BookDividerItemDecoration
import lin.com.bookreader.databinding.FragmentMainSearchBinding
import lin.com.bookreader.databinding.ItemMainSearchResultBinding
import lin.com.bookreader.entity.Book
import lin.com.bookreader.extensions.buildComponent
import lin.com.bookreader.extensions.showRecyclerViewDialog
import lin.com.bookreader.viewmodels.MainSearchFragmentViewModel

class MainSearchFragment :
    BaseFragment<FragmentMainSearchBinding, MainSearchFragmentViewModel>(R.layout.fragment_main_search) {
    override fun initDaggerInjector() {
        buildComponent().inject(this)
    }

    override fun setDatabindingVaribles() {
        databinding.viewModel = viewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databinding.searchWebName.setOnClickListener {
            requireActivity().showRecyclerViewDialog(
                viewModel.dialogSelectBean,
                viewModel.dialogItemClickListener
            )
        }
        databinding.searchRcv.run {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(BookDividerItemDecoration())
            adapter = BaseRecyclerAdapter<ItemMainSearchResultBinding, Book>(
                requireContext(),
                viewModel.list,
                R.layout.item_main_search_result
            ) { dataBinding, itemData, position ->
                dataBinding.bean = itemData
            }
        }
    }
}