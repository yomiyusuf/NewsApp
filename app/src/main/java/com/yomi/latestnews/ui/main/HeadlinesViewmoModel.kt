package com.yomi.latestnews.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yomi.latestnews.domain.usecase.HeadlinesUseCase

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class HeadlinesViewmoModel(private val headlinesUseCase: HeadlinesUseCase):  ViewModel() {
    private val _headlines = MutableLiveData<>
}