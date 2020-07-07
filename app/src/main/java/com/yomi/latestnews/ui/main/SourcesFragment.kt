package com.yomi.latestnews.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yomi.latestnews.R
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
        viewModel.getSources()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SourcesFragment()
    }
}
