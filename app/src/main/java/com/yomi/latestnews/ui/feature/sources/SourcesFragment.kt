package com.yomi.latestnews.ui.feature.sources

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.yomi.latestnews.R
import com.yomi.latestnews.ui.feature.NewsViewModel
import kotlinx.android.synthetic.main.fragment_sources.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SourcesFragment : Fragment() {

    private val viewModel by sharedViewModel<NewsViewModel>()
    private lateinit var listAdapter : SourceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        initRecyclerView()
        viewModel.getSources()
    }

    private fun registerObservers() {
        viewModel.sources.observe(viewLifecycleOwner, Observer {
            rv_sources.visibility = View.VISIBLE
            listAdapter.updateData(it)
        })
    }

    private fun initRecyclerView() {
        listAdapter = SourceListAdapter(arrayListOf()).apply {
            itemClick = { id, isChecked ->
                if (isChecked) {
                    viewModel.saveSource(id)
                } else {
                    viewModel.deleteSource(id)
                }
            }
        }
        rv_sources.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SourcesFragment()
    }
}
