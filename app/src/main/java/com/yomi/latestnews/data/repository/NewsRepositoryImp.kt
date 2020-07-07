package com.yomi.latestnews.data.repository

import com.yomi.latestnews.data.model.HeadlineResponse
import com.yomi.latestnews.data.model.SourceResponse
import com.yomi.latestnews.data.remote.NewsService
import io.reactivex.Single

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class NewsRepositoryImp(private val newsService: NewsService): NewsRepository {

    override fun getSources(): Single<SourceResponse> {
        return newsService.getSources()
    }

    override fun getHeadlines(source: String): Single<HeadlineResponse> {
        return newsService.getHeadlines(source)
    }
}