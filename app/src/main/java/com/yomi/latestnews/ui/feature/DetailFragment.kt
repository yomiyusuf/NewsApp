package com.yomi.latestnews.ui.feature


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.yomi.latestnews.R
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : Fragment() {

    private val viewModel by sharedViewModel<NewsViewModel>()

    val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val headline = args.headline
            webview.loadUrl(headline.articleUrl)

            btn_detail_save.setOnClickListener {
                viewModel.saveHeadline(args.headline)
            }
        }
    }
}
