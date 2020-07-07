package com.yomi.latestnews.ui.feature.sources

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.yomi.latestnews.R
import com.yomi.latestnews.ui.feature.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SourcesFragment : Fragment() {
    private val viewModel by sharedViewModel<NewsViewModel>()

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
        viewModel.getSources()
    }

    private fun registerObservers() {
        viewModel.sources.observe(viewLifecycleOwner, Observer {
            it?.let{ Log.e("SOURCES", it.toString())}
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = SourcesFragment()
    }
}
