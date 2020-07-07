package com.yomi.latestnews.ui.main


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.yomi.latestnews.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HeadlinesFragment : Fragment() {

    private val viewModel by sharedViewModel<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewModel.refresh()
    }

    private fun initObservers() {
        viewModel.headlines.observe(viewLifecycleOwner, Observer {
            it?.let{ Log.e("FRAGMENT", it.toString())}
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = HeadlinesFragment()
    }
}
