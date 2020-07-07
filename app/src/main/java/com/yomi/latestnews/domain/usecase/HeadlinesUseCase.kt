package com.yomi.latestnews.domain.usecase

import com.yomi.latestnews.data.model.HeadlineResponse
import com.yomi.latestnews.data.repository.NewsRepository
import com.yomi.latestnews.domain.usecase.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class HeadlinesUseCase(private val repository: NewsRepository): SingleUseCase<HeadlineResponse>() {

    override fun buildUseCaseSingle(): Single<HeadlineResponse> {
        return repository.getHeadlines()
    }

}