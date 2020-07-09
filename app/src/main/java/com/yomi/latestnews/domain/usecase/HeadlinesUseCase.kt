package com.yomi.latestnews.domain.usecase

import com.yomi.latestnews.data.model.HeadlineResponse
import com.yomi.latestnews.data.repository.NewsRepository
import com.yomi.latestnews.domain.usecase.base.DatabaseInteractor
import com.yomi.latestnews.domain.usecase.base.SingleUseCase
import com.yomi.latestnews.ui.model.HeadlineScreenModel
import io.reactivex.Single
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class HeadlinesUseCase(private val repository: NewsRepository): SingleUseCase<List<HeadlineScreenModel>>(),
    DatabaseInteractor<HeadlineScreenModel>{

    override fun buildUseCaseSingle(sources: List<String>?): Single<List<HeadlineScreenModel>> {
        val jobs = mutableListOf<Single<HeadlineResponse>>()
        sources?.forEach { jobs.add(repository.getHeadlines(it).subscribeOn(Schedulers.newThread())) }
        val merger = Function<Array<Any>, List<HeadlineScreenModel>> {
            val response = HeadlineResponse("", 0, arrayListOf())
            it.forEach { res ->
                response.status = (res as HeadlineResponse).status
                response.totalResults += res.totalResults
                response.articles.addAll((res.articles))
            }
            response.apply { articles.sortByDescending { article -> article.publishedAt } }.articles
                .map { article -> HeadlineScreenModel(article) }
        }
        return Single.zip(jobs, merger)
    }

    override fun insertItem(item: HeadlineScreenModel) {
        repository.saveHeadline(item)
    }

    override fun deleteItem(item: HeadlineScreenModel) {
        repository.deleteHeadline(item)
    }

    override fun getSavedItems(): Single<List<HeadlineScreenModel>> {
        return repository.getSavedHeadlines()
    }

    override fun findItem(item: HeadlineScreenModel): Single<HeadlineScreenModel> {
        return repository.findHeadline(item.articleUrl)
    }


}