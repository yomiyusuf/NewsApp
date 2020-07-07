package com.yomi.latestnews.ui.model

import com.yomi.latestnews.data.model.Source

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class SourceScreenModel(fromApi: Source) {
    val name = fromApi.name
    val id = fromApi.id
}