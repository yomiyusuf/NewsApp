package com.yomi.latestnews.ui.feature


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs

import com.yomi.latestnews.R
import com.yomi.latestnews.util.AndroidHelper
import com.yomi.latestnews.util.visibleOrGone
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
            webview.webViewClient = WebViewClient() //force webpages to load with this webview
            webview.loadUrl(headline.articleUrl)

            viewModel.findHeadline(headline)

            btn_detail_save.setOnClickListener {
                viewModel.saveHeadline(args.headline)
                btn_detail_save.visibleOrGone(false)
                AndroidHelper.showToast(context!!, "Article Saved")
            }
        }
        registerObserver()
    }

    private fun registerObserver() {
        //hide 'save article' button if the headline has already been saved
        viewModel.headlineExistEvent.observe(viewLifecycleOwner, Observer { headlineSaved ->
            btn_detail_save.visibleOrGone(!headlineSaved)
        })
    }
}
