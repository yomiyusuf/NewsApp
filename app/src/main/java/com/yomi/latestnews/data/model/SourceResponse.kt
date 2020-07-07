package com.yomi.latestnews.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class SourceResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("sources")
    val sources: List<Source>
)

@Parcelize
data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("country")
    val country: String?
) : Parcelable