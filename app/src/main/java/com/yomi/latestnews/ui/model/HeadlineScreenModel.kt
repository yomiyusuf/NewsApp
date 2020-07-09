package com.yomi.latestnews.ui.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yomi.latestnews.data.model.Article
import kotlinx.android.parcel.Parcelize

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
@Entity(tableName = "headline")
@Parcelize
class HeadlineScreenModel(
    val title: String,

    val description: String,

    val author: String?,

    val thumbnailUrl: String?,

    @PrimaryKey
    val articleUrl: String
    ) : Parcelable {
    constructor(fromApi: Article): this(
        fromApi.title,
        fromApi.description,
        fromApi.author,
        fromApi.urlToImage,
        fromApi.url
    )
}