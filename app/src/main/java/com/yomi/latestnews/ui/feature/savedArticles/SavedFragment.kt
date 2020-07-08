package com.yomi.latestnews.ui.feature.savedArticles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.yomi.latestnews.R
import com.yomi.latestnews.ui.feature.NewsViewModel
import com.yomi.latestnews.ui.main.MainFragmentDirections
import com.yomi.latestnews.util.AndroidHelper
import kotlinx.android.synthetic.main.fragment_saved.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SavedFragment : Fragment() {
    private val viewModel by sharedViewModel<NewsViewModel>()
    private lateinit var listAdapter : SavedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSavedHeadlines()
    }

    private fun registerObservers() {
        viewModel.savedHeadlines.observe(viewLifecycleOwner, Observer {
            listAdapter.updateData(it)
        })
    }

    private fun initRecyclerView() {
        listAdapter = SavedListAdapter(arrayListOf()).apply {
            itemClick = { item ->
                val action = MainFragmentDirections.actionDetail(item)
                Navigation.findNavController(view!!).navigate(action)
            }

            deleteClick = { item ->
                AndroidHelper.showDialog(context!!, "Are you sure you want to delete?",
                    "You are about to delete ${item.title}", "Delete", "Cancel",
                    { viewModel.deleteHeadline(item) }, {})
            }
        }
        rv_saved.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SavedFragment()
    }
}
