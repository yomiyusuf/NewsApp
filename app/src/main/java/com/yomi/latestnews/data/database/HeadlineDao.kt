package com.yomi.latestnews.data.database

import androidx.room.*
import com.yomi.latestnews.ui.model.HeadlineScreenModel
import com.yomi.latestnews.ui.model.SourceScreenModel
import io.reactivex.Single

/**
 * Created by Yomi Joseph on 2020-07-08.
 */
@Dao
interface HeadlineDao {

    @Query("SELECT * FROM headline")
    fun getSavedHeadlines(): Single<List<HeadlineScreenModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: HeadlineScreenModel)

    @Delete
    fun delete(model: HeadlineScreenModel)
}