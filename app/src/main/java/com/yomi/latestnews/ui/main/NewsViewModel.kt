package com.yomi.latestnews.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yomi.latestnews.domain.usecase.HeadlinesUseCase
import com.yomi.latestnews.domain.usecase.SourcesUseCase
import com.yomi.latestnews.ui.main.model.HeadlineScreenModel

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class NewsViewModel(private val headlinesUseCase: HeadlinesUseCase, private val sourcesUseCase: SourcesUseCase):  ViewModel() {

    private val _headlines = MutableLiveData<List<HeadlineScreenModel>>()
    val headlines : MutableLiveData<List<HeadlineScreenModel>>
        get() = _headlines

    private val _headlines = MutableLiveData<List<SourceScreenModel>>()
    val headlines : MutableLiveData<List<SourceScreenModel>>
        get() = _headlines

    fun getHeadlines() {
        headlinesUseCase.execute(
            listOf("techcrunch", "abc-news"),
            onSuccess = { response ->
                _headlines.value = response.articles.map {
                    HeadlineScreenModel(
                        (it)
                    )
                }
            },

            onError = { Log.e("VM", it.toString())})
    }

    fun getSources() {
        sourcesUseCase.execute(
            null,
        )
    }
}