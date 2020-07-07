package com.yomi.latestnews.data.repository

import com.yomi.latestnews.data.model.HeadlineResponse
import com.yomi.latestnews.data.model.SourceResponse
import io.reactivex.Single

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
interface NewsRepository {
    fun getSources(): Single<SourceResponse>
    fun getHeadlines(): Single<HeadlineResponse>
}