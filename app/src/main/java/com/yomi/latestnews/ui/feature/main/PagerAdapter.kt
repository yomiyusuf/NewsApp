package com.yomi.latestnews.ui.feature.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yomi.latestnews.ui.feature.main.headlines.HeadlinesFragment
import com.yomi.latestnews.ui.feature.main.savedArticles.SavedFragment
import com.yomi.latestnews.ui.feature.main.sources.SourcesFragment

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HeadlinesFragment.newInstance()
            1 -> SourcesFragment.newInstance()
            else -> SavedFragment.newInstance()
        }
    }
}