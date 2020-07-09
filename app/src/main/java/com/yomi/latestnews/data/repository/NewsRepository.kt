package com.yomi.latestnews.data.repository

import com.yomi.latestnews.data.model.HeadlineResponse
import com.yomi.latestnews.data.model.SourceResponse
import com.yomi.latestnews.ui.model.HeadlineScreenModel
import com.yomi.latestnews.ui.model.SourceScreenModel
import io.reactivex.Single

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
interface NewsRepository {
    fun getSources(): Single<SourceResponse>
    fun getSavedSources(): Single<List<SourceScreenModel>>
    fun getHeadlines(source: String): Single<HeadlineResponse>
    fun getSavedHeadlines(): Single<List<HeadlineScreenModel>>
    fun saveSource(source: SourceScreenModel)
    fun deleteSource(source: SourceScreenModel)
    fun saveHeadline(headline: HeadlineScreenModel)
    fun deleteHeadline(headline: HeadlineScreenModel)
    fun findHeadline(url: String): Single<HeadlineScreenModel>
    fun findSource(id: String): Single<SourceScreenModel>
}