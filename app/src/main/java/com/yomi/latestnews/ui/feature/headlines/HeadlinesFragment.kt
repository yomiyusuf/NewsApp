package com.yomi.latestnews.ui.feature.headlines


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.yomi.latestnews.R
import com.yomi.latestnews.ui.feature.NewsViewModel
import kotlinx.android.synthetic.main.fragment_headlines.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HeadlinesFragment : Fragment() {

    private val viewModel by sharedViewModel<NewsViewModel>()
    private val listAdapter = HeadlineListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        initRecyclerView()
        viewModel.getHeadlines()

    }

    private fun registerObservers() {
        viewModel.headlines.observe(viewLifecycleOwner, Observer {
            rv_headlines.visibility = View.VISIBLE
            listAdapter.updateData(it)
        })
    }

    private fun initRecyclerView() {
        rv_headlines.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HeadlinesFragment()
    }
}
