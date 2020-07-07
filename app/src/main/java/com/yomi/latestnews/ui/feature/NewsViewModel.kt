package com.yomi.latestnews.ui.feature

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yomi.latestnews.domain.usecase.HeadlinesUseCase
import com.yomi.latestnews.domain.usecase.SourcesUseCase
import com.yomi.latestnews.ui.model.HeadlineScreenModel
import com.yomi.latestnews.ui.model.SourceScreenModel

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class NewsViewModel(private val headlinesUseCase: HeadlinesUseCase, private val sourcesUseCase: SourcesUseCase):  ViewModel() {

    private val _headlines = MutableLiveData<List<HeadlineScreenModel>>()
    val headlines : MutableLiveData<List<HeadlineScreenModel>>
        get() = _headlines

    private val _sources = MutableLiveData<List<SourceScreenModel>>()
    val sources : MutableLiveData<List<SourceScreenModel>>
        get() = _sources

    fun getHeadlines() {
        headlinesUseCase.execute(
            listOf("techcrunch"),
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
            onSuccess = { response ->
                _sources.value = response.sources.map { SourceScreenModel((it)) }
            },

            onError = { Log.e("VM", it.toString())})
    }

    fun saveArticle(headline: HeadlineScreenModel) {
        //headlinesUseCase.save()
    }
}