package com.yomi.latestnews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yomi.latestnews.ui.model.HeadlineScreenModel
import com.yomi.latestnews.ui.model.SourceScreenModel

/**
 * Created by Yomi Joseph on 2020-07-08.
 */
@Database(entities = [HeadlineScreenModel::class, SourceScreenModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sourceDao(): SourceDao
    abstract fun headlineDao(): HeadlineDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "newsapp-db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}