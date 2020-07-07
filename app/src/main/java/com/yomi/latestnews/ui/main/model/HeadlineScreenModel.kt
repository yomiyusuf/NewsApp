package com.yomi.latestnews.ui.main.model

import com.yomi.latestnews.data.model.Article

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class HeadlineScreenModel(fromApi: Article) {
    val sourceName = fromApi.source.name
    val title = fromApi.title
    val description = fromApi.description
    val author = fromApi.author
    val thumbnailUrl = fromApi.urlToImage
    val articleUrl = fromApi.url
}