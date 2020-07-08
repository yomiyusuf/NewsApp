package com.yomi.latestnews.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yomi.latestnews.data.model.Source

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
@Entity(tableName = "source")
class SourceScreenModel(
    @PrimaryKey
    val id: String,

    val name: String,

    var selected: Boolean) {

    constructor(fromApi: Source, selected: Boolean = false): this(
        fromApi.id,
        fromApi.name,
        selected
    )
}