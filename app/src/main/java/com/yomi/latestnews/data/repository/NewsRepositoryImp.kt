package com.yomi.latestnews.data.repository

import com.yomi.latestnews.data.database.AppDatabase
import com.yomi.latestnews.data.model.HeadlineResponse
import com.yomi.latestnews.data.model.SourceResponse
import com.yomi.latestnews.data.remote.NewsService
import com.yomi.latestnews.ui.model.HeadlineScreenModel
import com.yomi.latestnews.ui.model.SourceScreenModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class NewsRepositoryImp(private val newsService: NewsService, private val database: AppDatabase): NewsRepository {

    override fun getSources(): Single<SourceResponse> {
        return newsService.getSources()
    }

    override fun getSavedSources(): Single<List<SourceScreenModel>> {
        return database.sourceDao().getSelectedSources()
    }

    override fun getHeadlines(source: String): Single<HeadlineResponse> {
        return newsService.getHeadlines(source)
    }

    override fun getSavedHeadlines(): Single<List<HeadlineScreenModel>> {
        return database.headlineDao().getSavedHeadlines()
    }

    override fun saveSource(source: SourceScreenModel) {
        Completable.fromRunnable { database.sourceDao().insert(source) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun deleteSource(source: SourceScreenModel) {
        Completable.fromRunnable {database.sourceDao().delete(source)}
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun saveHeadline(headline: HeadlineScreenModel) {
        Completable.fromRunnable { database.headlineDao().insert(headline) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun deleteHeadline(headline: HeadlineScreenModel) {
        Completable.fromRunnable {database.headlineDao().delete(headline)}
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}