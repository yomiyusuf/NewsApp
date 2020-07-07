package com.yomi.latestnews.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yomi.latestnews.R
import com.yomi.latestnews.ui.feature.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager2
    private val headlinesViewModel by viewModel<NewsViewModel>()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPager(view)
    }

    private fun setupPager(view: View) {
        pagerAdapter = PagerAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = pagerAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position){
                0 -> tab.text = getString(R.string.headlines)
                1 -> tab.text = getString(R.string.sources)
                else -> tab.text = getString(R.string.saves)
            }
        }.attach()
    }
}
