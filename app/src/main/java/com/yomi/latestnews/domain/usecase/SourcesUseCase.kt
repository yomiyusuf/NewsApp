package com.yomi.latestnews.domain.usecase

import com.yomi.latestnews.data.model.SourceResponse
import com.yomi.latestnews.data.repository.NewsRepository
import com.yomi.latestnews.domain.usecase.base.DatabaseInteractor
import com.yomi.latestnews.domain.usecase.base.SingleUseCase
import com.yomi.latestnews.ui.model.SourceScreenModel
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class SourcesUseCase(private val repository: NewsRepository): SingleUseCase<List<SourceScreenModel>>(),
    DatabaseInteractor<SourceScreenModel> {

    override fun buildUseCaseSingle(params: List<String>?): Single<List<SourceScreenModel>> {
        return Singles.zip(repository.getSources(), repository.getSavedSources()) { remoteSources, localSources ->
            combineSources(remoteSources, localSources)
        }
    }

    private fun combineSources(remoteSources: SourceResponse, localSources: List<SourceScreenModel>)
            : List<SourceScreenModel> {
        return remoteSources.sources.map { remote ->
            val isSelected = localSources.any { local -> local.id ==  remote.id}
            SourceScreenModel(remote.id, remote.name, isSelected) }
    }

    override fun insertItem(item: SourceScreenModel) {
        repository.saveSource(item)
    }

    override fun deleteItem(item: SourceScreenModel) {
        repository.deleteSource(item)
    }

    override fun getSavedItems(): Single<List<SourceScreenModel>> {
        return repository.getSavedSources()
    }

    override fun findItem(item: SourceScreenModel): Single<SourceScreenModel> {
        return repository.findSource(item.id)
    }
}