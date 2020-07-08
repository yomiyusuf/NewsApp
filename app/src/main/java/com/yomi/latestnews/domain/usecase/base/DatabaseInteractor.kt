package com.yomi.latestnews.domain.usecase.base

import io.reactivex.Single

/**
 * Created by Yomi Joseph on 2020-07-08.
 */
interface DatabaseInteractor<T> {
    fun insertItem(item: T)
    fun deleteItem(item: T)
    fun getSavedItems(): Single<List<T>>
}