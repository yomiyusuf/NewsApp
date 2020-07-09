package com.yomi.latestnews.data.database

import androidx.room.*
import com.yomi.latestnews.ui.model.SourceScreenModel
import io.reactivex.Single

/**
 * Created by Yomi Joseph on 2020-07-08.
 */
@Dao
interface SourceDao {

    @Query("SELECT * FROM source")
    fun getSelectedSources(): Single<List<SourceScreenModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(model: SourceScreenModel)

    @Delete
    fun delete(model: SourceScreenModel)

    @Query("SELECT * FROM source WHERE id =:id")
    fun find(id: String): Single<SourceScreenModel>
}