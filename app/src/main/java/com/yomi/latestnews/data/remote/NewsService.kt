package com.yomi.latestnews.data.remote

import com.yomi.latestnews.data.model.HeadlineResponse
import com.yomi.latestnews.data.model.SourceResponse
import com.yomi.latestnews.util.Endpoints
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
interface NewsService {

    @GET(Endpoints.SOURCES)
    fun getSources(): Single<SourceResponse>

    @GET(Endpoints.HEADLINES)
    fun getHeadlines(): Single<HeadlineResponse>
}