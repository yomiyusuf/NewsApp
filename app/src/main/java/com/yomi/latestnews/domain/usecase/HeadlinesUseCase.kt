package com.yomi.latestnews.domain.usecase

import com.yomi.latestnews.data.model.HeadlineResponse
import com.yomi.latestnews.data.repository.NewsRepository
import com.yomi.latestnews.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class HeadlinesUseCase(private val repository: NewsRepository): SingleUseCase<HeadlineResponse>() {

    override fun buildUseCaseSingle(sources: List<String>?): Single<HeadlineResponse> {
        val jobs = mutableListOf<Single<HeadlineResponse>>()
        sources?.forEach { jobs.add(repository.getHeadlines(it).subscribeOn(Schedulers.newThread())) }
        val merger = Function<Array<Any>, HeadlineResponse> {
            val response = HeadlineResponse("", 0, arrayListOf())
            it.forEach { res ->
                response.status = (res as HeadlineResponse).status
                response.totalResults += res.totalResults
                response.articles.addAll((res.articles))
            }
            response.apply { articles.sortByDescending { article -> article.publishedAt } }
        }
        return Single.zip(jobs, merger)
    }
}