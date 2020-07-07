package com.yomi.latestnews.domain.usecase

import com.yomi.latestnews.data.model.SourceResponse
import com.yomi.latestnews.data.repository.NewsRepository
import com.yomi.latestnews.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class SourcesUseCase(private val repository: NewsRepository): SingleUseCase<SourceResponse>() {

    override fun buildUseCaseSingle(params: List<String>?): Single<SourceResponse> {
        return repository.getSources()
    }

}