package com.yomi.latestnews.ui.feature

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yomi.latestnews.domain.usecase.HeadlinesUseCase
import com.yomi.latestnews.domain.usecase.SourcesUseCase
import com.yomi.latestnews.ui.model.HeadlineScreenModel
import com.yomi.latestnews.ui.model.SourceScreenModel
import com.yomi.latestnews.util.SingleLiveEvent
import io.reactivex.schedulers.Schedulers

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

    private val _savedHeadlines = MutableLiveData<List<HeadlineScreenModel>>()
    val savedHeadlines : MutableLiveData<List<HeadlineScreenModel>>
        get() = _savedHeadlines

    val emptyListEvent = SingleLiveEvent<Boolean>()
    val emptySavedHeadlinesEvent = SingleLiveEvent<Boolean>()
    val headlineExistEvent = SingleLiveEvent<Boolean>()
    val errorEvent = SingleLiveEvent<Boolean>()

    @SuppressLint("CheckResult")
    fun refreshHeadlines() {
        sourcesUseCase.getSavedItems()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe({ result ->
                if(result.isEmpty()) {
                    emptyListEvent.postValue(true)
                    _headlines.postValue(emptyList())
                } else {
                    emptyListEvent.postValue(false)
                    getHeadlines(result)}
                }
                , {
                    Log.e("NETWORK ERROR", it.toString())
                    errorEvent.postValue(true)
                    emptyListEvent.postValue(true)
                    _headlines.postValue(emptyList())
                })
    }

    private fun getHeadlines(sources: List<SourceScreenModel>) {
        headlinesUseCase.execute(
            sources.map { it.id },
            onSuccess = { response ->
                _headlines.value = response
            },

            onError = {
                errorEvent.postValue(true)
                Log.e("NETWORK ERROR", it.toString())
            })
    }

    fun getSources() {
        sourcesUseCase.execute(
            null,
            onSuccess = { response ->
                _sources.value = response
            },

            onError = {
                errorEvent.postValue(true)
                Log.e("NETWORK ERROR", it.toString())
            })
    }

    @SuppressLint("CheckResult")
    fun getSavedHeadlines() {
        headlinesUseCase.getSavedItems()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe({ result ->
                if(result.isEmpty()) {
                    emptySavedHeadlinesEvent.postValue(true)
                    _savedHeadlines.postValue(emptyList())
                } else {
                    emptySavedHeadlinesEvent.postValue(false)
                    _savedHeadlines.postValue(result)}
            }, {
                errorEvent.postValue(true)
                Log.e("DB ERROR", it.toString())
            })
    }

    @SuppressLint("CheckResult")
    fun findHeadline(headline: HeadlineScreenModel) {
        headlinesUseCase.findItem(headline)
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe({ headline -> headlineExistEvent.postValue(true) }, {headlineExistEvent.postValue(false)})
    }

    fun saveHeadline(headline: HeadlineScreenModel) {
        headlinesUseCase.insertItem(headline)
    }

    fun deleteHeadline(headline: HeadlineScreenModel) {
        headlinesUseCase.deleteItem(headline)
    }

    fun saveSource(source: SourceScreenModel) {
        sourcesUseCase.insertItem(source)
    }

    fun deleteSource(source: SourceScreenModel) {
        sourcesUseCase.deleteItem(source)
    }
}